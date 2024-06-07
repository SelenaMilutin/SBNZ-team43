package com.ftn.sbnz.model.contract.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ContractDTO {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime expirationDate;

    private boolean activeFlag;

    private PackageDTO packageDTO;

    private double discount;
}
