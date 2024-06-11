package com.ftn.sbnz.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private boolean blockedFlag;
    private String serviceAreaId;
    private boolean serviceAreaAvailable;
    private int serviceAreaCurrentCapacity;
    private int serviceAreaMaximumCapacity;
}
