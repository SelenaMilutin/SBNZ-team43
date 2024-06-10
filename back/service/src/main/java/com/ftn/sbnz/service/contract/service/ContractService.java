package com.ftn.sbnz.service.contract.service;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.ContractProposal;
import com.ftn.sbnz.model.contract.dto.ContractDTO;
import com.ftn.sbnz.model.contract.dto.CreateContractDTO;
import com.ftn.sbnz.model.contract.service.IContractService;
import com.ftn.sbnz.model.packages.Packages;
import com.ftn.sbnz.model.packages.dto.PackageDTO;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.model.user.Discount;
import com.ftn.sbnz.service.contract.repository.ContractProposalRepository;
import com.ftn.sbnz.service.exception.contract.NoContractProposalExistsException;
import com.ftn.sbnz.service.exception.packages.PackageDoesNotExistByIdException;
import com.ftn.sbnz.service.exception.user.UsernameNotFoundException;
import com.ftn.sbnz.service.mapper.ContractMapper;
import com.ftn.sbnz.service.mapper.PackageMapper;
import com.ftn.sbnz.service.packages.repository.PackagesRepository;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import com.ftn.sbnz.service.user.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.service.contract.repository.ContractRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContractService implements IContractService {

    private final ContractRepository contractRepository;
    private final PackagesRepository packagesRepository;
    private final DiscountRepository discountRepository;
    private final ContractMapper contractMapper;
    private final ContractDroolsService contractDroolsService;
    private final ClientRepository clientRepository;
    private final PackageMapper packageMapper;

    @Override
    public ContractDTO create(CreateContractDTO createContractDTO, String username) {
        Contract contract = new Contract();
        Client client = clientRepository.findByEmail(username).orElseThrow(
                () -> {return new UsernameNotFoundException(username); }
        );
        Packages packages = packagesRepository.findById(createContractDTO.getPackageId()).orElseThrow(
                () -> {return new PackageDoesNotExistByIdException(createContractDTO.getPackageId().toString());}
        );
        contract.setPackages(packages);
        contract.setStartDate(LocalDateTime.now());
        contract.setExpirationDate(LocalDateTime.now().plusMonths(createContractDTO.getLengthInMonths()));
        contract.setActiveFlag(true);
        contract.setClient(client);
        this.applyDiscount(contract);
        Contract saved = contractRepository.save(contract);
        contractDroolsService.insertOrUpdateContractFact(saved);
        return contractMapper.mapContractToContractDTO(saved);
    }

    @Override
    public void createDiscount(Contract contract, int numberOfPreviousContracts) {
        Discount discount = new Discount();
        discount.setClient(contract.getClient());
        discount.setPercentage(numberOfPreviousContracts * 0.02);
        contractRepository.save(contract);
        discountRepository.save(discount);
        contractDroolsService.insertOrUpdateDiscountFact(discount);
    }

    @Override
    public void applyDiscount(Contract contract) {
        Optional<Discount> discount = discountRepository.findDiscountByClientId(contract.getClient().getId());
        if (discount.isPresent()) {
            contract.setDiscount(discount.get().getPercentage());
            contractDroolsService.deleteDiscountFact(discount.get());
            discountRepository.deleteById(discount.get().getId());
        }
    }

    @Override
    public List<ContractDTO> getContractsForClient(String username) {
        Client client = clientRepository.findByEmail(username).orElseThrow(
                () -> {return new UsernameNotFoundException(username); }
        );
        List<ContractDTO> contractDTOs = new ArrayList<>();
        for (Contract contract : contractRepository.findContractsByClientId(client.getId())) {
            contractDTOs.add(contractMapper.mapContractToContractDTO(contract));
        }
        return contractDTOs;
    }

    @Override
    public PackageDTO getContractProposal(String username) {
        Client client = clientRepository.findByEmail(username).orElseThrow(
                () -> {return new UsernameNotFoundException(username); }
        );
        if (!client.hasContractProposal()) {
            throw new NoContractProposalExistsException(client.getUsername());
        }
        return packageMapper.mapPackageToPackageDTO(client.getContractProposal().getPackages());
    }

    //    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000) // 12 hours
    @Scheduled(fixedDelay = 20 * 1000)
    public void checkContractExpirations() {
        try {
            System.out.println("Scheduled task for contract executed at " + LocalDateTime.now());
            contractDroolsService.fireAllRules(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
