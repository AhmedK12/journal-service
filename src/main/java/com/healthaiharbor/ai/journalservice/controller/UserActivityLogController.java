package com.healthaiharbor.ai.journalservice.controller;

import com.healthaiharbor.ai.journalservice.dto.UserActivitySearch;
import com.healthaiharbor.ai.journalservice.modal.UserActivityLog;
import com.healthaiharbor.ai.journalservice.repository.UserActivityLogRepository;
import com.healthaiharbor.ai.journalservice.service.UserActivityService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class UserActivityLogController {

    private final UserActivityLogRepository repository;
    private final UserActivityService service;

    @GetMapping
    public ResponseEntity<List<UserActivityLog>> getAllLogs(@AuthenticationPrincipal Jwt jwt) {
        if (!jwt.getClaimAsStringList("roles").contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.getAllActivities());
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<UserActivityLog>> getLogsByUsername(@PathVariable String username, @AuthenticationPrincipal Jwt jwt) {
        if (!jwt.getClaimAsStringList("roles").contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(service.getActivitiesByUsername(username));
    }
    @PostMapping("/search")
    public ResponseEntity<Page<UserActivityLog>> searchLogs(
            @RequestBody UserActivitySearch searchDTO,
            @AuthenticationPrincipal Jwt jwt) {
        System.out.printf("Searching for %s");
        if (!jwt.getClaimAsStringList("roles").contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


        return ResponseEntity.ok(service.searchActivities(searchDTO));
    }
}

