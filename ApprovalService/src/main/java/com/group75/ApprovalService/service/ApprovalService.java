package com.group75.ApprovalService.service;

import com.group75.ApprovalService.entity.Approval;
import com.group75.ApprovalService.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovalService {
    private final ApprovalRepository approvalRepository;

    @Autowired
    public ApprovalService(ApprovalRepository approvalRepository) {
        this.approvalRepository = approvalRepository;
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
