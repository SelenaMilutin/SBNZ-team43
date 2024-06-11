package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.servicearea.ServiceArea;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.service.servicearea.repository.ServiceAreaRepository;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RequiredArgsConstructor
public class DroolsConfigTest {

    private final Random random = new Random();

    @Test
    public void testKieSessionNotNull() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("forwardKsession");

        Client client = new Client();
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        ServiceArea serviceArea = new ServiceArea(1L, true, true, true, 0, 0, LocalDateTime.now(), clients);

        client.setServiceArea(serviceArea);

        kieSession.insert(client);
        kieSession.insert(serviceArea);
        Collection<FactHandle> factHandles = kieSession.getFactHandles();
        for (FactHandle handle : factHandles) {
            Object fact = kieSession.getObject(handle);
            System.out.println("Fact in WM: " + fact);
        }
        kieSession.fireAllRules();

        assertThat(serviceArea.getCurrentCapacity(), equalTo(2));

        kieSession.dispose();

    }
}