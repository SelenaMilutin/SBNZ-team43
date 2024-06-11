package com.ftn.sbnz.service.user.controller;

import com.ftn.sbnz.model.user.UserProfileDTO;
import com.ftn.sbnz.model.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api/user")
@Validated
public class AppUserController {

    @Autowired
    private IAppUserService appUserService;

    @GetMapping
    ResponseEntity<UserProfileDTO> getProfile(@RequestParam String username) {
        return new ResponseEntity<>(appUserService.getUserProfile(username), HttpStatus.OK);
    }
}
