package com.ftn.sbnz.service.contract.sheduler;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.events.NewContractCreation;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.service.contract.repository.ContractRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class ContractScheduler {

    @Autowired
    ContractRepository contractRepository;

//    @Autowired
//    KieContainer kieContainer;

    @Scheduled(fixedRate = 60000) // na min
    public void CheckForExpirations() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("forwardKsession");


        LocalDateTime from = LocalDateTime.now().minusMonths(1);
        LocalDateTime to = LocalDateTime.now();
        ArrayList<Contract> contracts = contractRepository.findByExpirationDateBetween(from, to);
        for (Contract c: contracts)
            kieSession.insert(c);

        LocalDateTime timeBoundry = LocalDateTime.now().minusDays(14);
        Collection<NewContractCreation> contractCreations = new ArrayList<>();
        kieSession.setGlobal("contractCreations", contractCreations);
        kieSession.setGlobal("timeBoundry", timeBoundry);
        kieSession.fireAllRules();

        for (NewContractCreation creation: contractCreations)
        {
            
        }


    }
}
