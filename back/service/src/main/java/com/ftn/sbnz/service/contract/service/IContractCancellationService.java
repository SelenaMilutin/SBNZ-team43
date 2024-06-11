package com.ftn.sbnz.service.contract.service;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.dto.CancellationDTO;
import org.springframework.http.HttpStatus;

public interface IContractCancellationService {
    CancellationDTO cancelContract(Contract contract);
}
