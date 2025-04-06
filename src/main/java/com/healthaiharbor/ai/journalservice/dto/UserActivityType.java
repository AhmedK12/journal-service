package com.healthaiharbor.ai.journalservice.dto;


public enum UserActivityType {
    LOGIN,
    LOGOUT,
    REGISTER,
    PASSWORD_CHANGE,
    PROFILE_UPDATE,
    TOKEN_REFRESH,
    ROLE_ASSIGNED,
    PERMISSION_GRANTED,
    ACCOUNT_LOCKED,
    ACCOUNT_UNLOCKED, ACCOUNT_DELETE;

    // Optional: Add custom logic or label if needed
    public String getLabel() {
        return this.name().replace("_", " ").toLowerCase();
    }
}
