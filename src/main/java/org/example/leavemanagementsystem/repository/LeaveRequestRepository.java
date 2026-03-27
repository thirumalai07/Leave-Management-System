package org.example.leavemanagementsystem.repository;

import org.example.leavemanagementsystem.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
    
    List<LeaveRequest> findByEmployeeId(Integer employeeId);
    
    List<LeaveRequest> findByStatus(LeaveRequest.LeaveStatus status);
    
    List<LeaveRequest> findByEmployeeIdAndStatus(Integer employeeId, LeaveRequest.LeaveStatus status);
    
    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employee.id = :employeeId AND lr.startDate <= :endDate AND lr.endDate >= :startDate")
    List<LeaveRequest> findOverlappingLeaves(@Param("employeeId") Integer employeeId, 
                                             @Param("startDate") LocalDate startDate, 
                                             @Param("endDate") LocalDate endDate);
    
    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.employee.manager.id = :managerId AND lr.status = :status")
    List<LeaveRequest> findPendingLeavesByManager(@Param("managerId") Integer managerId, 
                                                   @Param("status") LeaveRequest.LeaveStatus status);
    
    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.startDate BETWEEN :startDate AND :endDate")
    List<LeaveRequest> findLeavesBetweenDates(@Param("startDate") LocalDate startDate, 
                                              @Param("endDate") LocalDate endDate);
}
