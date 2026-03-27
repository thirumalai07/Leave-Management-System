package org.example.leavemanagementsystem.repository;

import org.example.leavemanagementsystem.entity.LeavePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeavePolicyRepository extends JpaRepository<LeavePolicy, Integer> {
    
    Optional<LeavePolicy> findByLeaveType(LeavePolicy.LeaveType leaveType);
    
    List<LeavePolicy> findByIsActiveTrue();
}
