package com.ftn.sbnz.service.auth.service;

import com.ftn.sbnz.model.user.AppUser;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.service.auth.dto.AuthResponseDTO;
import com.ftn.sbnz.service.auth.dto.CreateUserDTO;
import com.ftn.sbnz.service.auth.dto.LoginDTO;
import com.ftn.sbnz.service.exception.user.UsernameAlreadyExistsException;
import com.ftn.sbnz.service.mapper.AppUserMapper;
import com.ftn.sbnz.service.servicearea.service.ServiceAreaService;
import com.ftn.sbnz.service.user.repository.AppUserRepository;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {

    private final AppUserRepository appUserRepository;
    private final ClientRepository clientRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AppUserMapper appUserMapper;
    private final ServiceAreaService serviceAreaService;

    @Override
    public void createClientUser(CreateUserDTO dto) {
        if (appUserRepository.existsByEmail(dto.getEmail())) {
            throw new UsernameAlreadyExistsException(dto.getEmail());
        }

        Client client = appUserMapper.mapCreateUserDTOToClient(dto);
        client.setPassword(passwordEncoder.encode(dto.getPassword()));
        clientRepository.save(client);
        serviceAreaService.assignServiceAreaToUser(client);
    }

    @Override
    public AuthResponseDTO login(LoginDTO dto) {
        AppUser user = appUserRepository.findByEmail(dto.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(dto.getUsername())
        );
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword(), user.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken();
        System.out.println(token);
        return new AuthResponseDTO(token, user.getUsername(), user.getId(), user.getRole().getAuthority());
    }
}
