package com.ftn.sbnz.service.servicearea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.service.servicearea.service.IServiceAreaService;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api/servicearea")
@Validated
public class ServiceAreaController {
    
    @Autowired
    private IServiceAreaService serviceAreaService;
}
