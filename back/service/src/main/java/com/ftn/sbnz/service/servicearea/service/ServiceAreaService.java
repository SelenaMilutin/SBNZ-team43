package com.ftn.sbnz.service.servicearea.service;

import com.ftn.sbnz.model.servicearea.IServiceAreaService;
import com.ftn.sbnz.model.servicearea.ServiceArea;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.service.dto.NotificationDTO;
import com.ftn.sbnz.service.exception.servicearea.NoServiceAreaAvailableException;
import com.ftn.sbnz.service.exception.servicearea.ServiceAreaDoesNotExistException;
import com.ftn.sbnz.service.mapper.NotificationMapper;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.KieBase;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.servicearea.repository.ServiceAreaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static com.ftn.sbnz.service.config.WebSocketConfig.NOTIFICATION_PREFIX;

@RequiredArgsConstructor
@Service
public class ServiceAreaService implements IServiceAreaService {
    
    private final ServiceAreaRepository serviceAreaRepository;
    private final ClientRepository clientRepository;
    private final KieContainer kieContainer;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationMapper notificationMapper;
    private final Random random = new Random();

    @Override
    public void assignServiceAreaToUser(Client client) {
        ServiceArea serviceArea = selectAvailableServiceArea().orElseThrow(NoServiceAreaAvailableException::new);
        client.setServiceArea(serviceArea);

        KieSession kieSession = kieContainer.newKieSession("forwardServiceareaKsession");
        kieSession.insert(client);
        kieSession.insert(serviceArea);
        testKieSessionFactsAndRules(kieSession);
        kieSession.fireAllRules();
        serviceAreaRepository.save(serviceArea);
        clientRepository.save(client);
        kieSession.dispose();
    }

    private Optional<ServiceArea> selectAvailableServiceArea() {
        List<ServiceArea> areaList = serviceAreaRepository.findServiceAreasWithLessThan50PercentCapacity();
        if (areaList.isEmpty()) {
            areaList = serviceAreaRepository.findServiceAreasWithAvailableCapacity();
            if (areaList.isEmpty()) {
                return Optional.empty();
            }
        }
        return Optional.ofNullable(areaList.get(random.nextInt(areaList.size())));
    }

    @Override
    public void notifyUsersAboutUnavailableServiceArea(ServiceArea serviceArea) {
        NotificationDTO notificationDTO = notificationMapper.mapToServiceAreaUnavailableNotification(LocalDateTime.now());
        for (Client client : serviceArea.getClients()) {
            this.simpMessagingTemplate.convertAndSend(
                    NOTIFICATION_PREFIX + "/" + client.getUsername(),
                    notificationDTO);
        }
    }

    @Override
    public void setServiceAreaAvailability(Long serviceAreaId, boolean availability) {
        ServiceArea serviceArea = serviceAreaRepository.findById(serviceAreaId).orElseThrow(
                () -> new ServiceAreaDoesNotExistException(serviceAreaId)
        );
        serviceArea.setAvailableFlag(availability);

        KieSession kieSession = kieContainer.newKieSession("forwardServiceareaKsession");
        kieSession.setGlobal("serviceAreaService", this);
        kieSession.insert(serviceArea);
        testKieSessionFactsAndRules(kieSession);
        kieSession.fireAllRules();
        serviceAreaRepository.save(serviceArea);
        kieSession.dispose();
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
}
