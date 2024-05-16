package com.ftn.sbnz.service.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.contract.ContractProposal;

public interface ContractProposalRepository extends JpaRepository<ContractProposal, Long> {
    
}
