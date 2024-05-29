package com.ftn.sbnz.service.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

public interface IJWTService {

    String generateToken();
    String getUsernameFromJWT(String token);
    boolean validateToken(String token);

    UserDetails getAuthenticatedUser();

    String getJWTFromRequest(HttpServletRequest request);

}
