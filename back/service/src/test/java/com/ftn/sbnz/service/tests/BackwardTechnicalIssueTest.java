package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.complaint.RecursiveTechnicalIssue;
import com.ftn.sbnz.model.complaint.TechnicalIssue;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.model.user.Role;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDateTime;
import java.util.*;

import static com.ftn.sbnz.service.config.DroolsConfig.testKieSessionFactsAndRules;

public class BackwardTechnicalIssueTest {

    @Test
    public void testBackwardTechnicalIssueRuterNeRadi() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("bwTechnicalissueKsession");

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

        TechnicalIssue technicalIssue = new TechnicalIssue();
        technicalIssue.setClient(client);
        technicalIssue.setSubmitTime(LocalDateTime.now());
        technicalIssue.setConsequence("Ruter ne radi");

        List<RecursiveTechnicalIssue> issueList = new ArrayList<>();
        issueList.add(new RecursiveTechnicalIssue("Ruter ne radi", "Problem sa internetom"));

        issueList.add(new RecursiveTechnicalIssue("Ruter nema napajanje", "Ruter ne radi"));
        issueList.add(new RecursiveTechnicalIssue("Ruter nije ukljucen u struju", "Ruter nema napajanje"));
        issueList.add(new RecursiveTechnicalIssue("Zica za napajanje nije ukljucena u struju", "Ruter nema napajanje"));
        issueList.add(new RecursiveTechnicalIssue("Ruter je neispravan", "Ruter nema napajanje"));

        issueList.add(new RecursiveTechnicalIssue("Ne svetli lampica da je ruter aktivan", "Ruter ne radi"));

        Map<String, String> solutions = new HashMap<>();
        solutions.put("Ne svetli lampica da je ruter aktivan", "Resetovati ruter pritiskom na dugme");

        for (RecursiveTechnicalIssue issue : issueList) {
            ksession.insert(issue);
        }
        ksession.insert(client);
        ksession.insert(technicalIssue);
        ksession.setGlobal("solutions", solutions);
        testKieSessionFactsAndRules(ksession);
        ksession.fireAllRules();
        System.out.println(technicalIssue);
        ksession.fireAllRules();
    }

    @Test
    public void testBackwardTechnicalIssueRuterNemaNapajanje() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("bwTechnicalissueKsession");

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

        TechnicalIssue technicalIssue = new TechnicalIssue();
        technicalIssue.setClient(client);
        technicalIssue.setSubmitTime(LocalDateTime.now());
        technicalIssue.setConsequence("Ruter nema napajanje");

        List<RecursiveTechnicalIssue> issueList = new ArrayList<>();
        issueList.add(new RecursiveTechnicalIssue("Ruter ne radi", "Problem sa internetom"));

        issueList.add(new RecursiveTechnicalIssue("Ruter nema napajanje", "Ruter ne radi"));
        issueList.add(new RecursiveTechnicalIssue("Ruter nije ukljucen u struju", "Ruter nema napajanje"));
        issueList.add(new RecursiveTechnicalIssue("Zica za napajanje nije ukljucena u struju", "Ruter nema napajanje"));
        issueList.add(new RecursiveTechnicalIssue("Ruter je neispravan", "Ruter nema napajanje"));

        issueList.add(new RecursiveTechnicalIssue("Ne svetli lampica da je ruter aktivan", "Ruter ne radi"));

        for (RecursiveTechnicalIssue issue : issueList) {
            ksession.insert(issue);
        }
        ksession.insert(client);
        ksession.insert(technicalIssue);
        testKieSessionFactsAndRules(ksession);
        ksession.fireAllRules();
    }
}
