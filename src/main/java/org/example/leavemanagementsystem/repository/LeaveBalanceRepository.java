package org.example.leavemanagementsystem.repository;

import org.example.leavemanagementsystem.entity.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Integer> {
    
    List<LeaveBalance> findByEmployeeId(Integer employeeId);
    
    Optional<LeaveBalance> findByEmployeeIdAndLeaveType(Integer employeeId, LeaveBalance.LeaveType leaveType);
}
