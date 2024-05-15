package com.ftn.sbnz.service.user.controller;

import com.ftn.sbnz.service.user.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api/user")
@Validated
public class AppUserController {

    @Autowired
    private IAppUserService appUserService;
}
