package com.ftn.sbnz.service.mapper;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.dto.ContractDTO;
import com.ftn.sbnz.model.contract.dto.CreateContractDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContractMapper {

    private PackageMapper packageMapper;

    public ContractDTO mapContractToContractDTO(Contract contract) {
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setId(contract.getId());
        contractDTO.setDiscount(contract.getDiscount());
        contractDTO.setStartDate(contract.getStartDate());
        contractDTO.setExpirationDate(contract.getExpirationDate());
        contractDTO.setActiveFlag(contract.isActiveFlag());
        contractDTO.setPackageDTO(packageMapper.mapPackageToPackageDTO(contract.getPackages()));
        return contractDTO;
    }

}
