package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.model.servicearea.ServiceArea;
import com.ftn.sbnz.model.user.Client;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ServiceAreaTest {

    @Test
    public void testAddServiceArea() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("forwardKsession");

        List<Client> clients = new ArrayList<>();
        ServiceArea sa = new ServiceArea();
        Client c = new Client();

        c.setServiceArea(sa);
        ksession.insert(c);
        ksession.insert(sa);
        ksession.fireAllRules();

        assertThat(sa.getCurrentCapacity(), equalTo(1));
    }

    @Test
    public void testRemoveServiceArea() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("forwardKsession");

        Client c = new Client();
        List<Client> clients = new ArrayList<>();
        clients.add(c);
        ServiceArea sa = new ServiceArea(1L, true, true, true, 0, 0, LocalDateTime.now(), clients);
        c.setServiceArea(null);
        c.setPreviousServiceArea(sa);

        assertThat(c.getServiceArea(), equalTo(null));

        ksession.insert(c);
        ksession.insert(sa);
        ksession.fireAllRules();

        assertThat(sa.getCurrentCapacity(), equalTo(0));

    }

}
