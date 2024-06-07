package com.ftn.sbnz.model.contract.service;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.dto.ContractDTO;
import com.ftn.sbnz.model.contract.dto.CreateContractDTO;
import com.ftn.sbnz.model.user.Client;

public interface IContractService {

    ContractDTO create(CreateContractDTO contract, Client client);

    void createDiscount(Contract contract, int numberOfPreviousContracts);

    void applyDiscount(Contract contract);

}
