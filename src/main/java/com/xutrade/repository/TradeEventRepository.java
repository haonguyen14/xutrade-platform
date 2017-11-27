package com.xutrade.repository;

import com.xutrade.repository.entity.TradeEventEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TradeEventRepository extends CrudRepository<TradeEventEntity, Long> {
    List<TradeEventEntity> findByTradeId(Long tradeId);
}
