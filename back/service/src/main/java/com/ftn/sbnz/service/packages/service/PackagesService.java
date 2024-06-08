package com.ftn.sbnz.service.packages.service;

import com.ftn.sbnz.model.packages.PackageType;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.packages.dto.PackageDTO;
import com.ftn.sbnz.service.mapper.PackageMapper;
import com.ftn.sbnz.service.packages.repository.PackagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PackagesService implements IPackagesService{

    private final PackagesRepository packagesRepository;
    private final PackageMapper packageMapper;

    @Override
    public List<PackageDTO> getPackagesInOfferForType(PackageType type) {
        List<PackageDTO> packageDTOS = new ArrayList<>();
        for (Packages packages : packagesRepository.findPackagesByInOfferFlagAndPackageType(true, type)) {
            packageDTOS.add(packageMapper.mapPackageToPackageDTO(packages));
        }
        return packageDTOS;
    }
}
