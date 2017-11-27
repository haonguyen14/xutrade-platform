package com.xutrade.service;

import com.xutrade.repository.entity.TradeEventType;

public class EventLogRequest {
    private TradeEventType eventType;
    private Long tradeId;
    private String eventData;

    public TradeEventType getEventType() {
        return eventType;
    }

    public void setEventType(TradeEventType eventType) {
        this.eventType = eventType;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }
}
