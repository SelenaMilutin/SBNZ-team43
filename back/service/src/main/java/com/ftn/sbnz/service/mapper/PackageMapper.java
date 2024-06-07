package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.contract.dto.PackageDTO;
import org.springframework.stereotype.Component;

@Component
public class PackageMapper {

    public PackageDTO mapPackageToPackageDTO(Packages pkg) {
        PackageDTO packageDTO = new PackageDTO();
        packageDTO.setId(pkg.getId());
        packageDTO.setName(packageDTO.getName());
        packageDTO.setInOfferFlag(packageDTO.isInOfferFlag());
        packageDTO.setMonthlyPrice(packageDTO.getMonthlyPrice());
        packageDTO.setPackageType(packageDTO.getPackageType());
        return packageDTO;
    }
}
