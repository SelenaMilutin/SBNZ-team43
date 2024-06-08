package com.ftn.sbnz.service.complaint.Controller;

import com.ftn.sbnz.model.complaint.IssueAndSolution;
import com.ftn.sbnz.model.user.AppUser;
import com.ftn.sbnz.service.auth.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ftn.sbnz.model.complaint.service.IComplaintService;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost"})
@RestController
@RequestMapping("/api/complaints")
@Validated
public class ComplaintController {
    
    private final IComplaintService complaintService;
    private final JWTService jwtService;

    @GetMapping("/technicalissues")
    ResponseEntity<List<IssueAndSolution>> findIssueAndSolution(@RequestParam String issueConsequence) {

//        AppUser user = (AppUser) jwtService.getAuthenticatedUser();
//        if (user == null) {
//            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
//        }

//        return new ResponseEntity<>(complaintService.diagnoseTechnicalIssue(issueConsequence, user.getUsername()), HttpStatus.OK);
        return new ResponseEntity<>(complaintService.diagnoseTechnicalIssue(issueConsequence, "a"), HttpStatus.OK);
    }

}
