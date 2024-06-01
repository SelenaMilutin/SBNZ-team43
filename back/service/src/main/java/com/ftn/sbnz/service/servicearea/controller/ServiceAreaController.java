package com.ftn.sbnz.service.servicearea.controller;

import com.ftn.sbnz.model.servicearea.IServiceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/servicearea")
@Validated
public class ServiceAreaController {
    
    @Autowired
    private IServiceAreaService serviceAreaService;

    @PutMapping("/setavailable")
    ResponseEntity<Void> setUnavailable(@RequestParam Long serviceAreaId, @RequestParam boolean available) {
        serviceAreaService.setServiceAreaAvailability(serviceAreaId, available);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
