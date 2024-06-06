package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.servicearea.ServiceArea;
import com.ftn.sbnz.model.servicearea.ServiceAreaDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceAreaMapper {

    public List<ServiceAreaDTO> mapAreasToDTOs(List<ServiceArea> areas) {
        List<ServiceAreaDTO> dtos = new ArrayList<>();
        for (ServiceArea area : areas) {
            dtos.add(mapAreaToDTO(area));
        }
        return dtos;
    }

    public ServiceAreaDTO mapAreaToDTO(ServiceArea area) {
        ServiceAreaDTO dto = new ServiceAreaDTO();
        dto.setId(area.getId());
        dto.setActiveFlag(area.isActiveFlag());
        dto.setAvailableFlag(area.isAvailableFlag());
        dto.setBackupFlag(area.isBackupFlag());
        dto.setMaximumCapacity(area.getMaximumCapacity());
        dto.setCurrentCapacity(area.getCurrentCapacity());
        dto.setLastUnavailableTimestamp(area.getLastUnavailableTimestamp());
        return dto;
    }

}
