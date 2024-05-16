package com.ftn.sbnz.model.contract;

import com.ftn.sbnz.model.packages.Packages;
import lombok.*;
import javax.persistence.*;

import com.ftn.sbnz.model.user.Client;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "contract_proposal")
public class ContractProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

     @OneToOne
     @JoinColumn(name = "packages_id")
     private Packages packages;

}
