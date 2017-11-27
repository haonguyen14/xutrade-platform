package com.xutrade.service;

import com.xutrade.repository.TradeEventRepository;
import com.xutrade.repository.entity.TradeEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TradeEventService {

    @Autowired
    private TradeEventRepository repository;

    public void logEvent(EventLogRequest request) {
        TradeEventEntity event = new TradeEventEntity();
        event.setEventType(request.getEventType());
        event.setTradeId(request.getTradeId());
        event.setEventData(request.getEventData());
        event.setCreatedTime(new Date());
        repository.save(event);
    }

    public List<TradeEventEntity> getLogs(long id) {
        return repository.findByTradeId(id);
    }
}
