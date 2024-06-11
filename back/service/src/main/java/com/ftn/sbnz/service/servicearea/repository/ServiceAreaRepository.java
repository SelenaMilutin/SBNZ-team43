package com.ftn.sbnz.service.servicearea.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.servicearea.ServiceArea;

import java.util.List;

public interface ServiceAreaRepository extends JpaRepository<ServiceArea, Long> {

    @Query("SELECT sa FROM ServiceArea sa WHERE sa.currentCapacity < sa.maximumCapacity * 0.5 AND sa.availableFlag = true")
    List<ServiceArea> findAvailableServiceAreasWithLessThan50PercentCapacity();

    @Query("SELECT sa FROM ServiceArea sa WHERE sa.currentCapacity < sa.maximumCapacity AND sa.availableFlag = true")
    List<ServiceArea> findAvailableServiceAreasWithAvailableCapacity();

    List<ServiceArea> findServiceAreasByBackupFlag(boolean flag);

    List<ServiceArea> findServiceAreasByAvailableFlag(boolean flag);

}
