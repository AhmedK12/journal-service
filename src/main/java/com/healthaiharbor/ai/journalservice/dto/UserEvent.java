package com.healthaiharbor.ai.journalservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record UserEvent (
        @NotBlank(message = "Username must not be blank")
        String username,

        @NotBlank(message = "Activity is required")
        String activity,

        @NotBlank(message = "PerformedBy is required")
        String performedBy,

        @NotBlank(message = "Timestamp is required")
        LocalDateTime timestamp,

        @Size(max = 500, message = "Details must be less than 500 characters")
        String details){
}
