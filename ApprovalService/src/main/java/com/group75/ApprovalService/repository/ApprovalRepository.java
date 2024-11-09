package com.group75.ApprovalService.repository;

import com.group75.ApprovalService.entity.Approval;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ApprovalRepository extends MongoRepository<Approval, String> {
    List<Approval> findByStatus(String status); // e.g., find approvals by status ("Pending", "Approved", etc.)
    List<Approval> findByEventId(String eventId); // Find all approvals for a specific event
}
