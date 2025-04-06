package com.healthaiharbor.ai.journalservice.modal;

import com.healthaiharbor.ai.journalservice.dto.UserActivityType;
import com.healthaiharbor.ai.journalservice.serviceimpl.MapToJsonConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "user_activity_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private UserActivityType activity;

    private String performedBy;

    private Instant timestamp;

    @Column(columnDefinition = "TEXT") // or "JSON" if your MySQL version supports it
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Object> metadata;

}
