package com.ftn.sbnz.service.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.contract.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    
}
