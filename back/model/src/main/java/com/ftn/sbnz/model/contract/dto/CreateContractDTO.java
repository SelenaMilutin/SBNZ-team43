package com.ftn.sbnz.model.contract.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateContractDTO {

    private String packageId;
    private int lengthInMonths;

}
