package com.ftn.sbnz.model.contract.service;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.dto.ContractDTO;
import com.ftn.sbnz.model.contract.dto.CreateContractDTO;
import com.ftn.sbnz.model.packages.dto.PackageDTO;
import com.ftn.sbnz.model.user.Client;

import java.util.List;

public interface IContractService {

    ContractDTO create(CreateContractDTO contract, String username);

    void createDiscount(Contract contract, int numberOfPreviousContracts);

    void applyDiscount(Contract contract);

    List<ContractDTO> getContractsForClient(String username);

    PackageDTO getContractProposal(String username);

}
