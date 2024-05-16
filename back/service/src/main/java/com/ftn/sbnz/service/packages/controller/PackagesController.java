package com.ftn.sbnz.service.packages.controller;

import com.ftn.sbnz.service.packages.service.IPackagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api/packages")
@Validated
public class PackagesController {

    @Autowired
    IPackagesService packagesService;
}
