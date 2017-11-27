package com.xutrade.repository.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TradeEventEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long tradeId;

    @Enumerated(EnumType.ORDINAL)
    private TradeEventType eventType;

    private String eventData;

    private Date createdTime;

    public void setId(Long id) { this.id = id; }
    public void setTradeId(Long tradeId) { this.tradeId = tradeId; }
    public void setEventType(TradeEventType eventType) { this.eventType = eventType; }
    public void setEventData(String eventData) { this.eventData = eventData; }
    public void setCreatedTime(Date createdTime) { this.createdTime = createdTime; }

    public Long getId() { return id; }
    public Long getTradeId() { return tradeId; }
    public TradeEventType getEventType() { return eventType; }
    public String getEventData() { return eventData; }
    public Date getCreatedTime() { return createdTime; }
}
