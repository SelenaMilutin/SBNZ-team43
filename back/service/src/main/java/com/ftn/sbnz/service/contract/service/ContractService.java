package com.ftn.sbnz.service.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.contract.repository.ContractRepository;

@Service
public class ContractService implements IContractService {

    @Autowired
    private ContractRepository contractRepository;
    
}
