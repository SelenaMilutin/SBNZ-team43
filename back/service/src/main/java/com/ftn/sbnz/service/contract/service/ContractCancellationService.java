package com.ftn.sbnz.service.contract.service;

import com.ftn.sbnz.kjar.KjarApplication;
import com.ftn.sbnz.model.contract.Contract;
import com.ftn.sbnz.model.contract.ContractCancellation;
import com.ftn.sbnz.model.contract.dto.CancellationDTO;
import com.ftn.sbnz.service.contract.repository.ContractCancellationRepository;
import com.ftn.sbnz.service.contract.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContractCancellationService implements IContractCancellationService{
    private final ContractCancellationRepository contractCancellationRepository;
    private final ContractRepository contractRepository;

    @Override
    public CancellationDTO cancelContract(Contract contract) {
        double discount = contract.getDiscount();
        InputStream template = KjarApplication.class.getResourceAsStream("/rules/templates/cancellation.drt");
        InputStream data = KjarApplication.class.getResourceAsStream("/rules/templates/cancellation-data.xls");

        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drl = converter.compile(data, template, 3, 2);


        KieSession ksession = this.createKieSessionFromDRL(drl);
        ContractCancellation cancellation = new ContractCancellation(0L, contract, LocalDateTime.now());
        ksession.insert(cancellation);
        ksession.fireAllRules();
        contractCancellationRepository.save(cancellation);
        double debt = contract.getDiscount();
        contract.setDiscount(discount);
        contractRepository.save(contract);
        return new CancellationDTO(true, debt);
    }

    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }

}
