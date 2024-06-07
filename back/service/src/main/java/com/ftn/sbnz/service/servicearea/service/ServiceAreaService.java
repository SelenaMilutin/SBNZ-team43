package com.ftn.sbnz.service.servicearea.service;

import com.ftn.sbnz.model.servicearea.IServiceAreaService;
import com.ftn.sbnz.model.servicearea.ServiceArea;
import com.ftn.sbnz.model.servicearea.ServiceAreaDTO;
import com.ftn.sbnz.model.user.Admin;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.service.dto.NotificationDTO;
import com.ftn.sbnz.service.exception.servicearea.NoServiceAreaAvailableException;
import com.ftn.sbnz.service.exception.servicearea.ServiceAreaDoesNotExistException;
import com.ftn.sbnz.service.mapper.NotificationMapper;
import com.ftn.sbnz.service.mapper.ServiceAreaMapper;
import com.ftn.sbnz.service.user.repository.AdminRepository;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.KieBase;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.servicearea.repository.ServiceAreaRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.ftn.sbnz.service.config.WebSocketConfig.NOTIFICATION_PREFIX;

@RequiredArgsConstructor
@Service
public class ServiceAreaService implements IServiceAreaService {
    
    private final ServiceAreaRepository serviceAreaRepository;
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationMapper notificationMapper;
    private final Random random = new Random();
    private final ServiceAreaMapper serviceAreaMapper;
    private final KieSession forwardServiceareaKsession;
//    private Map<Object, FactHandle> factHandles = new HashMap<>();
    private Map<Long, FactHandle> clientHandles;
    private Map<Long, FactHandle> serviceAreaHandles;

    @PostConstruct
    private void init() {
        this.clientHandles = fillClientHandles();
        this.serviceAreaHandles = fillServiceAreaHandles();
    }

    @Override
    public void assignServiceAreaToUser(Client client) {
        ServiceArea serviceArea = selectAvailableServiceArea().orElseThrow(NoServiceAreaAvailableException::new);
        client.setServiceArea(serviceArea);
        insertOrUpdateClientFact(client);
        insertOrUpdateServiceAreaFact(serviceArea);

        testKieSessionFactsAndRules(forwardServiceareaKsession);
        forwardServiceareaKsession.setGlobal("serviceAreaService", this);
        forwardServiceareaKsession.fireAllRules();
        serviceAreaRepository.save(serviceArea);
        clientRepository.save(client);
    }

    private Optional<ServiceArea> selectAvailableServiceArea() {
        List<ServiceArea> areaList = serviceAreaRepository.findAvailableServiceAreasWithLessThan50PercentCapacity();
        if (areaList.isEmpty()) {
            areaList = serviceAreaRepository.findAvailableServiceAreasWithAvailableCapacity();
            if (areaList.isEmpty()) {
                return Optional.empty();
            }
        }
        return Optional.ofNullable(areaList.get(random.nextInt(areaList.size())));
    }

    @Override
    public void notifyUsersAboutUnavailableServiceArea(ServiceArea serviceArea) {
        NotificationDTO notificationDTO = notificationMapper.mapToServiceAreaUnavailableNotification(LocalDateTime.now());
        List<Client> clients = clientRepository.findClientsByServiceAreaId(serviceArea.getId());
        for (Client client : clients) {
            this.simpMessagingTemplate.convertAndSend(
                    NOTIFICATION_PREFIX + "/" + client.getUsername(),
                    notificationDTO);
        }
    }

    @Override
    public void notifyClientAboutServiceAreaChange(Client client) {
        NotificationDTO notificationDTO = notificationMapper.mapToServiceAreaChanged(LocalDateTime.now(), client.getServiceArea());
        this.simpMessagingTemplate.convertAndSend(
                    NOTIFICATION_PREFIX + "/" + client.getUsername(),
                    notificationDTO);
    }

    @Override
    public void notifyAdminAboutUnavailableServiceAreas() {
        NotificationDTO notificationDTO = notificationMapper.mapToMoreThanPercentAreasUnavailableNotification(LocalDateTime.now(), 10);
        for (Admin admin : adminRepository.findAll()) {
            this.simpMessagingTemplate.convertAndSend(
                    NOTIFICATION_PREFIX + "/" + admin.getUsername(),
                    notificationDTO);
        }
    }

    @Override
    public ServiceAreaDTO setServiceAreaAvailability(Long serviceAreaId, boolean availability) {
        ServiceArea serviceArea = serviceAreaRepository.findById(serviceAreaId).orElseThrow(
                () -> new ServiceAreaDoesNotExistException(serviceAreaId)
        );
        serviceArea.setAvailableFlag(availability);
        if (availability) {
            serviceArea.setLastUnavailableTimestamp(null);
        }
        serviceAreaRepository.save(serviceArea);
        insertOrUpdateServiceAreaFact(serviceArea);

        forwardServiceareaKsession.setGlobal("serviceAreaService", this);
        testKieSessionFactsAndRules(forwardServiceareaKsession);
        forwardServiceareaKsession.fireAllRules();
        serviceAreaRepository.save(serviceArea);

        return serviceAreaMapper.mapAreaToDTO(serviceArea);
    }

    @Override
    public void toggleActiveFlagForBackupServiceAreas(boolean active) {
        List<ServiceArea> backupAreas = serviceAreaRepository.findServiceAreasByBackupFlag(true);
        for (ServiceArea serviceArea : backupAreas) {
            serviceArea.setActiveFlag(active);
            serviceArea.setAvailableFlag(active);
            if (active == false) {
                serviceArea.setLastUnavailableTimestamp(null);
            }
        }
        serviceAreaRepository.saveAll(backupAreas);
        for (ServiceArea area : backupAreas) {
            insertOrUpdateServiceAreaFact(area);
            forwardServiceareaKsession.setGlobal("serviceAreaService", this);
            forwardServiceareaKsession.fireAllRules();
        }
    }

    @Override
    public List<ServiceAreaDTO> getAllServiceAreas() {
        return serviceAreaMapper.mapAreasToDTOs(serviceAreaRepository.findAll());
    }

    @Override
    public void moveClientsToAvailableServiceAreasWithCapacityBelow90Percent(ServiceArea serviceArea) {

        List<Client> clients = clientRepository.findClientsByServiceAreaId(serviceArea.getId());
        for (Client client : clients) {
            ServiceArea newServiceArea = selectAvailableServiceArea().orElseThrow(NoServiceAreaAvailableException::new);
            client.setPreviousServiceArea(null);
            serviceArea.decrementCurrentCapacity();
            client.setServiceArea(newServiceArea);
            clientRepository.save(client);
            serviceAreaRepository.save(serviceArea);
            insertOrUpdateClientFact(client);
            forwardServiceareaKsession.setGlobal("serviceAreaService", this);
            forwardServiceareaKsession.fireAllRules();
        }

    }

    @Scheduled(fixedDelay = 60 * 1000) // 1 minute
    public void updateHandleForServiceAreaAvailability() {
        try {
            System.out.println("Scheduled task for service area executed at " + LocalDateTime.now());
            for (ServiceArea area : serviceAreaRepository.findServiceAreasByAvailableFlag(false)) {
                insertOrUpdateServiceAreaFact(area);
                System.out.println("Service area is unavailable, from " + area.getLastUnavailableTimestamp());
                testKieSessionFactsAndRules(forwardServiceareaKsession);
                forwardServiceareaKsession.setGlobal("serviceAreaService", this);
                forwardServiceareaKsession.fireAllRules();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testKieSessionFactsAndRules(KieSession kieSession) {

        // Testing WM facts
        Collection<FactHandle> factHandles = kieSession.getFactHandles();
        for (FactHandle handle : factHandles) {
            Object fact = kieSession.getObject(handle);
            System.out.println("Fact in WM: " + fact);
        }
        // Testing KieBase rules loading
        KieBase kieBase = kieSession.getKieBase();
        Collection<Rule> rules = kieBase.getKiePackages().stream()
                .flatMap(kiePackage -> kiePackage.getRules().stream())
                .collect(Collectors.toList());
        for (Rule rule : rules) {
            System.out.println("Rule: " + rule.getName());
        }

    }

//    private void insertOrUpdateFact(Object object) {
//        FactHandle handle = factHandles.get(object);
//        if (handle == null) {
//            handle = forwardServiceareaKsession.insert(object);
//            factHandles.put(object, handle);
//        } else {
//            forwardServiceareaKsession.update(handle, object);
//        }
//    }
//
//    private void deleteFact(Object object) {
//        FactHandle handle = factHandles.get(object);
//        if (handle != null) {
//            forwardServiceareaKsession.delete(handle);
//            factHandles.remove(object);
//        }
//    }

    private Map<Long, FactHandle> fillClientHandles() {
        List<Client> clients = clientRepository.findAll();
        Map<Long, FactHandle> clientMap = new HashMap<>();
        for (Client client : clients) {
            FactHandle handle = forwardServiceareaKsession.insert(client);
            clientMap.put(client.getId(), handle);
        }
        return clientMap;
    }

    private Map<Long, FactHandle> fillServiceAreaHandles() {
        List<ServiceArea> serviceAreas = serviceAreaRepository.findAll();
        Map<Long, FactHandle> serviceAreaMap = new HashMap<>();
        for (ServiceArea area : serviceAreas) {
            FactHandle handle = forwardServiceareaKsession.insert(area);
            serviceAreaMap.put(area.getId(), handle);
        }
        return serviceAreaMap;
    }

    private void insertOrUpdateClientFact(Client client) {
        FactHandle handle = clientHandles.get(client.getId());
        if (handle == null) {
            handle = forwardServiceareaKsession.insert(client);
            clientHandles.put(client.getId(), handle);
        } else {
            forwardServiceareaKsession.update(handle, client);
        }
    }

    private void insertOrUpdateServiceAreaFact(ServiceArea serviceArea) {
        FactHandle handle = serviceAreaHandles.get(serviceArea.getId());
        if (handle == null) {
            handle = forwardServiceareaKsession.insert(serviceArea);
            serviceAreaHandles.put(serviceArea.getId(), handle);
        } else {
            forwardServiceareaKsession.update(handle, serviceArea);
        }
    }
}
