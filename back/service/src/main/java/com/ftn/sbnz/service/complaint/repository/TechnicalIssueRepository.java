package com.ftn.sbnz.service.complaint.repository;

import com.ftn.sbnz.model.complaint.TechnicalIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalIssueRepository extends JpaRepository<TechnicalIssue, Long> {
}
