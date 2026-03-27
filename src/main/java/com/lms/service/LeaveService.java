package com.lms.service;

import com.lms.dto.LeaveRequestDTO;
import com.lms.exception.ResourceNotFoundException;
import com.lms.mapper.EntityMapper;
import com.lms.model.*;
import com.lms.repository.LeaveBalanceRepository;
import com.lms.repository.LeaveRepository;
import com.lms.repository.EmployeeRepository;
import com.lms.validator.LeaveValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveService {
    
    private final LeaveRepository leaveRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;
    private final EmployeeRepository employeeRepository;
    private final EntityMapper entityMapper;
    private final LeaveValidator leaveValidator;
    private final NotificationService notificationService;
    
    public LeaveRequestDTO applyLeave(LeaveRequestDTO leaveRequestDTO, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        
        // Get leave balance
        LeaveBalance leaveBalance = leaveBalanceRepository
                .findByEmployeeIdAndLeaveTypeAndYear(
                        employeeId,
                        LeaveType.valueOf(leaveRequestDTO.getLeaveType()),
                        java.time.Year.now().getValue()
                )
                .orElseThrow(() -> new ResourceNotFoundException("Leave balance not found"));
        
        // Convert DTO to entity
        LeaveRequest leaveRequest = entityMapper.toLeaveRequest(leaveRequestDTO);
        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(LeaveType.valueOf(leaveRequestDTO.getLeaveType()));
        leaveRequest.setStatus(LeaveStatus.PENDING);
        
        // Calculate days
        if (Boolean.TRUE.equals(leaveRequestDTO.getIsWorkingDays())) {
            leaveRequest.setNumberOfDays(
                leaveValidator.calculateWorkingDays(leaveRequestDTO.getStartDate(), leaveRequestDTO.getEndDate())
            );
        } else {
            leaveRequest.setNumberOfDays(
                leaveValidator.calculateCalendarDays(leaveRequestDTO.getStartDate(), leaveRequestDTO.getEndDate())
            );
        }
        
        // Get overlapping leaves
        List<LeaveRequest> overlappingLeaves = leaveRepository.findOverlappingLeaves(
                employeeId,
                leaveRequestDTO.getStartDate(),
                leaveRequestDTO.getEndDate()
        );
        
        // Validate leave request
        leaveValidator.validateLeaveRequest(leaveRequest, leaveBalance, overlappingLeaves);
        
        // Save leave request
        LeaveRequest savedLeaveRequest = leaveRepository.save(leaveRequest);
        
        // Send notification to manager
        if (employee.getManager() != null) {
            notificationService.createNotification(
                    employee.getManager(),
                    "New Leave Request",
                    employee.getFirstName() + " " + employee.getLastName() + " has requested " + 
                    leaveRequest.getNumberOfDays() + " days leave",
                    NotificationType.NEW_LEAVE_REQUEST_FOR_APPROVAL,
                    String.valueOf(savedLeaveRequest.getId())
            );
        }
        
        return entityMapper.toLeaveRequestDTO(savedLeaveRequest);
    }
    
    public LeaveRequestDTO approveLeave(Long leaveRequestId, Long approverId) {
        LeaveRequest leaveRequest = leaveRepository.findById(leaveRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
        
        Employee approver = employeeRepository.findById(approverId)
                .orElseThrow(() -> new ResourceNotFoundException("Approver not found"));
        
        leaveRequest.setStatus(LeaveStatus.APPROVED);
        leaveRequest.setApprovedBy(approver);
        leaveRequest.setApprovedAt(LocalDateTime.now());
        
        // Update leave balance
        LeaveBalance leaveBalance = leaveBalanceRepository
                .findByEmployeeIdAndLeaveTypeAndYear(
                        leaveRequest.getEmployee().getId(),
                        leaveRequest.getLeaveType(),
                        java.time.Year.now().getValue()
                )
                .orElseThrow(() -> new ResourceNotFoundException("Leave balance not found"));
        
        leaveBalance.setUsedDays(leaveBalance.getUsedDays() + leaveRequest.getNumberOfDays());
        leaveBalanceRepository.save(leaveBalance);
        
        LeaveRequest savedLeaveRequest = leaveRepository.save(leaveRequest);
        
        // Send notification to employee
        notificationService.createNotification(
                leaveRequest.getEmployee(),
                "Leave Request Approved",
                "Your leave request from " + leaveRequest.getStartDate() + " to " + leaveRequest.getEndDate() + " has been approved",
                NotificationType.LEAVE_REQUEST_APPROVED,
                String.valueOf(savedLeaveRequest.getId())
        );
        
        return entityMapper.toLeaveRequestDTO(savedLeaveRequest);
    }
    
    public LeaveRequestDTO rejectLeave(Long leaveRequestId, Long approverId, String rejectionReason) {
        LeaveRequest leaveRequest = leaveRepository.findById(leaveRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
        
        Employee approver = employeeRepository.findById(approverId)
                .orElseThrow(() -> new ResourceNotFoundException("Approver not found"));
        
        leaveRequest.setStatus(LeaveStatus.REJECTED);
        leaveRequest.setApprovedBy(approver);
        leaveRequest.setRejectionReason(rejectionReason);
        leaveRequest.setApprovedAt(LocalDateTime.now());
        
        LeaveRequest savedLeaveRequest = leaveRepository.save(leaveRequest);
        
        // Send notification to employee
        notificationService.createNotification(
                leaveRequest.getEmployee(),
                "Leave Request Rejected",
                "Your leave request has been rejected. Reason: " + rejectionReason,
                NotificationType.LEAVE_REQUEST_REJECTED,
                String.valueOf(savedLeaveRequest.getId())
        );
        
        return entityMapper.toLeaveRequestDTO(savedLeaveRequest);
    }
    
    public LeaveRequestDTO cancelLeave(Long leaveRequestId) {
        LeaveRequest leaveRequest = leaveRepository.findById(leaveRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
        
        if (!leaveRequest.getStatus().equals(LeaveStatus.APPROVED)) {
            throw new IllegalArgumentException("Only approved leaves can be cancelled");
        }
        
        leaveRequest.setStatus(LeaveStatus.CANCELLED);
        
        // Refund leave balance
        LeaveBalance leaveBalance = leaveBalanceRepository
                .findByEmployeeIdAndLeaveTypeAndYear(
                        leaveRequest.getEmployee().getId(),
                        leaveRequest.getLeaveType(),
                        java.time.Year.now().getValue()
                )
                .orElseThrow(() -> new ResourceNotFoundException("Leave balance not found"));
        
        leaveBalance.setUsedDays(Math.max(0, leaveBalance.getUsedDays() - leaveRequest.getNumberOfDays()));
        leaveBalanceRepository.save(leaveBalance);
        
        LeaveRequest savedLeaveRequest = leaveRepository.save(leaveRequest);
        
        // Send notification
        notificationService.createNotification(
                leaveRequest.getEmployee(),
                "Leave Request Cancelled",
                "Your leave request from " + leaveRequest.getStartDate() + " to " + leaveRequest.getEndDate() + " has been cancelled",
                NotificationType.LEAVE_REQUEST_CANCELLED,
                String.valueOf(savedLeaveRequest.getId())
        );
        
        return entityMapper.toLeaveRequestDTO(savedLeaveRequest);
    }
    
    public List<LeaveRequestDTO> getLeavesByEmployee(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId)
                .stream()
                .map(entityMapper::toLeaveRequestDTO)
                .collect(Collectors.toList());
    }
    
    public List<LeaveRequestDTO> getPendingLeavesByManager(Long managerId) {
        return leaveRepository.findPendingLeavesByManagerId(managerId)
                .stream()
                .map(entityMapper::toLeaveRequestDTO)
                .collect(Collectors.toList());
    }
    
    public List<LeaveRequestDTO> getAllLeaves() {
        return leaveRepository.findAll()
                .stream()
                .map(entityMapper::toLeaveRequestDTO)
                .collect(Collectors.toList());
    }
}
