package com.xutrade.service;

import com.xutrade.repository.TradeRepository;
import com.xutrade.repository.entity.TradeEntity;
import com.xutrade.service.exception.ContractNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

@Component
public class TradeService {

    @Autowired
    private TradeRepository repository;

    public long createTrade(NewTradeRequest request) throws InternalError {
        TradeEntity entity = new TradeEntity();
        entity.setSellerAddr(request.getSellerAddr());
        entity.setBuyerAddr(request.getBuyerAddr());
        entity.setCoinName(request.getCoinName());
        entity.setCoinAmount(request.getAmount().toString());
        entity.setCoinPrice(request.getWeiPerCoin().toString());
        entity.setCreatedTime(new Date());
        return repository.save(entity).getId();
    }

    public TradeEntity getTrade(long id) throws ContractNotFound {
        TradeEntity tradeEntity = this.repository.findOne(id);
        if(tradeEntity == null)
            throw new ContractNotFound(id);
        return tradeEntity;
    }
}
