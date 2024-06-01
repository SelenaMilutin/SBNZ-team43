package com.ftn.sbnz.service.tests;

import com.ftn.sbnz.kjar.KjarApplication;
import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.packages.PackageType;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.model.user.Discount;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.time.SessionPseudoClock;
import org.kie.internal.utils.KieHelper;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

public class DiscountTest {

    @Test
    public void testSimpleTemplateWithSpreadsheet2(){
        InputStream template = KjarApplication.class.getResourceAsStream("/rules/templates/discount.drt");
        InputStream data = KjarApplication.class.getResourceAsStream("/rules/templates/discount-data.xls");

        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drl = converter.compile(data, template, 3, 2);

        System.out.println(drl);

        KieSession ksession = this.createKieSessionFromDRL(drl);

        this.doTest(ksession);
    }
    private void doTest(KieSession ksession){
//        KieServices ks = KieServices.Factory.get();
//        KieContainer kContainer = ks.getKieClasspathContainer();
//        KieSession ksessionCommonRule = kContainer.newKieSession("cepKsession");
//        SessionPseudoClock clock = ksessionCommonRule.getSessionClock();
        Client client = new Client(false, null, null, null, null, null);
        client.setActiveFlag(true);
        Contract contract1 = new Contract(0L, LocalDateTime.now().minusMonths(12), LocalDateTime.now().plusMonths(2), true, client, new Packages(0L, null, "aaa", 10.0, PackageType.CABLE, true), 0.0);
        Contract contract2 = new Contract(1L, LocalDateTime.now().minusMonths(12), LocalDateTime.now().plusMonths(2), true, client, new Packages(0L, null, "aaa", 10.0, PackageType.CABLE, true), 0.0);
        Contract contract3 = new Contract(2L, LocalDateTime.now().minusMonths(12), LocalDateTime.now().plusMonths(2), true, client, new Packages(0L, null, "aaa", 10.0, PackageType.CABLE, true), 0.0);
        Contract contract4 = new Contract(3L, LocalDateTime.now().minusMonths(12), LocalDateTime.now().plusMonths(2), true, client, new Packages(0L, null, "aaa", 10.0, PackageType.CABLE, true), 0.0);
        Contract contract5 = new Contract(3L, LocalDateTime.now().minusMonths(12), LocalDateTime.now().plusMonths(2), true, client, new Packages(0L, null, "aaa", 10.0, PackageType.CABLE, true), 0.0);
        Contract contract6 = new Contract(3L, LocalDateTime.now().minusMonths(12), LocalDateTime.now().plusMonths(2), true, client, new Packages(0L, null, "aaa", 10.0, PackageType.CABLE, true), 0.0);
        Discount dis = new Discount(0L, client, 0.1);
//        Customer customer1 = new Customer(1L, 19);
//        Customer customer2 = new Customer(2L, 27);
//        Customer customer3 = new Customer(3L, 32);
//        Customer customer4 = new Customer(4L, 60);
//
//        ksessionCommonRule.insert(contract1);
//        ksessionCommonRule.insert(contract2);
//        ksessionCommonRule.insert(contract3);
//        ksessionCommonRule.insert(contract4);
        ksession.insert(dis);
        ksession.insert(contract1);
        ksession.insert(contract2);
        ksession.insert(contract3);

        ksession.fireAllRules();
        ksession.insert(contract4);
        ksession.fireAllRules();
//
        ksession.insert(contract5);
        ksession.fireAllRules();

//        ksession.insert(contract6);
//        ksession.fireAllRules();

        //        ksessionCommonRule.fireAllRules();
//
//        assertThat(customer1.getCategory(), is(Customer.Category.NA));
//        assertThat(customer2.getCategory(), is(Customer.Category.BRONZE));
//        assertThat(customer3.getCategory(), is(Customer.Category.SILVER));
//        assertThat(customer4.getCategory(), is(Customer.Category.GOLD));
    }

    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }
}
