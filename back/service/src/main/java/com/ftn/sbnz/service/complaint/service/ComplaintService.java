package com.ftn.sbnz.service.complaint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.complaint.repository.ComplaintRepository;

@Service
public class ComplaintService implements IComplaintService {
    
    @Autowired
    private ComplaintRepository complaintRepository;

}
