package com.ftn.sbnz.service.packages.service;

import com.ftn.sbnz.model.packages.PackageType;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.packages.dto.PackageDTO;

import java.util.List;

public interface IPackagesService {

    List<PackageDTO> getPackagesInOfferForType(PackageType type);

    Packages findById(long packageId);
}
