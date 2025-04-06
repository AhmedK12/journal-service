package com.healthaiharbor.ai.journalservice.service;

import com.healthaiharbor.ai.journalservice.modal.UserActivityLog;

import java.util.List;

public interface UserEventService {
    void saveEvent(String jsonEvent);
    List<UserActivityLog> getAllEvents();
    List<UserActivityLog> getEventsByUsername(String username);
}
