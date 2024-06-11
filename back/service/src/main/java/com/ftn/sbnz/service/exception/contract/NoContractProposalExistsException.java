package com.ftn.sbnz.service.exception.contract;

public class NoContractProposalExistsException extends RuntimeException {

    public NoContractProposalExistsException(String clientUsername) {
        super("No contract proposal exists for client " + clientUsername);
    }
}
