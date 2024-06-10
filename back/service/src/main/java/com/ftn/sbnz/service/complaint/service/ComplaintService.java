package com.ftn.sbnz.service.complaint.service;

import com.ftn.sbnz.model.complaint.Complaint;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.service.config.DroolsConfig;
import com.ftn.sbnz.service.packages.repository.PackagesRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.complaint.repository.ComplaintRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService implements IComplaintService {
    
    private final ComplaintRepository complaintRepository;
    private final DroolsConfig config;
    private final PackagesRepository packagesRepository;

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
