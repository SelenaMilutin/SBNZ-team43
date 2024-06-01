package com.ftn.sbnz.model.user;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.ftn.sbnz.model.complaint.Complaint;
import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.ContractProposal;
import com.ftn.sbnz.model.servicearea.ServiceArea;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends AppUser {
    private boolean premium;

    @ManyToOne
    @JoinColumn(name = "service_area_id")
    private ServiceArea serviceArea;

    private transient ServiceArea previousServiceArea;

    @OneToMany(mappedBy = "client")
    private List<Contract> contracts = new ArrayList<>();

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private ContractProposal contractProposal;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints = new ArrayList<>();

    public boolean hasContractProposal() {
        return this.contractProposal != null;
    }

    public void setServiceArea(ServiceArea serviceArea) {
        this.previousServiceArea = this.serviceArea;
        this.serviceArea = serviceArea;
    }

    public boolean isServiceAreaSet() {
        return this.serviceArea != null;
    }

}
