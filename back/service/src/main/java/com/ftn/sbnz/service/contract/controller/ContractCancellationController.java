package com.ftn.sbnz.service.contract.controller;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.dto.CancellationDTO;
import com.ftn.sbnz.model.contract.service.IContractService;
import com.ftn.sbnz.model.user.AppUser;
import com.ftn.sbnz.service.auth.service.JWTService;
import com.ftn.sbnz.service.contract.service.IContractCancellationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost"})
@RestController
@RequestMapping("/api/cancellation")
@Validated
public class ContractCancellationController {
    private final IContractCancellationService cancellationService;
    private final IContractService contractService;
    private final JWTService jwtService;

    @PostMapping(path = "/{id}")
    ResponseEntity<CancellationDTO> cancelContract(@PathVariable int id) {
        AppUser user = (AppUser) jwtService.getAuthenticatedUser();
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated for creating contract");
        }
        Contract contract = contractService.findById((long) id);
        if (contract.getClient().getId() != user.getId())
            throw  new RuntimeException("Client is not authorised to cancel this contract");
        return new ResponseEntity<>(cancellationService.cancelContract(contract), HttpStatus.CREATED);
    }
}
