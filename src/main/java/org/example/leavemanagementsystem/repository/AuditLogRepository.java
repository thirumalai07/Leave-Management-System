package org.example.leavemanagementsystem.repository;

import org.example.leavemanagementsystem.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
    
    List<AuditLog> findByPerformedById(Integer performedById);
    
    List<AuditLog> findByTargetId(Integer targetId);
    
    List<AuditLog> findByAction(String action);
    
    List<AuditLog> findByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);
}
