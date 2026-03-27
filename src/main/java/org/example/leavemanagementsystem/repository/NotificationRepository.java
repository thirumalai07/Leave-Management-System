package org.example.leavemanagementsystem.repository;

import org.example.leavemanagementsystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    
    List<Notification> findByUserId(Integer userId);
    
    List<Notification> findByUserIdAndIsReadFalse(Integer userId);
    
    long countByUserIdAndIsReadFalse(Integer userId);
}
