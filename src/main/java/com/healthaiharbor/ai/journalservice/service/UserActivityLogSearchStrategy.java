package com.healthaiharbor.ai.journalservice.service;

import com.healthaiharbor.ai.journalservice.dto.UserActivitySearch;
import com.healthaiharbor.ai.journalservice.modal.UserActivityLog;
import org.springframework.data.jpa.domain.Specification;

public interface UserActivityLogSearchStrategy {
    Specification<UserActivityLog> buildSpecification(UserActivitySearch searchDTO);
}
