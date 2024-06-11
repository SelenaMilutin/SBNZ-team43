package com.ftn.sbnz.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserDTO {

    private String email;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String password;

}
