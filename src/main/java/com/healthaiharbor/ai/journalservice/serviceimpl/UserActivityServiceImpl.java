package com.healthaiharbor.ai.journalservice.serviceimpl;


import com.healthaiharbor.ai.journalservice.dto.UserActivityEvent;
import com.healthaiharbor.ai.journalservice.dto.UserActivitySearch;
import com.healthaiharbor.ai.journalservice.modal.UserActivityLog;
import com.healthaiharbor.ai.journalservice.repository.UserActivityLogRepository;
import com.healthaiharbor.ai.journalservice.service.UserActivityLogSearchStrategy;

import com.healthaiharbor.ai.journalservice.service.UserActivityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserActivityServiceImpl implements UserActivityService {

    private final UserActivityLogRepository repository;
    private final UserActivityLogSearchStrategy searchStrategy;

    @Transactional
    public void logActivity(UserActivityEvent event) {
        UserActivityLog logEntry = UserActivityLog.builder()
                .username(event.getUsername())
                .activity(event.getActivityType())
                .metadata(event.getMetadata())
                .timestamp(Instant.now())
                .build();
        repository.save(logEntry);
    }

    @Override
    public Page<UserActivityLog> searchActivities(UserActivitySearch searchDTO) {
        Sort.Direction direction = "desc".equalsIgnoreCase(searchDTO.getSortDirection())
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(),
                Sort.by(direction, searchDTO.getSortBy()));

        Specification<UserActivityLog> spec = searchStrategy.buildSpecification(searchDTO);
        return repository.findAll(spec, pageable);
    }

    @Override
    public List<UserActivityLog> getAllActivities() {
        return repository.findAll();
    }

    @Override
    public List<UserActivityLog> getActivitiesByUsername(String username) {
        return repository.findAll((root, query, cb) -> cb.equal(root.get("username"), username));
    }

    public List<UserActivityLog> getLogsByUsername(String username) {
        return repository.findAll((root, query, cb) -> cb.equal(root.get("username"), username));
    }


}

