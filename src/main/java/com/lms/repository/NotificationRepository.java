package com.lms.repository;

import com.lms.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByEmployeeId(Long employeeId);
    List<Notification> findByEmployeeIdAndIsReadFalse(Long employeeId);
    Integer countByEmployeeIdAndIsReadFalse(Long employeeId);
}
