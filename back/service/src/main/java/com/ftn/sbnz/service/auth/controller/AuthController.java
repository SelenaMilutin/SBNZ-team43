package com.ftn.sbnz.service.auth.controller;

import com.ftn.sbnz.service.auth.dto.AuthResponseDTO;
import com.ftn.sbnz.service.auth.dto.CreateUserDTO;
import com.ftn.sbnz.service.auth.dto.LoginDTO;
import com.ftn.sbnz.service.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<Void> registerUser(@RequestBody CreateUserDTO dto) {
        authService.createClientUser(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dto) {
        return new ResponseEntity<>(authService.login(dto), HttpStatus.OK);
    }


}
