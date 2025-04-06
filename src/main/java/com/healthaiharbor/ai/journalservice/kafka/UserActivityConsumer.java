package com.healthaiharbor.ai.journalservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthaiharbor.ai.journalservice.dto.UserActivityEvent;
import com.healthaiharbor.ai.journalservice.modal.UserActivityLog;
import com.healthaiharbor.ai.journalservice.repository.UserActivityLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserActivityConsumer {

    private final UserActivityLogRepository repository;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "user-events", groupId = "journal-service")
    public void consume(UserActivityEvent event) {
        log.info("Consumed event: {}", event);

        UserActivityLog logEntry = UserActivityLog.builder()
                .username(event.getUsername())
                .performedBy(event.getPerformedBy())
                .activity(event.getActivityType())
                .metadata(event.getMetadata())
                .timestamp(Instant.now())
                .build();

        repository.save(logEntry);
    }

}