package com.ftn.sbnz.service.user.service;

import com.ftn.sbnz.service.user.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements IAppUserService{

    @Autowired
    AppUserRepository appUserRepository;
}
