package com.ftn.sbnz.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotNull
    @Size(max = 100, message = "Username cannot be longer than 100 characters")
    private String username;
    @NotNull(message = "Password is required!")
    private String password;

}
