package com.ftn.sbnz.model.contract;

import com.ftn.sbnz.model.packages.Packages;
import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.ftn.sbnz.model.user.Client;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "contract")
public class Contract {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime expirationDate;

    private boolean activeFlag;

    @ManyToOne
    private Client client;


    @OneToOne
    @JoinColumn(name = "packages_id")
    private Packages packages;
}
