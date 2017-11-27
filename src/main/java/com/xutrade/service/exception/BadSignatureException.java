package com.xutrade.service.exception;

public class BadSignatureException extends Exception {

    public BadSignatureException(String senderAddress, String address) {
        super(String.format("Expecting %s, but get %s", senderAddress, address));
    }
}
