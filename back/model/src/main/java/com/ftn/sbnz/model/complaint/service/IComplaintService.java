package com.ftn.sbnz.model.complaint.service;

import com.ftn.sbnz.model.complaint.Complaint;
import com.ftn.sbnz.model.complaint.IssueAndSolution;
import com.ftn.sbnz.model.complaint.TechnicalIssue;
import com.ftn.sbnz.model.packages.Packages;

import java.util.List;

public interface IComplaintService {

    List<IssueAndSolution> diagnoseTechnicalIssue(String issueConsequence, String username);
    void addIssueAndSolutionToList(TechnicalIssue technicalIssue, String issue);
    void fillIssuesAndSolutionsMap();
    void handleComplaint(Complaint complaint);

    void handlePackageRecommendation(Packages packages);

}
