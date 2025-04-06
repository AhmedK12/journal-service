package com.healthaiharbor.ai.journalservice.serviceimpl;

import com.healthaiharbor.ai.journalservice.dto.UserActivitySearch;
import com.healthaiharbor.ai.journalservice.modal.UserActivityLog;
import com.healthaiharbor.ai.journalservice.service.UserActivityLogSearchStrategy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
@Service
public class UserActivityLogSearchStrategyImpl implements UserActivityLogSearchStrategy {

    @Override
    public Specification<UserActivityLog> buildSpecification(UserActivitySearch dto) {
        Specification<UserActivityLog> spec = Specification.where(null);

        if (dto.getUsername() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("username"), dto.getUsername()));
        }

        if (dto.getPerformedBy() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("performedBy"), dto.getPerformedBy()));
        }

        if (dto.getRole() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("role"), dto.getRole()));
        }

        if (dto.getActivityType() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("activityType"), dto.getActivityType()));
        }

        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            spec = spec.and((root, query, cb) -> cb.between(root.get("timestamp"), dto.getStartDate(), dto.getEndDate()));
        }

        return spec;
    }
}