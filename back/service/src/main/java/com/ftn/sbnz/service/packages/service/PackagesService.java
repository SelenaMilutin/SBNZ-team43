package com.ftn.sbnz.service.packages.service;

import com.ftn.sbnz.service.user.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackagesService implements IPackagesService{
    @Autowired
    AppUserRepository PackaAppUserRepository;
}
