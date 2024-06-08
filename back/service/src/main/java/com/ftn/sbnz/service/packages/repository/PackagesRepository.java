package com.ftn.sbnz.service.packages.repository;

import com.ftn.sbnz.model.packages.PackageType;
import com.ftn.sbnz.model.packages.Packages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackagesRepository extends JpaRepository<Packages, Long> {

    List<Packages> findPackagesByInOfferFlagAndPackageType(boolean inOfferFlag, PackageType packageType);

}
