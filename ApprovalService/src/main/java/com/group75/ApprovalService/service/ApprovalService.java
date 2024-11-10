package com.group75.ApprovalService.service;

import com.group75.ApprovalService.entity.Approval;
import com.group75.ApprovalService.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group75.ApprovalService.model.Approval;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MongoDBContainer;


import java.util.List;
import java.util.Optional;

@Service
public class ApprovalService {
    private final ApprovalRepository approvalRepository;

    @Autowired
    public ApprovalService(ApprovalRepository approvalRepository) {
        this.approvalRepository = approvalRepository;

        @Autowired
        private ApprovalRepository approvalRepository;

        @Autowired
        private RestTemplate restTemplate;

        private static final String EVENT_SERVICE_URL = "http://event-service/api/events/details"; // Adjust URL accordingly
        private static final String USER_SERVICE_URL = "http://user-service/api/users/role"; // Adjust URL accordingly

        public boolean isEventValid(String eventId) {
            // Call EventService to fetch event details and validate
            String url = EVENT_SERVICE_URL + "?eventId=" + eventId;
            return restTemplate.getForObject(url, Boolean.class); // EventService should return event validity
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

        public void save(Approval approval) {
            approvalRepository.save(approval);
        }
        @SpringBootTest
        public class BookingServiceIntegrationTest {

            static MongoDBContainer mongoDB = new MongoDBContainer("mongo:latest");

            @BeforeAll
            static void startContainer() {
                mongoDB.start();
            }

            @DynamicPropertySource
            static void databaseProperties(DynamicPropertyRegistry registry) {
                registry.add("spring.data.mongodb.uri", mongoDB::getReplicaSetUrl);
            }
}
