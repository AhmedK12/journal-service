package com.healthaiharbor.ai.journalservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivitySearch {
    private String username; // Target user
    private String performedBy; // Admin or performing user
    private String role;
    private UserActivityType activityType;
    private Instant startDate;
    private Instant endDate;
    private int page = 0;
    private int size = 10;
    private String sortBy = "timestamp";
    private String sortDirection = "desc";
}
