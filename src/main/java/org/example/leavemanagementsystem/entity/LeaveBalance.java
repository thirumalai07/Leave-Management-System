package org.example.leavemanagementsystem.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_balance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "employee")
@ToString(exclude = "employee")
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveType leaveType;

    @Column(nullable = false)
    private Integer totalAllocated;

    @Column(nullable = false)
    private Integer usedLeaves;

    @Column(nullable = false)
    private Integer remainingLeaves;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        usedLeaves = usedLeaves != null ? usedLeaves : 0;
        remainingLeaves = totalAllocated - usedLeaves;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        remainingLeaves = totalAllocated - usedLeaves;
    }

    public enum LeaveType {
        SICK, CASUAL, PAID
    }
}
