package com.lms.mapper;

import com.lms.dto.EmployeeDTO;
import com.lms.dto.LeaveRequestDTO;
import com.lms.model.Employee;
import com.lms.model.LeaveRequest;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {
    
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setRole(employee.getRole().toString());
        dto.setDepartment(employee.getDepartment());
        dto.setDesignation(employee.getDesignation());
        dto.setJoinDate(employee.getJoinDate());
        dto.setManagerId(employee.getManager() != null ? employee.getManager().getId() : null);
        dto.setActive(employee.getActive());
        
        return dto;
    }
    
    public Employee toEmployee(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setDepartment(dto.getDepartment());
        employee.setDesignation(dto.getDesignation());
        employee.setJoinDate(dto.getJoinDate());
        employee.setActive(dto.getActive());
        
        return employee;
    }
    
    public LeaveRequestDTO toLeaveRequestDTO(LeaveRequest leaveRequest) {
        if (leaveRequest == null) {
            return null;
        }
        
        LeaveRequestDTO dto = new LeaveRequestDTO();
        dto.setId(leaveRequest.getId());
        dto.setEmployeeId(leaveRequest.getEmployee().getId());
        dto.setLeaveType(leaveRequest.getLeaveType().toString());
        dto.setStartDate(leaveRequest.getStartDate());
        dto.setEndDate(leaveRequest.getEndDate());
        dto.setReason(leaveRequest.getReason());
        dto.setStatus(leaveRequest.getStatus().toString());
        dto.setApprovedBy(leaveRequest.getApprovedBy() != null ? leaveRequest.getApprovedBy().getId() : null);
        dto.setNumberOfDays(leaveRequest.getNumberOfDays());
        dto.setIsWorkingDays(leaveRequest.getIsWorkingDays());
        dto.setRejectionReason(leaveRequest.getRejectionReason());
        dto.setCreatedAt(leaveRequest.getCreatedAt());
        dto.setApprovedAt(leaveRequest.getApprovedAt());
        
        return dto;
    }
    
    public LeaveRequest toLeaveRequest(LeaveRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setId(dto.getId());
        leaveRequest.setStartDate(dto.getStartDate());
        leaveRequest.setEndDate(dto.getEndDate());
        leaveRequest.setReason(dto.getReason());
        leaveRequest.setNumberOfDays(dto.getNumberOfDays());
        leaveRequest.setIsWorkingDays(dto.getIsWorkingDays());
        
        return leaveRequest;
    }
}
