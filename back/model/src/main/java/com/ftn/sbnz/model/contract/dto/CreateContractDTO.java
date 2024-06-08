package com.ftn.sbnz.model.contract.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateContractDTO {

    private Long packageId;
    private int lengthInMonths;

}
