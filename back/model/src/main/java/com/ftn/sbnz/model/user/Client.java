package com.ftn.sbnz.model.user;

import lombok.*;

import java.util.List;

import javax.persistence.*;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.ContractProposal;
import com.ftn.sbnz.model.servicearea.ServiceArea;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends AppUser {

    @ManyToOne
    @JoinColumn( name = "service_area_id")
    private ServiceArea serviceArea;

    @OneToMany( mappedBy = "client")
    private List<Contract> contracts;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private ContractProposal contractProposal;

    public boolean hasContractProposal() {
        return this.contractProposal != null;
    }

}
