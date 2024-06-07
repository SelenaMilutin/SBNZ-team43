package com.ftn.sbnz.service.user.service;

import com.ftn.sbnz.model.servicearea.ServiceArea;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.model.user.Discount;
import com.ftn.sbnz.model.user.IAppUserService;
import com.ftn.sbnz.service.contract.service.ContractDroolsService;
import com.ftn.sbnz.service.exception.user.UsernameNotFoundException;
import com.ftn.sbnz.service.mapper.AppUserMapper;
import com.ftn.sbnz.model.user.UserProfileDTO;
import com.ftn.sbnz.service.user.repository.AppUserRepository;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import com.ftn.sbnz.service.user.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

}
