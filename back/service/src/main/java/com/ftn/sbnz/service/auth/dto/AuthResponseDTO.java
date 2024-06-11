package com.ftn.sbnz.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private String username;
    private String role;
    private Long userId;

    public AuthResponseDTO(String accessToken, String username, Long userId, String role) {
        this.accessToken = accessToken;
        this.username = username;
        this.userId = userId;
        this.role = role;
    }
}
