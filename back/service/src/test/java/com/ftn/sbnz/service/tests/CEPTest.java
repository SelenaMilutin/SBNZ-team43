//package com.ftn.sbnz.service.tests;
//
//import org.junit.Test;
//import org.kie.api.KieServices;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.drools.core.time.SessionPseudoClock;
//
//import com.ftn.sbnz.model.complaint.*;
//import com.ftn.sbnz.model.packages.*;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.junit.Assert.assertThat;
//
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
//public class CEPTest {
//
//
//    @Test
//    public void test() {
//        KieServices ks = KieServices.Factory.get();
//        KieContainer kContainer = ks.getKieClasspathContainer();
//        KieSession ksession = kContainer.newKieSession("cepKsession");
//
//
//        SessionPseudoClock clock = ksession.getSessionClock();
//
//        Packages packages = new Packages();
//        packages.setId(1L);
//        for (int i = 0; i <= 10; i++) {
//            PackageComplaint complaint = new PackageComplaint();
//            complaint.setPackages(packages);
//            complaint.setSubmitTime(new Date());
//            ksession.insert(complaint);
//            clock.advanceTime(1, TimeUnit.HOURS);
//        }
//
//        ksession.fireAllRules();
//    }
//
//    @Test
//    public void test2() {
//        KieServices ks = KieServices.Factory.get();
//        KieContainer kContainer = ks.getKieClasspathContainer();
//        KieSession ksession = kContainer.newKieSession("cepKsession");
//
//
//        SessionPseudoClock clock = ksession.getSessionClock();
//
//        Packages packages = new Packages();
//        packages.setId(1L);
//        packages.setInOfferFlag(true);
//        ksession.insert(packages);
//        for (int i = 0; i <= 50; i++) {
//            PackageComplaint complaint = new PackageComplaint();
//            complaint.setPackages(packages);
//            complaint.setSubmitTime(new Date());
//            ksession.insert(complaint);
//            clock.advanceTime(15, TimeUnit.MINUTES);
//        }
//
//        ksession.fireAllRules();
//        assertThat(packages.isInOfferFlag(), equalTo(false));
//    }
//
//}
