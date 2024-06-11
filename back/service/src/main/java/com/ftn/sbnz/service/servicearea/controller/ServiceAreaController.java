package com.ftn.sbnz.service.servicearea.controller;

import com.ftn.sbnz.model.servicearea.IServiceAreaService;
import com.ftn.sbnz.model.servicearea.ServiceAreaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/servicearea")
@Validated
public class ServiceAreaController {
    
    @Autowired
    private IServiceAreaService serviceAreaService;

    @GetMapping
    ResponseEntity<List<ServiceAreaDTO>> getAllServiceAreas() {
        return new ResponseEntity<>(serviceAreaService.getAllServiceAreas(), HttpStatus.OK);
    }

    @PutMapping("/setavailable")
    ResponseEntity<ServiceAreaDTO> setUnavailable(@RequestParam Long serviceAreaId, @RequestParam boolean available) {
        return new ResponseEntity<>( serviceAreaService.setServiceAreaAvailability(serviceAreaId, available), HttpStatus.OK);
    }
}
