package com.ftn.sbnz.service.contract.service;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.dto.ContractDTO;
import com.ftn.sbnz.model.contract.dto.CreateContractDTO;
import com.ftn.sbnz.model.contract.dto.PyChartDTO;
import com.ftn.sbnz.model.contract.events.NewContractCreation;
import com.ftn.sbnz.model.contract.service.IContractService;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.model.user.Discount;
import com.ftn.sbnz.service.config.DroolsConfig;
import com.ftn.sbnz.service.exception.packages.PackageDoesNotExistByIdException;
import com.ftn.sbnz.service.exception.user.UsernameNotFoundException;
import com.ftn.sbnz.service.mapper.ContractMapper;
import com.ftn.sbnz.service.packages.repository.PackagesRepository;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import com.ftn.sbnz.service.user.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.contract.repository.ContractRepository;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ContractService implements IContractService {

    private final ContractRepository contractRepository;
    private final PackagesRepository packagesRepository;
    private final DiscountRepository discountRepository;
    private final ContractMapper contractMapper;
    private final ContractDroolsService contractDroolsService;
    private final ClientRepository clientRepository;
    private final DroolsConfig config;
    @Override
    public ContractDTO create(CreateContractDTO createContractDTO, String username) {
        Contract contract = new Contract();
        Client client = clientRepository.findByEmail(username).orElseThrow(
                () -> {return new UsernameNotFoundException(username); }
        );
        Packages packages = packagesRepository.findById(createContractDTO.getPackageId()).orElseThrow(
                () -> {return new PackageDoesNotExistByIdException(createContractDTO.getPackageId().toString());}
        );
        contract.setPackages(packages);
        contract.setStartDate(LocalDateTime.now());
        contract.setExpirationDate(LocalDateTime.now().plusMonths(createContractDTO.getLengthInMonths()));
        contract.setActiveFlag(true);
        contract.setClient(client);
        this.applyDiscount(contract);
        Contract saved = contractRepository.save(contract);
        contractDroolsService.insertOrUpdateContractFact(saved);
        return contractMapper.mapContractToContractDTO(saved);
    }

    @Override
    public void createDiscount(Contract contract, int numberOfPreviousContracts) {
        Discount discount = new Discount();
        discount.setClient(contract.getClient());
        discount.setPercentage(numberOfPreviousContracts * 0.02);
        contractRepository.save(contract);
        discountRepository.save(discount);
        contractDroolsService.insertOrUpdateDiscountFact(discount);
    }

    @Override
    public void applyDiscount(Contract contract) {
        Optional<Discount> discount = discountRepository.findDiscountByClientId(contract.getClient().getId());
        if (discount.isPresent()) {
            contract.setDiscount(discount.get().getPercentage());
            contractDroolsService.deleteDiscountFact(discount.get());
            discountRepository.deleteById(discount.get().getId());
        }
    }

    @Override
    public List<ContractDTO> getContractsForClient(String username) {
        Client client = clientRepository.findByEmail(username).orElseThrow(
                () -> {return new UsernameNotFoundException(username); }
        );
        List<ContractDTO> contractDTOs = new ArrayList<>();
        for (Contract contract : contractRepository.findContractsByClientId(client.getId())) {
            contractDTOs.add(contractMapper.mapContractToContractDTO(contract));
        }
        return contractDTOs;
    }

    @Override
    public ArrayList<PyChartDTO> getPrepaidPostpaidDistribution() {
        KieSession kieSession = config.backwardForReportsKsession();

        List<Contract> contracts = contractRepository.findAll();
        for (Contract c: contracts) {
//            System.out.println(c.getPackages().getName());
            kieSession.insert(c);
        }

        List<Packages> packages = packagesRepository.findAll();
        for (Packages c: packages) {
//            System.out.println(c.getName());
            kieSession.insert(c);
        }

        int prepaidCount = 0;
        int postpaidCount = 0;
        kieSession.setGlobal("prepaidCount", prepaidCount);
        kieSession.setGlobal("postpaidCount", postpaidCount);
        kieSession.fireAllRules();
        ArrayList<PyChartDTO> values = new ArrayList<>();
        values.add(new PyChartDTO("Pripejd", prepaidCount));
        values.add(new PyChartDTO("Postpejd", postpaidCount));
        return values;
    }

    //    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000) // 12 hours
    @Scheduled(fixedDelay = 20 * 1000)
    public void checkContractExpirations() {
        try {
            System.out.println("Scheduled task for contract executed at " + LocalDateTime.now());
            contractDroolsService.fireAllRules(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
