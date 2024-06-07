package com.ftn.sbnz.service.contract.service;

import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.servicearea.ServiceArea;
import com.ftn.sbnz.model.user.Client;
import com.ftn.sbnz.model.user.Discount;
import com.ftn.sbnz.model.user.IAppUserService;
import com.ftn.sbnz.service.contract.repository.ContractRepository;
import com.ftn.sbnz.service.user.repository.ClientRepository;
import com.ftn.sbnz.service.user.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.kie.api.KieBase;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ftn.sbnz.service.config.DroolsConfig.testKieSessionFactsAndRules;

@RequiredArgsConstructor
@Service
public class ContractDroolsService {

    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;
    private final DiscountRepository discountRepository;
    private final KieSession forwardContractKsession;
    private Map<Long, FactHandle> clientHandles;
    private Map<Long, FactHandle> discountHandles;
    private Map<Long, FactHandle> contractHandles;

    @PostConstruct
    private void init() {
        this.clientHandles = fillClientHandles();
        this.discountHandles = fillDiscountHandles();
        this.contractHandles = fillContractHandles();
    }

    private Map<Long, FactHandle> fillClientHandles() {
        List<Client> clients = clientRepository.findAll();
        Map<Long, FactHandle> clientMap = new HashMap<>();
        for (Client client : clients) {
            FactHandle handle = forwardContractKsession.insert(client);
            clientMap.put(client.getId(), handle);
        }
        return clientMap;
    }

    private Map<Long, FactHandle> fillContractHandles() {
        List<Contract> contracts = contractRepository.findAll();
        Map<Long, FactHandle> contractsMap = new HashMap<>();
        for (Contract contract : contracts) {
            FactHandle handle = forwardContractKsession.insert(contract);
            contractsMap.put(contract.getId(), handle);
        }
        return contractsMap;
    }

    private Map<Long, FactHandle> fillDiscountHandles() {
        List<Discount> discounts = discountRepository.findAll();
        Map<Long, FactHandle> discountsMap = new HashMap<>();
        for (Discount discount : discounts) {
            FactHandle handle = forwardContractKsession.insert(discount);
            discountsMap.put(discount.getId(), handle);
        }
        return discountsMap;
    }

    public void insertOrUpdateClientFact(Client client) {
        FactHandle handle = clientHandles.get(client.getId());
        if (handle == null) {
            handle = forwardContractKsession.insert(client);
            clientHandles.put(client.getId(), handle);
        } else {
            forwardContractKsession.update(handle, client);
        }
    }

    public void insertOrUpdateContractFact(Contract contract) {
        FactHandle handle = contractHandles.get(contract.getId());
        if (handle == null) {
            handle = forwardContractKsession.insert(contract);
            contractHandles.put(contract.getId(), handle);
        } else {
            forwardContractKsession.update(handle, contract);
        }
    }

    public void insertOrUpdateDiscountFact(Discount discount) {
        FactHandle handle = discountHandles.get(discount.getId());
        if (handle == null) {
            handle = forwardContractKsession.insert(discount);
            discountHandles.put(discount.getId(), handle);
        } else {
            forwardContractKsession.update(handle, discount);
        }
    }

    public void fireAllRules(IAppUserService globalAppUserService) {
        forwardContractKsession.setGlobal("appUserService", globalAppUserService);
        testKieSessionFactsAndRules(forwardContractKsession);
        forwardContractKsession.fireAllRules();
    }

}
