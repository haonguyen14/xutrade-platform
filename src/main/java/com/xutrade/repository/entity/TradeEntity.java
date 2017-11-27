package com.xutrade.repository.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TradeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String sellerAddr;
    private String buyerAddr;
    private String contractPassword;
    private String coinName;
    private String coinAmount;
    private String coinPrice;
    private Date createdTime;

    public void setId(Long id) { this.id = id; }
    public void setCoinName(String coinName) { this.coinName = coinName; }
    public void setCoinAmount(String coinAmount) { this.coinAmount = coinAmount; }
    public void setCoinPrice(String coinPrice) { this.coinPrice = coinPrice; }
    public void setSellerAddr(String sellerAddr) { this.sellerAddr = sellerAddr;}
    public void setBuyerAddr(String buyerAddr) { this.buyerAddr = buyerAddr; }
    public void setContractPassword(String contractPassword) { this.contractPassword = contractPassword; }
    public void setCreatedTime(Date createdTime) { this.createdTime = createdTime; }

    public Long getId() { return id; }
    public String getCoinName() { return coinName; }
    public String getCoinAmount() { return coinAmount; }
    public String getCoinPrice() { return coinPrice; }
    public String getSellerAddr() { return sellerAddr; }
    public String getBuyerAddr() { return buyerAddr; }
    public String getContractPassword() { return contractPassword; }
    public Date getCreatedTime() { return createdTime; }
}
