package com.ftn.sbnz.service.servicearea.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.servicearea.ServiceArea;

import java.util.List;

public interface ServiceAreaRepository extends JpaRepository<ServiceArea, Long> {

    @Query("SELECT sa FROM ServiceArea sa WHERE sa.currentCapacity < sa.maximumCapacity * 0.5")
    List<ServiceArea> findServiceAreasWithLessThan50PercentCapacity();

    @Query("SELECT sa FROM ServiceArea sa WHERE sa.currentCapacity < sa.maximumCapacity")
    List<ServiceArea> findServiceAreasWithAvailableCapacity();

    List<ServiceArea> findServiceAreasByBackupFlag(boolean flag);

}
