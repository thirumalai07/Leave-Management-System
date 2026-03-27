-- ================================
-- Leave Management System Database Schema
-- ================================

-- Create Employee Table
CREATE TABLE IF NOT EXISTS employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role ENUM('EMPLOYEE', 'MANAGER', 'ADMIN') DEFAULT 'EMPLOYEE',
    department VARCHAR(50) NOT NULL,
    manager_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (manager_id) REFERENCES employee(id) ON DELETE SET NULL
);

-- Create LeaveRequest Table
CREATE TABLE IF NOT EXISTS leave_request (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    leave_type ENUM('SICK', 'CASUAL', 'PAID') NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_days INT NOT NULL,
    reason TEXT,
    status ENUM('PENDING', 'APPROVED', 'REJECTED', 'CANCELLED') DEFAULT 'PENDING',
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    approved_by INT,
    comments TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE,
    FOREIGN KEY (approved_by) REFERENCES employee(id) ON DELETE SET NULL,
    INDEX idx_employee_id (employee_id),
    INDEX idx_status (status),
    INDEX idx_start_date (start_date),
    INDEX idx_end_date (end_date)
);

-- Create LeaveBalance Table
CREATE TABLE IF NOT EXISTS leave_balance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    leave_type ENUM('SICK', 'CASUAL', 'PAID') NOT NULL,
    total_allocated INT DEFAULT 0,
    used_leaves INT DEFAULT 0,
    remaining_leaves INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE,
    UNIQUE KEY unique_employee_leave_type (employee_id, leave_type),
    INDEX idx_employee_id (employee_id)
);

-- Create Notification Table
CREATE TABLE IF NOT EXISTS notification (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    type ENUM('EMAIL', 'SYSTEM') DEFAULT 'SYSTEM',
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES employee(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_is_read (is_read)
);

-- Create AuditLog Table (Advanced Feature)
CREATE TABLE IF NOT EXISTS audit_log (
    id INT PRIMARY KEY AUTO_INCREMENT,
    action VARCHAR(100) NOT NULL,
    performed_by INT NOT NULL,
    target_id INT,
    entity_type VARCHAR(50),
    old_value TEXT,
    new_value TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (performed_by) REFERENCES employee(id) ON DELETE SET NULL,
    INDEX idx_performed_by (performed_by),
    INDEX idx_target_id (target_id),
    INDEX idx_timestamp (timestamp)
);

-- Create Holiday Table (Optional but Recommended)
CREATE TABLE IF NOT EXISTS holiday (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    holiday_date DATE NOT NULL,
    is_optional BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_holiday_date (holiday_date),
    INDEX idx_holiday_date (holiday_date)
);

-- Create Department Table (Normalization)
CREATE TABLE IF NOT EXISTS department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create LeavePolicy Table (New - For Advanced Features)
CREATE TABLE IF NOT EXISTS leave_policy (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    leave_type ENUM('SICK', 'CASUAL', 'PAID') NOT NULL,
    total_days_per_year INT NOT NULL,
    carry_forward_days INT DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY unique_leave_type (leave_type)
);

-- Create Indexes for Performance
CREATE INDEX idx_leave_request_employee_status ON leave_request(employee_id, status);
CREATE INDEX idx_leave_request_date_range ON leave_request(start_date, end_date);
CREATE INDEX idx_leave_balance_employee ON leave_balance(employee_id);
CREATE INDEX idx_notification_created ON notification(created_at);
