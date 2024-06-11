package com.ftn.sbnz.service.user.service;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.servicearea.ServiceArea;
import com.ftn.sbnz.model.user.*;
import com.ftn.sbnz.service.contract.service.ContractDroolsService;
import com.ftn.sbnz.service.exception.user.UsernameNotFoundException;
import com.ftn.sbnz.service.mapper.AppUserMapper;
import com.ftn.sbnz.service.user.repository.AppUserRepository;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import com.ftn.sbnz.service.user.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService implements IAppUserService {

    private final ClientRepository clientRepository;
    private final AppUserMapper appUserMapper;

    @Override
    public UserProfileDTO getUserProfile(String username) {
        Client client = clientRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return appUserMapper.mapUserToUserProfileDTO(client);
    }

    @Override
    public AppUser findById(long l) {
        Optional<Client> opt = clientRepository.findById(l);
        if (opt.isEmpty())
            throw new RuntimeException("Contract now found");
        return opt.get();
    }

}
