package com.ftn.sbnz.service.complaint.Controller;

import com.ftn.sbnz.model.complaint.Complaint;
import com.ftn.sbnz.model.complaint.dto.ComplaintDTO;
import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.service.IContractService;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.user.AppUser;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.model.user.IAppUserService;
import com.ftn.sbnz.service.auth.service.JWTService;
import com.ftn.sbnz.service.packages.service.IPackagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ftn.sbnz.service.complaint.service.IComplaintService;

import java.time.LocalDateTime;
import java.util.Date;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api/complaint")
@Validated
@RequiredArgsConstructor
public class ComplaintController {

    private final IComplaintService complaintService;
    private final IPackagesService packagesService;
    private final JWTService jwtService;
    private final IAppUserService appUserService;

    @PostMapping()
    public ResponseEntity<Void> handleComplaint(@RequestBody ComplaintDTO complaintRequest) {
        AppUser user = (AppUser) jwtService.getAuthenticatedUser();
//        if (user == null) {
//            throw new AuthenticationCredentialsNotFoundException("User is not authenticated for creating contract");
//        }
        user = appUserService.findById(1L);
        Packages packages = packagesService.findById((long) complaintRequest.getPackageId());
        Complaint complaint = new Complaint(0L, complaintRequest.getComplaint(), complaintRequest.getRecommendation(), (Client) user, packages, new Date());
        complaintService.handleComplaint(complaint);
        return ResponseEntity.ok().build();
    }
}
