package com.healthaiharbor.ai.journalservice.service;

import com.healthaiharbor.ai.journalservice.dto.UserActivityEvent;
import com.healthaiharbor.ai.journalservice.dto.UserActivitySearch;
import com.healthaiharbor.ai.journalservice.modal.UserActivityLog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserActivityService {
    void logActivity(UserActivityEvent event);
    Page<UserActivityLog> searchActivities(UserActivitySearch searchDTO);
    List<UserActivityLog> getAllActivities();
    List<UserActivityLog> getActivitiesByUsername(String username);
}