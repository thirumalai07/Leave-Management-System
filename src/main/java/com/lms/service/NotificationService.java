package com.lms.service;

import com.lms.model.*;
import com.lms.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    
    private final NotificationRepository notificationRepository;
    
    public Notification createNotification(Employee employee, String title, String message, 
                                          NotificationType type, String relatedEntityId) {
        Notification notification = new Notification();
        notification.setEmployee(employee);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setIsRead(false);
        notification.setRelatedEntityId(relatedEntityId);
        
        return notificationRepository.save(notification);
    }
    
    public List<Notification> getUnreadNotifications(Long employeeId) {
        return notificationRepository.findByEmployeeIdAndIsReadFalse(employeeId);
    }
    
    public Integer getUnreadNotificationCount(Long employeeId) {
        return notificationRepository.countByEmployeeIdAndIsReadFalse(employeeId);
    }
    
    public Notification markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        notification.setReadAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }
    
    public List<Notification> getEmployeeNotifications(Long employeeId) {
        return notificationRepository.findByEmployeeId(employeeId);
    }
}
