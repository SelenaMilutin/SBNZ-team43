package com.ftn.sbnz.service.advice;

import com.ftn.sbnz.service.exception.contract.NoContractProposalExistsException;
import com.ftn.sbnz.service.exception.servicearea.NoServiceAreaAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ContractExceptionHandlerAdvice {

    @ExceptionHandler(NoContractProposalExistsException.class)
    public ResponseEntity<String> handleNoServiceAreaAvailable(NoContractProposalExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}