package com.ftn.sbnz.model.contract;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "contractCancelation")
public class ContractCancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToOne
    private Contract contract;

    private LocalDateTime timestamp;

}
