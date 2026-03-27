package com.lms.validator;

import com.lms.exception.InsufficientLeaveBalanceException;
import com.lms.exception.LeaveConflictException;
import com.lms.model.LeaveBalance;
import com.lms.model.LeaveRequest;
import com.lms.model.Holiday;
import com.lms.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LeaveValidator {
    
    private final HolidayRepository holidayRepository;
    
    public void validateLeaveRequest(LeaveRequest leaveRequest, LeaveBalance leaveBalance, List<LeaveRequest> overlappingLeaves) {
        
        // Validate dates
        if (leaveRequest.getEndDate().isBefore(leaveRequest.getStartDate())) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        
        // Validate that leave is not in the past
        if (leaveRequest.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot apply for leave in the past");
        }
        
        // Check for overlapping leaves
        if (!overlappingLeaves.isEmpty()) {
            throw new LeaveConflictException("Leave dates overlap with existing leave request");
        }
        
        // Check if sufficient balance available
        if (leaveBalance.getRemainingDays() < leaveRequest.getNumberOfDays()) {
            throw new InsufficientLeaveBalanceException(
                "Insufficient leave balance. Required: " + leaveRequest.getNumberOfDays() + 
                ", Available: " + leaveBalance.getRemainingDays()
            );
        }
    }
    
    public Integer calculateWorkingDays(LocalDate startDate, LocalDate endDate) {
        int workingDays = 0;
        LocalDate current = startDate;
        
        while (!current.isAfter(endDate)) {
            DayOfWeek dayOfWeek = current.getDayOfWeek();
            
            // Check if it's a weekend (Saturday = 6, Sunday = 7)
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                // Check if it's a holiday
                Holiday holiday = holidayRepository.findByDate(current).orElse(null);
                if (holiday == null) {
                    workingDays++;
                }
            }
            
            current = current.plusDays(1);
        }
        
        return workingDays;
    }
    
    public Integer calculateCalendarDays(LocalDate startDate, LocalDate endDate) {
        return (int) (endDate.toEpochDay() - startDate.toEpochDay() + 1);
    }
}
