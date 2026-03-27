package com.lms.repository;

import com.lms.model.LeaveRequest;
import com.lms.model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);
    List<LeaveRequest> findByEmployeeIdAndStatus(Long employeeId, LeaveStatus status);
    List<LeaveRequest> findByStatus(LeaveStatus status);
    
    @Query("SELECT l FROM LeaveRequest l WHERE l.employee.manager.id = :managerId AND l.status = 'PENDING'")
    List<LeaveRequest> findPendingLeavesByManagerId(Long managerId);
    
    @Query("SELECT l FROM LeaveRequest l WHERE l.employee.id = :employeeId AND l.startDate <= :endDate AND l.endDate >= :startDate AND l.status IN ('APPROVED', 'PENDING')")
    List<LeaveRequest> findOverlappingLeaves(Long employeeId, LocalDate startDate, LocalDate endDate);
}
