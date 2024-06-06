package com.ftn.sbnz.model.servicearea;

import lombok.*;

import javax.persistence.*;
import com.ftn.sbnz.model.user.Client;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "service_area")
public class ServiceArea {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean activeFlag;

    private boolean availableFlag;

    private boolean backupFlag;

    private int maximumCapacity;

    private int currentCapacity;

    private LocalDateTime lastUnavailableTimestamp;

    @OneToMany( mappedBy = "serviceArea")
    private List<Client> clients = new ArrayList<>();

    public double getCapacityPercentage() {
        return (double) currentCapacity / maximumCapacity;
    }

    public void incrementCurrentCapacity() {
        currentCapacity++;
    }

    public void decrementCurrentCapacity() {
        currentCapacity--;
    }

    public String toString() {
        return "Service area " + this.getId().toString() + " available = " + this.isAvailableFlag();
    }
}
