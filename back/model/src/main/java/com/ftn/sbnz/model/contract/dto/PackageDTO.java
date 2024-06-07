package com.ftn.sbnz.model.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PackageDTO {

    protected Long id;
    private String name;
    private double monthlyPrice;
    private String packageType;
    private boolean inOfferFlag;
}
