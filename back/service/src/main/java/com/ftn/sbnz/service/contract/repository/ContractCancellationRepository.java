package com.ftn.sbnz.service.contract.repository;

import com.ftn.sbnz.model.contract.ContractCancellation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractCancellationRepository extends JpaRepository<ContractCancellation, Long> {
}
