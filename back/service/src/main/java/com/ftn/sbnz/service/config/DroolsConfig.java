package com.ftn.sbnz.service.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.kie.api.runtime.KieSession;

import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
public class DroolsConfig {

    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(1000);
        return kContainer;
    }

    @Bean
    public KieSession forwardServiceareaKsession() {
        return kieContainer().newKieSession("forwardServiceareaKsession");
    }

    @Bean
    public KieSession forwardContractKsession() {
        return kieContainer().newKieSession("forwardContractKsession");
    }

    @Bean
    public KieSession backwardForReportsKsession() {
        return kieContainer().newKieSession("bwKsession");
    }

    @Bean
    public KieSession cepKsession() {
        return kieContainer().newKieSession("cepKsession");
    }

    public static void testKieSessionFactsAndRules(KieSession kieSession) {

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
