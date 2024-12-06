package com.group75.ApprovalService.service;

import com.group75.ApprovalService.entity.Approval;
import com.group75.ApprovalService.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovalService {
    private final ApprovalRepository approvalRepository;
    private final RestTemplate restTemplate;

    private static final String EVENT_SERVICE_URL = "http://event-service:8082/api/events/details"; // Adjust URL accordingly
    private static final String USER_SERVICE_URL = "http://user-service:8082/api/users/role"; // Adjust URL accordingly

    @Autowired
    public ApprovalService(ApprovalRepository approvalRepository, RestTemplate restTemplate) {
        this.approvalRepository = approvalRepository;
        this.restTemplate = restTemplate;
    }

    public boolean isEventValid(String eventId) {
        String url = EVENT_SERVICE_URL + "?eventId=" + eventId; // Use the constant
        return Boolean.TRUE.equals(restTemplate.getForObject(url, Boolean.class)); // Ensure null-safety
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

    public boolean isStaffAuthorized(String staffId) {
        String url = USER_SERVICE_URL + "?userId=" + staffId;
        String role = restTemplate.getForObject(url, String.class);
        return "staff".equals(role);
    }
}
