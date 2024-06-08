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
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.complaint.repository.ComplaintRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

import static com.ftn.sbnz.service.config.DroolsConfig.testKieSessionFactsAndRules;

@RequiredArgsConstructor
@Service
public class ComplaintService implements IComplaintService {
    
    private final TechnicalIssueRepository technicalIssueRepository;
    private final ClientRepository clientRepository;
    private final KieSession bwTechnicalissueKsession;
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
        technicalIssue.setMessage("no message");
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
        solutions.put("Router activity lamp indicator not on", "Reset router by pressing power button");

        issueList.add(new RecursiveTechnicalIssue("Unavailable internet connection", "Problems with internet"));
        solutions.put("Unavailable internet connection", "Try following further solutions for diagnosing");
        issueList.add(new RecursiveTechnicalIssue("Device is not connected to router's wifi", "Unavailable internet connection"));
        solutions.put("Device is not connected to router's wifi", "Try following further solutions for diagnosing");
        issueList.add(new RecursiveTechnicalIssue("Wifi regime on device is turned off", "Device is not connected to router's wifi"));
        solutions.put("Wifi regime on device is turned off", "Turn on device's wifi regime");
        issueList.add(new RecursiveTechnicalIssue("Wifi password entered is incorrect", "Device is not connected to router's wifi"));
        solutions.put("Wifi password entered is incorrect", "Check for correct password and try entering it again");
        issueList.add(new RecursiveTechnicalIssue("Service area is unavailable", "Unavailable internet connection"));
        solutions.put("Service area is unavailable", "Check service area availability on profile page and wait for availability return");

        for (RecursiveTechnicalIssue issue : issueList) {
            bwTechnicalissueKsession.insert(issue);
        }
        bwTechnicalissueKsession.setGlobal("complaintService", this);
    }
}
