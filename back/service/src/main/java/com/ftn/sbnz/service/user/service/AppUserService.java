package com.ftn.sbnz.service.user.service;

import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.service.exception.user.UserDoesNotExistByIdException;
import com.ftn.sbnz.service.exception.user.UsernameNotFoundException;
import com.ftn.sbnz.service.mapper.AppUserMapper;
import com.ftn.sbnz.service.user.dto.UserProfileDTO;
import com.ftn.sbnz.service.user.repository.AppUserRepository;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService implements IAppUserService{

    private final AppUserRepository appUserRepository;
    private final ClientRepository clientRepository;
    private final AppUserMapper appUserMapper;

    @Override
    public UserProfileDTO getUserProfile(String username) {
        Client client = clientRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return appUserMapper.mapUserToUserProfileDTO(client);
    }
}
