package com.ftn.sbnz.service.complaint.service;

import com.ftn.sbnz.model.complaint.IssueAndSolution;
import com.ftn.sbnz.model.complaint.RecursiveTechnicalIssue;
import com.ftn.sbnz.model.complaint.TechnicalIssue;
import com.ftn.sbnz.model.complaint.service.IComplaintService;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.service.complaint.repository.TechnicalIssueRepository;
import com.ftn.sbnz.service.exception.user.UsernameNotFoundException;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import com.ftn.sbnz.model.complaint.Complaint;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.service.config.DroolsConfig;
import com.ftn.sbnz.service.packages.repository.PackagesRepository;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.complaint.repository.ComplaintRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

import static com.ftn.sbnz.service.config.DroolsConfig.testKieSessionFactsAndRules;


@Service
@RequiredArgsConstructor
public class ComplaintService implements IComplaintService {
    
    private final TechnicalIssueRepository technicalIssueRepository;
    private final ClientRepository clientRepository;
    private final ComplaintRepository complaintRepository;
    private final PackagesRepository packagesRepository;
    private final KieSession bwTechnicalissueKsession;
    private final DroolsConfig config;
    private Map<String, String> solutions;

    @PostConstruct
    private void init() {
        fillIssuesAndSolutionsMap();
    }

    @Override
    public List<IssueAndSolution> diagnoseTechnicalIssue(String issueConsequence, String username) {
        Client client = clientRepository.findByEmail(username).orElseThrow(
                () -> {return new UsernameNotFoundException(username); }
        );
        TechnicalIssue technicalIssue = new TechnicalIssue();
        technicalIssue.setSubmitTime(LocalDateTime.now());
        technicalIssue.setConsequence(issueConsequence);
        technicalIssue.setClient(client);
        TechnicalIssue saved = technicalIssueRepository.save(technicalIssue);

        FactHandle technicalIssueHandle = bwTechnicalissueKsession.insert(saved);
        testKieSessionFactsAndRules(bwTechnicalissueKsession);
        bwTechnicalissueKsession.fireAllRules();
        bwTechnicalissueKsession.delete(technicalIssueHandle);
        System.out.println(technicalIssue);
        return technicalIssue.getIssueAndSolutions();
    }

    @Override
    public void addIssueAndSolutionToList(TechnicalIssue technicalIssue, String issue) {
        String solution = solutions.getOrDefault(issue, "No currently available solution, try calling an operator for additional help.");
        technicalIssue.addIssueAndSolution(issue, solution);
        System.out.println("Found solution " + solution + " for issue " + issue);
        technicalIssueRepository.save(technicalIssue);
    }

    @Override
    public void fillIssuesAndSolutionsMap() {
        List<RecursiveTechnicalIssue> issueList = new ArrayList<>();
        solutions = new HashMap<>();

        issueList.add(new RecursiveTechnicalIssue("Router not working", "Problems with internet"));
        solutions.put("Router not working", "Try following further solutions for diagnosing");
        issueList.add(new RecursiveTechnicalIssue("Router not powered on", "Router not working"));
        solutions.put("Router not powered on", "Turn router on by pressing power button");
        issueList.add(new RecursiveTechnicalIssue("Router not plugged to power outlet", "Router not powered on"));
        solutions.put("Router not plugged to power outlet", "Plug router into power outlet");
        issueList.add(new RecursiveTechnicalIssue("Router power cable wire not plugged into router", "Router not powered on"));
        solutions.put("Router power cable wire not plugged into router", "Plug router power wire into router");
        issueList.add(new RecursiveTechnicalIssue("Router is faulty", "Router not powered on"));
        solutions.put("Router is faulty", "Contact operater for router change");

        issueList.add(new RecursiveTechnicalIssue("Router activity lamp indicator not on", "Router not working"));
        issueList.add(new RecursiveTechnicalIssue("Router has overheated", "Router activity lamp indicator not on"));
        issueList.add(new RecursiveTechnicalIssue("Router has encountered a bug", "Router activity lamp indicator not on"));
        solutions.put("Router activity lamp indicator not on", "Reset router by pressing power button");
        solutions.put("Router has overheated", "Reset router by pressing power button");
        solutions.put("Router has encountered a bug", "Reset router by pressing power button");

        issueList.add(new RecursiveTechnicalIssue("Unavailable internet connection", "Problems with internet"));
        solutions.put("Unavailable internet connection", "Try following further solutions for diagnosing");
        issueList.add(new RecursiveTechnicalIssue("Device is not connected to router's wifi", "Unavailable internet connection"));
        solutions.put("Device is not connected to router's wifi", "Try following further solutions for diagnosing");
        issueList.add(new RecursiveTechnicalIssue("Wifi regime on device is turned off", "Device is not connected to router's wifi"));
        solutions.put("Wifi regime on device is turned off", "Turn on device's wifi regime");
        issueList.add(new RecursiveTechnicalIssue("Wifi password entered is incorrect", "Device is not connected to router's wifi"));
        solutions.put("Wifi password entered is incorrect", "Check for correct password and try entering it again");
        issueList.add(new RecursiveTechnicalIssue("Can't establish connection with service area", "Unavailable internet connection"));
        solutions.put("Can't establish connection with service area", "Check service area availability on profile page and wait for availability return");
        issueList.add(new RecursiveTechnicalIssue("Service area is unavailable", "Can't establish connection with service area"));
        issueList.add(new RecursiveTechnicalIssue("Account may be blocked", "Can't establish connection with service area"));
        solutions.put("Service area is unavailable", "Check service area availability on profile page and wait for availability return");
        solutions.put("Account may be blocked", "Check account blocked status on profile page, or contact operator for more information");


        issueList.add(new RecursiveTechnicalIssue("Low signal range", "Service and signal range problems"));
        solutions.put("Low signal range", "Try following further solutions for diagnosing");
        issueList.add(new RecursiveTechnicalIssue("You are possibly within great distance of signal zone", "Low signal range"));
        solutions.put("You are possibly within great distance of signal zone", "Check out our signal zone page and find out where you can go to get into a zone with reception");

        issueList.add(new RecursiveTechnicalIssue("No signal reception", "Service and signal range problems"));
        solutions.put("No signal reception", "Try following further solutions for diagnosing");
        issueList.add(new RecursiveTechnicalIssue("Service area's unavailable", "No signal reception"));
        solutions.put("Service area's unavailable", "Check service area availability on profile page and wait for availability return");
        issueList.add(new RecursiveTechnicalIssue("Your device is in a zone without reception", "No signal reception"));
        solutions.put("Your device is in a zone without reception", "Check out our signal zone page and find out where you can go to get into a zone with reception");

        issueList.add(new RecursiveTechnicalIssue("Phone can't receive reception", "Service and signal range problems"));
        solutions.put("Phone can't receive reception", "Try following further solutions for diagnosing");
        issueList.add(new RecursiveTechnicalIssue("Phone is in Flight regime/Airplane mode", "Phone can't receive reception"));
        solutions.put("Phone is in Flight regime/Airplane mode", "Switch the regime off and restart phone");
        issueList.add(new RecursiveTechnicalIssue("SIM card is not properly inserted", "Phone can't receive reception"));
        solutions.put("SIM card is not properly inserted", "After turning phone off, carefully remove and re-insert SIM, and turn phone on again");


        for (RecursiveTechnicalIssue issue : issueList) {
            bwTechnicalissueKsession.insert(issue);
        }
        bwTechnicalissueKsession.setGlobal("complaintService", this);
    }

    @Override
    public void handleComplaint(Complaint complaint) {
        KieSession kieSession = config.cepKsession();
        Packages recommendedPackage = new Packages();
        kieSession.setGlobal("recomend", recommendedPackage);
        List<Packages> packages = packagesRepository.findAll();
        for (Packages c: packages) {
            kieSession.insert(c);
        }
        kieSession.fireAllRules();
        kieSession.insert(complaint);
        kieSession.fireAllRules();
        recommendedPackage = (Packages) kieSession.getGlobal("recomend");
        if (recommendedPackage != null) {
            System.out.println("jeje");
        }
        else {
            System.out.println("aaaaaaaa");
        }
        complaintRepository.save(complaint);
    }
}
