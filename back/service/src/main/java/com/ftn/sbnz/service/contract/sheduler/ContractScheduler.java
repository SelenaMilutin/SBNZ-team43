package com.ftn.sbnz.service.contract.sheduler;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.events.NewContractCreation;
import com.ftn.sbnz.model.contract.service.IContractService;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.service.contract.repository.ContractRepository;
import com.ftn.sbnz.service.contract.service.ContractService;
import com.ftn.sbnz.service.packages.repository.PackagesRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.ftn.sbnz.service.config.DroolsConfig.testKieSessionFactsAndRules;

@RequiredArgsConstructor
@Component
public class ContractScheduler {

    private final ContractRepository contractRepository;
    private final IContractService contractService;
    private final KieContainer kieContainer;

    @Scheduled(fixedRate = 120000) // na min
    public void CheckForExpirations() {
        System.out.println("Scheduled task for contract expiration executed at " + LocalDateTime.now());
        KieServices ks = KieServices.Factory.get();
        KieSession kieSession = kieContainer.newKieSession("forwardKsession");

        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusMonths(1);
        ArrayList<Contract> contracts = contractRepository.findByExpirationDateBetween(from, to);
        for (Contract c: contracts)
            kieSession.insert(c);
        testKieSessionFactsAndRules(kieSession);

        LocalDateTime timeBoundry = LocalDateTime.now().minusDays(14);
        kieSession.setGlobal("contractService", contractService);
        kieSession.setGlobal("timeBoundry", timeBoundry);

        kieSession.fireAllRules();

    }
}
