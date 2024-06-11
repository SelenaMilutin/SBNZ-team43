//package com.ftn.sbnz.service.tests;
//
//import com.ftn.sbnz.kjar.KjarApplication;
//import com.ftn.sbnz.model.contract.Contract;
//import com.ftn.sbnz.model.contract.ContractCancellation;
//import com.ftn.sbnz.model.packages.PackageType;
//import com.ftn.sbnz.model.packages.Packages;
//import com.ftn.sbnz.model.user.Client;
//import org.junit.Test;
//import org.drools.decisiontable.ExternalSpreadsheetCompiler;
//import org.junit.jupiter.api.BeforeAll;
//import org.kie.api.builder.Message;
//import org.kie.api.builder.Results;
//import org.kie.api.io.ResourceType;
//import org.kie.api.runtime.KieSession;
//import org.kie.internal.utils.KieHelper;
//
//import java.io.InputStream;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//
//public class CancellationTest {
//
//    @Test
//    public void testSimpleTemplateWithSpreadsheet2(){
//        InputStream template = KjarApplication.class.getResourceAsStream("/rules/templates/cancellation.drt");
//        InputStream data = KjarApplication.class.getResourceAsStream("/rules/templates/cancellation-data.xls");
//
//        System.out.println(template==null);
//        System.out.println(data==null);
//
//        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
//        String drl = converter.compile(data, template, 3, 2);
//
//        System.out.println(drl);
//
//        KieSession ksession = this.createKieSessionFromDRL(drl);
//
//        this.doTest(ksession);
//    }
//    private void doTest(KieSession ksession){
//<<<<<<< HEAD
//        ContractCancellation contractCancellation1 = new ContractCancellation(0L, new Contract(0L, LocalDateTime.now().minusMonths(12), LocalDateTime.now().plusMonths(2), true, new Client(), new Packages(0L, null, "aaa", 10.0, PackageType.CABLE, true), 0.0, false));
//=======
//        ContractCancellation contractCancellation1 = new ContractCancellation(0L, new Contract(0L, LocalDateTime.now().minusMonths(12), LocalDateTime.now().plusMonths(2), true, new Client(), new Packages(0L, null, "aaa", 10.0, PackageType.CABLE, true), 0.0, true), LocalDateTime.now());
//>>>>>>> origin/feature/paketi
////        Customer customer1 = new Customer(1L, 19);
////        Customer customer2 = new Customer(2L, 27);
////        Customer customer3 = new Customer(3L, 32);
////        Customer customer4 = new Customer(4L, 60);
//
//        ksession.insert(contractCancellation1);
////        ksession.insert(customer2);
////        ksession.insert(customer3);
////        ksession.insert(customer4);
////
//        ksession.fireAllRules();
//
//        assertFalse(contractCancellation1.getContract().isActiveFlag());
////        assertThat(contractCancellation1.getContract().is, is(Customer.Category.NA));
////        assertThat(customer2.getCategory(), is(Customer.Category.BRONZE));
////        assertThat(customer3.getCategory(), is(Customer.Category.SILVER));
////        assertThat(customer4.getCategory(), is(Customer.Category.GOLD));
//    }
//
//    private KieSession createKieSessionFromDRL(String drl){
//        KieHelper kieHelper = new KieHelper();
//        kieHelper.addContent(drl, ResourceType.DRL);
//
//        Results results = kieHelper.verify();
//
//        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
//            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
//            for (Message message : messages) {
//                System.out.println("Error: "+message.getText());
//            }
//
//            throw new IllegalStateException("Compilation errors were found. Check the logs.");
//        }
//
//        return kieHelper.build().newKieSession();
//    }
//
//
//}
