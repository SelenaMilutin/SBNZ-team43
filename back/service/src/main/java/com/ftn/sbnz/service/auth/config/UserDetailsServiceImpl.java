package com.ftn.sbnz.service.auth.config;

import com.ftn.sbnz.model.user.AppUser;
import com.ftn.sbnz.service.user.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return userEntity;
    }

}