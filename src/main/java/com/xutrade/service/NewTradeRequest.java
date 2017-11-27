package com.xutrade.service;

import com.google.common.base.Strings;

import java.math.BigInteger;

public class NewTradeRequest {
    private String coinName;
    private String amount;
    private String weiPerCoin;
    private String sellerAddr;
    private String buyerAddr;

    public void setCoinName(String coinName) { this.coinName = coinName; }
    public void setAmount(String amount) { this.amount = amount; }
    public void setWeiPerCoin(String weiPerCoin) { this.weiPerCoin = weiPerCoin; }
    public void setSellerAddr(String sellerAddr) { this.sellerAddr = sellerAddr;}
    public void setBuyerAddr(String buyerAddr) { this.buyerAddr = buyerAddr; }

    public String getCoinName() { return this.coinName; }
    public BigInteger getAmount() { return Strings.isNullOrEmpty(this.amount) ? null : new BigInteger(this.amount); }
    public BigInteger getWeiPerCoin() { return Strings.isNullOrEmpty(this.weiPerCoin) ? null : new BigInteger(this.weiPerCoin); }
    public String getSellerAddr() { return sellerAddr; }
    public String getBuyerAddr() { return buyerAddr; }
}
