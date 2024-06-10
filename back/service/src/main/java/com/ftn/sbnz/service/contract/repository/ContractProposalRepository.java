package com.ftn.sbnz.service.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.contract.ContractProposal;

import java.util.Optional;

public interface ContractProposalRepository extends JpaRepository<ContractProposal, Long> {

    Optional<ContractProposal> findByClientId(Long clientId);

}
