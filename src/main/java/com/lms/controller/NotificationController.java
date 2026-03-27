package com.lms.controller;

import com.lms.service.NotificationService;
import com.lms.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    
    private final NotificationService notificationService;
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long employeeId) {
        return ResponseEntity.ok(notificationService.getEmployeeNotifications(employeeId));
    }
    
    @GetMapping("/employee/{employeeId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable Long employeeId) {
        return ResponseEntity.ok(notificationService.getUnreadNotifications(employeeId));
    }
    
    @GetMapping("/employee/{employeeId}/unread/count")
    public ResponseEntity<Integer> getUnreadCount(@PathVariable Long employeeId) {
        return ResponseEntity.ok(notificationService.getUnreadNotificationCount(employeeId));
    }
    
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long notificationId) {
        return ResponseEntity.ok(notificationService.markAsRead(notificationId));
    }
}
