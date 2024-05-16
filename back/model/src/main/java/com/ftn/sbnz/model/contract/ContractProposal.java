package com.ftn.sbnz.model.contract;

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

    // TODO
    // @OneToOne
    // JoinColumn(name = "package_id")
    // private Package package;

}
