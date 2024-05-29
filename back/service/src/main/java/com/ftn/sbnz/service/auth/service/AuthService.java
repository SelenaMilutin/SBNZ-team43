package com.ftn.sbnz.service.auth.service;

import com.ftn.sbnz.model.user.AppUser;
import com.ftn.sbnz.service.auth.dto.AuthResponseDTO;
import com.ftn.sbnz.service.auth.dto.CreateUserDTO;
import com.ftn.sbnz.service.auth.dto.LoginDTO;
import com.ftn.sbnz.service.exception.user.UsernameAlreadyExistsException;
import com.ftn.sbnz.service.exception.user.UsernameNotFoundForLoginException;
import com.ftn.sbnz.service.mapper.AppUserMapper;
import com.ftn.sbnz.service.user.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {

    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AppUserMapper appUserMapper;

    @Override
    public void createUser(CreateUserDTO dto) {
        if (appUserRepository.existsByEmail(dto.getEmail())) {
            throw new UsernameAlreadyExistsException(dto.getEmail());
        }

        AppUser userEntity = appUserMapper.mapCreateUserDTOToUserEntity(dto);
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

        appUserRepository.save(userEntity);
    }

    @Override
    public AuthResponseDTO login(LoginDTO dto) {
        AppUser user = appUserRepository.findByEmail(dto.getUsername()).orElseThrow(
                () -> new UsernameNotFoundForLoginException(dto.getUsername())
        );
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword(), user.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken();
        return new AuthResponseDTO(token, user.getUsername());
    }
}
