package com.ftn.sbnz.service.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.contract.repository.ContractProposalRepository;

@Service
public class ContractProposalService implements IContractProposalService{

    @Autowired
    private ContractProposalRepository contractProposalRepository;
    
}
