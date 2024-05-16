package com.ftn.sbnz.service.servicearea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.servicearea.repository.ServiceAreaRepository;

@Service
public class ServiceAreaService implements IServiceAreaService {
    
    @Autowired
    private ServiceAreaRepository serviceAreaRepository;
}
