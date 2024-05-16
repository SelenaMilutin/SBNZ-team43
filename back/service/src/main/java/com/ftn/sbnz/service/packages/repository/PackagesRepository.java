package com.ftn.sbnz.service.packages.repository;

import com.ftn.sbnz.model.packages.Packages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackagesRepository extends JpaRepository<Packages, Long> {
}
