package com.ftn.sbnz.service.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz.model.contract.Contract;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    ArrayList<Contract> findByExpirationDateBetween(LocalDateTime from, LocalDateTime to);

    ArrayList<Contract> findContractsByClientId(Long clientId);
}
