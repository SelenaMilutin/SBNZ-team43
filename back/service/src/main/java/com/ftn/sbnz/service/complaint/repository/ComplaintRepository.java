package com.ftn.sbnz.service.complaint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.complaint.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    
}
