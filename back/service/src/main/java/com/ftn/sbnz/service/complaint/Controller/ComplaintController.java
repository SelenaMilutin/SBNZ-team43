package com.ftn.sbnz.service.complaint.Controller;

import com.ftn.sbnz.model.complaint.IssueAndSolution;
import com.ftn.sbnz.model.user.AppUser;
import com.ftn.sbnz.service.auth.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ftn.sbnz.model.complaint.Complaint;
import com.ftn.sbnz.model.complaint.dto.ComplaintDTO;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.model.user.IAppUserService;
import com.ftn.sbnz.service.packages.service.IPackagesService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Date;
import com.ftn.sbnz.model.complaint.service.IComplaintService;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost"})
@RestController
@RequestMapping("/api/complaints")
@Validated
@RequiredArgsConstructor
public class ComplaintController {

    private final IComplaintService complaintService;
    private final IPackagesService packagesService;
    private final IAppUserService appUserService;
    private final JWTService jwtService;

    @GetMapping("/technicalissues")
    ResponseEntity<List<IssueAndSolution>> findIssueAndSolution(@RequestParam String issueConsequence) {

        AppUser user = (AppUser) jwtService.getAuthenticatedUser();
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
        }

//        return new ResponseEntity<>(complaintService.diagnoseTechnicalIssue(issueConsequence, user.getUsername()), HttpStatus.OK);
        return new ResponseEntity<>(complaintService.diagnoseTechnicalIssue(issueConsequence, "a"), HttpStatus.OK);
    }



    @PostMapping()
    public ResponseEntity<Void> handleComplaint(@RequestBody ComplaintDTO complaintRequest) {
        AppUser user = (AppUser) jwtService.getAuthenticatedUser();
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated for creating contract");
        }
//        user = appUserService.findById(1L);
        Packages packages = packagesService.findById((long) complaintRequest.getPackageId());
        Complaint complaint = new Complaint(0L, complaintRequest.getComplaint(), complaintRequest.getRecommendation(), (Client) user, packages, new Date(), false);
        complaintService.handleComplaint(complaint);
        return ResponseEntity.ok().build();
    }
}
