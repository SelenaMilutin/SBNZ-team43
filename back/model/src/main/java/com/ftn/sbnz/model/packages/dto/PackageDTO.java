package com.ftn.sbnz.model.packages.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageDTO {

    private Long id;
    private String name;
    private double monthlyPrice;
    private String packageType;
    private boolean inOfferFlag;

}
