package com.group75.ApprovalService.controller;

import com.group75.ApprovalService.entity.Approval;
import com.group75.ApprovalService.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/approvals")
public class ApprovalServiceController {
    private final ApprovalService approvalService;

    @Autowired
    public ApprovalServiceController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping
    public List<Approval> getAllApprovals() {
        return approvalService.getAllApprovals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Approval> getApprovalById(@PathVariable String id) {
        Optional<Approval> approval = approvalService.getApprovalById(id);
        return approval.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public String approveEvent(@RequestBody Approval approval) {if (approvalService.isEventValid(approval.getEventId()) && approvalService.isStaffAuthorized(approval.getUserId())) {
            approvalService.createApproval(approval);
            return "Event approved";
        }
        return "Event approval failed";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Approval> updateApproval(@PathVariable String id, @RequestBody Approval approvalDetails) {
        return ResponseEntity.ok(approvalService.updateApproval(id, approvalDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApproval(@PathVariable String id) {
        approvalService.deleteApproval(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public List<Approval> getApprovalsByStatus(@PathVariable String status) {
        return approvalService.findApprovalsByStatus(status);
    }
}
