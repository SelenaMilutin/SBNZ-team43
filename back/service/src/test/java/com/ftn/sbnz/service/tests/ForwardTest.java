package com.ftn.sbnz.service.tests;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz.model.contract.*;
import com.ftn.sbnz.model.packages.PackageType;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.user.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

public class ForwardTest {
    
    @Test
    public void testContractJustExpired() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer(); 
        KieSession ksession = kContainer.newKieSession("forwardKsession");

        Client client = new Client();
        client.setName("John");
        client.setLastName("Doe");
        client.setPhone("123456789");
        client.setEmail("john.doe@example.com");
        client.setPassword("password");
        client.setAddress("123 Main Street");
        client.setBlockedFlag(false);
        client.setActiveFlag(true);
        client.setRole(Role.CLIENT); 

        Packages pckgParent = new Packages();
        pckgParent.setName("Mobi1");
        pckgParent.setPackageType(PackageType.NET);
        Packages packages = new Packages();
        packages.setName("Mobi2");
        packages.setPackageType(PackageType.NET);
        packages.setParent(packages);

        Contract contract = new Contract();
        contract.setActiveFlag(true);
        contract.setPackages(packages);
        contract.setClient(client);

        ContractProposal proposal = new ContractProposal();
        proposal.setClient(client);
        proposal.setPackages(pckgParent);
        client.setContractProposal(proposal);
        List<Contract> contracts = new ArrayList<>();
        contracts.add(contract);
        client.setContracts(contracts);

        ksession.insert(pckgParent);
        ksession.insert(packages);
        ksession.insert(contract);
        ksession.insert(proposal);
        ksession.insert(client);

        contract.setActiveFlag(false);
        ksession.fireAllRules();

        assertThat(client.getName(), equalTo("alala"));
        assertThat(client.getContracts().size(), equalTo(2));
    }
}
