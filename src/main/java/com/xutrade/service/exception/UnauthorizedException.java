package com.xutrade.service.exception;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String tradeId) {
        super(String.format("Do not have permission to trade %s", tradeId));
    }
}
