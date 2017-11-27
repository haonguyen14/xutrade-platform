package com.xutrade.service.exception;

public class ContractNotFound extends Exception {
    public ContractNotFound(long id) {
        super(String.format("cannot get contract with id %s", id));
    }
}
