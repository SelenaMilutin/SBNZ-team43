package com.ftn.sbnz.service.contract.service;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.dto.ContractDTO;
import com.ftn.sbnz.model.contract.dto.CreateContractDTO;
import com.ftn.sbnz.model.contract.service.IContractService;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.service.exception.packages.PackageDoesNotExistByIdException;
import com.ftn.sbnz.service.mapper.ContractMapper;
import com.ftn.sbnz.service.packages.repository.PackagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.contract.repository.ContractRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ContractService implements IContractService {

    private final ContractRepository contractRepository;
    private final PackagesRepository packagesRepository;
    private final ContractMapper contractMapper;
    private final ContractDroolsService contractDroolsService;

    @Override
    public ContractDTO create(CreateContractDTO createContractDTO, Client client) {
        Contract contract = new Contract();
        Packages packages = packagesRepository.findById(Long.valueOf(createContractDTO.getPackageId())).orElseThrow(
                () -> {return new PackageDoesNotExistByIdException(createContractDTO.getPackageId());}
        );
        contract.setPackages(packages);
        contract.setStartDate(LocalDateTime.now());
        contract.setExpirationDate(LocalDateTime.now().plusMonths(createContractDTO.getLengthInMonths()));
        contract.setActiveFlag(true);
        contract.setClient(client);
        Contract saved = contractRepository.save(contract);
        contractDroolsService.insertOrUpdateContractFact(saved);
        return contractMapper.mapContractToContractDTO(saved);
    }
}
