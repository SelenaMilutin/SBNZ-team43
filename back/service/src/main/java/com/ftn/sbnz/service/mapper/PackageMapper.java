package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.packages.dto.PackageDTO;
import org.springframework.stereotype.Component;

@Component
public class PackageMapper {

    public PackageDTO mapPackageToPackageDTO(Packages packages) {
        PackageDTO packageDTO = new PackageDTO();
        packageDTO.setId(packages.getId());
        packageDTO.setName(packages.getName());
        packageDTO.setInOfferFlag(packages.isInOfferFlag());
        packageDTO.setMonthlyPrice(packages.getMonthlyPrice());
        packageDTO.setPackageType(packages.getPackageType().toString());
        return packageDTO;
    }
}
