package com.ftn.sbnz.service.packages.controller;

import com.ftn.sbnz.model.packages.PackageType;
import com.ftn.sbnz.model.packages.dto.PackageDTO;
import com.ftn.sbnz.service.packages.service.IPackagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost"})
@RestController
@RequestMapping("/api/packages")
@Validated
@RequiredArgsConstructor
public class PackagesController {

    private final IPackagesService packagesService;

    @GetMapping
    ResponseEntity<List<PackageDTO>> getPackagesInOfferForPackageType(@RequestParam PackageType packageType) {
        return new ResponseEntity<>(packagesService.getPackagesInOfferForType(packageType), HttpStatus.OK);
    }
}
