package com.group75.ApprovalService.service;

import com.group75.ApprovalService.entity.Approval;
import com.group75.ApprovalService.repository.ApprovalRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group75.ApprovalService.client.EventServiceClient;
import com.group75.ApprovalService.client.UserServiceClient;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final EventServiceClient eventServiceClient;
    private final UserServiceClient userServiceClient;

    @Autowired
    public ApprovalService(
            ApprovalRepository approvalRepository,
            EventServiceClient eventServiceClient,
            UserServiceClient userServiceClient) {
        this.approvalRepository = approvalRepository;
        this.eventServiceClient = eventServiceClient;
        this.userServiceClient = userServiceClient;
    }

    @CircuitBreaker(name = "eventservice", fallbackMethod = "eventValidationFallback")
    public boolean isEventValid(String eventId) {
        return eventServiceClient.isEventValid(eventId);
    }

    public boolean eventValidationFallback(String eventId, Throwable t) {
        return false;
    }

    @CircuitBreaker(name = "userservice", fallbackMethod = "userAuthorizationFallback")
    public boolean isStaffAuthorized(String staffId) {
        String role = userServiceClient.fetchUserRole(Long.parseLong(staffId));
        return "staff".equalsIgnoreCase(role);
    }

    public boolean userAuthorizationFallback(String staffId, Throwable t) {
        return false;
    }

    public List<Approval> getAllApprovals() {
        return approvalRepository.findAll();
    }

    public Optional<Approval> getApprovalById(String id) {
        return approvalRepository.findById(id);
    }

    public Approval createApproval(Approval approval) {
        return approvalRepository.save(approval);
    }

    public Approval updateApproval(String id, Approval approvalDetails) {
        approvalDetails.setId(id);
        return approvalRepository.save(approvalDetails);
    }

    public void deleteApproval(String id) {
        approvalRepository.deleteById(id);
    }

    public List<Approval> findApprovalsByStatus(String status) {
        return approvalRepository.findByStatus(status);
    }
}