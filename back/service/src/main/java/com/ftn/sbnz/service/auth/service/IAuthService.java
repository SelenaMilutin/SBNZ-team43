package com.ftn.sbnz.service.auth.service;

import com.ftn.sbnz.service.auth.dto.AuthResponseDTO;
import com.ftn.sbnz.service.auth.dto.CreateUserDTO;
import com.ftn.sbnz.service.auth.dto.LoginDTO;

public interface IAuthService {

    void createClientUser(CreateUserDTO dto);

    AuthResponseDTO login(LoginDTO dto);
}
