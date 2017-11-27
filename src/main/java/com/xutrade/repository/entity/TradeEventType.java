package com.xutrade.repository.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum TradeEventType {
    DEPLOYED,
    DEPOSITED,
    CONFIRMED,
    CLOSED;

    private static Map<String, TradeEventType> map = new HashMap<>();

    static {
        map.put("deployed", DEPLOYED);
        map.put("deposited", DEPOSITED);
        map.put("confirmed", CONFIRMED);
        map.put("closed", CLOSED);
    }

    @JsonCreator
    public static TradeEventType getEnumValue(String strValue) {
        return map.get(strValue.toLowerCase());
    }

    @JsonValue
    public String getStringValue(TradeEventType enumValue) {
        for(Map.Entry<String, TradeEventType> entry : map.entrySet()) {
            if(entry.getValue() == enumValue) {
                return entry.getKey();
            }
        }
        return null;
    }
}
