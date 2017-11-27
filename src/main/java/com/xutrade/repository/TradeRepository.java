package com.xutrade.repository;

import com.xutrade.repository.entity.TradeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TradeRepository extends CrudRepository<TradeEntity, Long> {
    TradeEntity findByIdAndContractPassword(Long id, String contractPassword);
}
