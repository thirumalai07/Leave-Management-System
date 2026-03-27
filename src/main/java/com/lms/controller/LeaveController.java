package com.lms.controller;

import com.lms.dto.LeaveRequestDTO;
import com.lms.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {
    
    private final LeaveService leaveService;
    
    @PostMapping("/apply/{employeeId}")
    public ResponseEntity<?> applyLeave(@PathVariable Long employeeId, @RequestBody LeaveRequestDTO leaveRequestDTO) {
        try {
            LeaveRequestDTO appliedLeave = leaveService.applyLeave(leaveRequestDTO, employeeId);
            return ResponseEntity.status(HttpStatus.CREATED).body(appliedLeave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error applying leave: " + e.getMessage());
        }
    }
    
    @PutMapping("/{leaveId}/approve/{approverId}")
    public ResponseEntity<?> approveLeave(@PathVariable Long leaveId, @PathVariable Long approverId) {
        try {
            LeaveRequestDTO approvedLeave = leaveService.approveLeave(leaveId, approverId);
            return ResponseEntity.ok(approvedLeave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error approving leave: " + e.getMessage());
        }
    }
    
    @PutMapping("/{leaveId}/reject/{approverId}")
    public ResponseEntity<?> rejectLeave(@PathVariable Long leaveId, @PathVariable Long approverId, 
                                         @RequestParam String reason) {
        try {
            LeaveRequestDTO rejectedLeave = leaveService.rejectLeave(leaveId, approverId, reason);
            return ResponseEntity.ok(rejectedLeave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error rejecting leave: " + e.getMessage());
        }
    }
    
    @PutMapping("/{leaveId}/cancel")
    public ResponseEntity<?> cancelLeave(@PathVariable Long leaveId) {
        try {
            LeaveRequestDTO cancelledLeave = leaveService.cancelLeave(leaveId);
            return ResponseEntity.ok(cancelledLeave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error cancelling leave: " + e.getMessage());
        }
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequestDTO>> getLeavesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getLeavesByEmployee(employeeId));
    }
    
    @GetMapping("/manager/{managerId}/pending")
    public ResponseEntity<List<LeaveRequestDTO>> getPendingLeavesByManager(@PathVariable Long managerId) {
        return ResponseEntity.ok(leaveService.getPendingLeavesByManager(managerId));
    }
    
    @GetMapping
    public ResponseEntity<List<LeaveRequestDTO>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }
}
