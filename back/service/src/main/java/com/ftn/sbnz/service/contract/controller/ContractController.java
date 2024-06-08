package com.ftn.sbnz.service.contract.controller;

import com.ftn.sbnz.model.contract.dto.ContractDTO;
import com.ftn.sbnz.model.contract.dto.CreateContractDTO;
import com.ftn.sbnz.model.user.AppUser;
import com.ftn.sbnz.service.auth.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ftn.sbnz.model.contract.service.IContractService;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost"})
@RestController
@RequestMapping("/api/contracts")
@Validated
public class ContractController {
    
    private final IContractService contractService;
    private final JWTService jwtService;

    @PostMapping
    ResponseEntity<ContractDTO> createContract(@Validated @RequestBody CreateContractDTO createContractDTO) {

        AppUser user = (AppUser) jwtService.getAuthenticatedUser();
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated for creating contract");
        }

        return new ResponseEntity<>(contractService.create(createContractDTO, user.getUsername()), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<ContractDTO>> getContracts() {
        AppUser user = (AppUser) jwtService.getAuthenticatedUser();
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated for creating contract");
        }

        return new ResponseEntity<>(contractService.getContractsForClient(user.getUsername()), HttpStatus.OK);
    }

}
