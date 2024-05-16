package com.ftn.sbnz.model.servicearea;

import lombok.*;

import javax.persistence.*;

import com.ftn.sbnz.model.user.Client;

import static javax.persistence.InheritanceType.JOINED;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

    @OneToMany( mappedBy = "serviceArea")
    private List<Client> clients;

    public double getCapacityPercentage() {
        return currentCapacity/maximumCapacity;
    }
}
