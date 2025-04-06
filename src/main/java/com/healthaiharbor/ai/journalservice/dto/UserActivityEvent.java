package com.healthaiharbor.ai.journalservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Instant;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor  // 🔥 this is REQUIRED for Jackson to deserialize
@AllArgsConstructor
public class UserActivityEvent {
    private String username;
    private UserActivityType activityType;
    private String performedBy;
    private Instant timestamp;
    private Map<String, Object> metadata;
}