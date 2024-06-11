package com.ftn.sbnz.model.servicearea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAreaDTO {
    private Long id;
    private boolean activeFlag;
    private boolean availableFlag;
    private boolean backupFlag;
    private int maximumCapacity;
    private int currentCapacity;
    private LocalDateTime lastUnavailableTimestamp;
    // lastUnavailableTimestamp
}
