-- ================================
-- Sample Data for Leave Management System
-- ================================

-- Insert Departments
INSERT INTO department (name, description, created_at, updated_at) VALUES
('IT', 'Information Technology Department', NOW(), NOW()),
('HR', 'Human Resources Department', NOW(), NOW()),
('Finance', 'Finance and Accounting Department', NOW(), NOW()),
('Sales', 'Sales and Business Development', NOW(), NOW()),
('Operations', 'Operations and Management', NOW(), NOW());

-- Insert Employees (Admin)
INSERT INTO employee (name, email, role, department, manager_id, created_at, updated_at) VALUES
('John Admin', 'john.admin@company.com', 'ADMIN', 'HR', NULL, NOW(), NOW()),
('Sarah Admin', 'sarah.admin@company.com', 'ADMIN', 'HR', NULL, NOW(), NOW());

-- Insert Managers
INSERT INTO employee (name, email, role, department, manager_id, created_at, updated_at) VALUES
('Rajesh Kumar', 'rajesh.kumar@company.com', 'MANAGER', 'IT', 1, NOW(), NOW()),
('Priya Singh', 'priya.singh@company.com', 'MANAGER', 'Finance', 1, NOW(), NOW()),
('Amit Patel', 'amit.patel@company.com', 'MANAGER', 'Sales', 2, NOW(), NOW()),
('Neha Sharma', 'neha.sharma@company.com', 'MANAGER', 'Operations', 2, NOW(), NOW());

-- Insert Employees
INSERT INTO employee (name, email, role, department, manager_id, created_at, updated_at) VALUES
('Arun Kumar', 'arun.kumar@company.com', 'EMPLOYEE', 'IT', 3, NOW(), NOW()),
('Sneha Desai', 'sneha.desai@company.com', 'EMPLOYEE', 'IT', 3, NOW(), NOW()),
('Vikram Singh', 'vikram.singh@company.com', 'EMPLOYEE', 'IT', 3, NOW(), NOW()),
('Isha Verma', 'isha.verma@company.com', 'EMPLOYEE', 'Finance', 4, NOW(), NOW()),
('Rohan Das', 'rohan.das@company.com', 'EMPLOYEE', 'Finance', 4, NOW(), NOW()),
('Anjali Roy', 'anjali.roy@company.com', 'EMPLOYEE', 'Sales', 5, NOW(), NOW()),
('Vishal Kumar', 'vishal.kumar@company.com', 'EMPLOYEE', 'Sales', 5, NOW(), NOW()),
('Divya Nair', 'divya.nair@company.com', 'EMPLOYEE', 'Operations', 6, NOW(), NOW()),
('Suresh Gupta', 'suresh.gupta@company.com', 'EMPLOYEE', 'Operations', 6, NOW(), NOW());

-- Insert Leave Policies
INSERT INTO leave_policy (name, leave_type, total_days_per_year, carry_forward_days, is_active, created_at, updated_at) VALUES
('Paid Leave Policy', 'PAID', 20, 5, TRUE, NOW(), NOW()),
('Casual Leave Policy', 'CASUAL', 12, 2, TRUE, NOW(), NOW()),
('Sick Leave Policy', 'SICK', 10, 0, TRUE, NOW(), NOW());

-- Insert Leave Balances for Employees (IDs 7-15)
-- Arun Kumar
INSERT INTO leave_balance (employee_id, leave_type, total_allocated, used_leaves, remaining_leaves, created_at, updated_at) VALUES
(7, 'PAID', 20, 5, 15, NOW(), NOW()),
(7, 'CASUAL', 12, 2, 10, NOW(), NOW()),
(7, 'SICK', 10, 0, 10, NOW(), NOW());

-- Sneha Desai
INSERT INTO leave_balance (employee_id, leave_type, total_allocated, used_leaves, remaining_leaves, created_at, updated_at) VALUES
(8, 'PAID', 20, 8, 12, NOW(), NOW()),
(8, 'CASUAL', 12, 3, 9, NOW(), NOW()),
(8, 'SICK', 10, 2, 8, NOW(), NOW());

-- Vikram Singh
INSERT INTO leave_balance (employee_id, leave_type, total_allocated, used_leaves, remaining_leaves, created_at, updated_at) VALUES
(9, 'PAID', 20, 3, 17, NOW(), NOW()),
(9, 'CASUAL', 12, 1, 11, NOW(), NOW()),
(9, 'SICK', 10, 1, 9, NOW(), NOW());

-- Isha Verma
INSERT INTO leave_balance (employee_id, leave_type, total_allocated, used_leaves, remaining_leaves, created_at, updated_at) VALUES
(10, 'PAID', 20, 6, 14, NOW(), NOW()),
(10, 'CASUAL', 12, 2, 10, NOW(), NOW()),
(10, 'SICK', 10, 0, 10, NOW(), NOW());

-- Rohan Das
INSERT INTO leave_balance (employee_id, leave_type, total_allocated, used_leaves, remaining_leaves, created_at, updated_at) VALUES
(11, 'PAID', 20, 4, 16, NOW(), NOW()),
(11, 'CASUAL', 12, 4, 8, NOW(), NOW()),
(11, 'SICK', 10, 1, 9, NOW(), NOW());

-- Anjali Roy
INSERT INTO leave_balance (employee_id, leave_type, total_allocated, used_leaves, remaining_leaves, created_at, updated_at) VALUES
(12, 'PAID', 20, 2, 18, NOW(), NOW()),
(12, 'CASUAL', 12, 0, 12, NOW(), NOW()),
(12, 'SICK', 10, 0, 10, NOW(), NOW());

-- Vishal Kumar
INSERT INTO leave_balance (employee_id, leave_type, total_allocated, used_leaves, remaining_leaves, created_at, updated_at) VALUES
(13, 'PAID', 20, 7, 13, NOW(), NOW()),
(13, 'CASUAL', 12, 5, 7, NOW(), NOW()),
(13, 'SICK', 10, 2, 8, NOW(), NOW());

-- Divya Nair
INSERT INTO leave_balance (employee_id, leave_type, total_allocated, used_leaves, remaining_leaves, created_at, updated_at) VALUES
(14, 'PAID', 20, 9, 11, NOW(), NOW()),
(14, 'CASUAL', 12, 1, 11, NOW(), NOW()),
(14, 'SICK', 10, 3, 7, NOW(), NOW());

-- Suresh Gupta
INSERT INTO leave_balance (employee_id, leave_type, total_allocated, used_leaves, remaining_leaves, created_at, updated_at) VALUES
(15, 'PAID', 20, 5, 15, NOW(), NOW()),
(15, 'CASUAL', 12, 2, 10, NOW(), NOW()),
(15, 'SICK', 10, 1, 9, NOW(), NOW());

-- Insert Holidays
INSERT INTO holiday (name, holiday_date, is_optional, created_at) VALUES
('Republic Day', '2026-01-26', FALSE, NOW()),
('Holi', '2026-03-14', FALSE, NOW()),
('Independence Day', '2026-08-15', FALSE, NOW()),
('Gandhi Jayanti', '2026-10-02', FALSE, NOW()),
('Christmas', '2026-12-25', FALSE, NOW()),
('Diwali', '2026-11-01', FALSE, NOW()),
('New Year', '2026-01-01', FALSE, NOW()),
('Easter Monday', '2026-04-13', TRUE, NOW());

-- Insert Sample Leave Requests
-- Arun Kumar - PAID Leave (Approved)
INSERT INTO leave_request (employee_id, leave_type, start_date, end_date, total_days, reason, status, applied_at, approved_by, comments, created_at, updated_at) VALUES
(7, 'PAID', '2026-04-01', '2026-04-05', 5, 'Vacation', 'APPROVED', NOW(), 3, 'Approved - Enjoy your vacation', NOW(), NOW());

-- Sneha Desai - CASUAL Leave (Pending)
INSERT INTO leave_request (employee_id, leave_type, start_date, end_date, total_days, reason, status, applied_at, approved_by, comments, created_at, updated_at) VALUES
(8, 'CASUAL', '2026-05-10', '2026-05-12', 3, 'Personal work', 'PENDING', NOW(), NULL, NULL, NOW(), NOW());

-- Vikram Singh - SICK Leave (Approved)
INSERT INTO leave_request (employee_id, leave_type, start_date, end_date, total_days, reason, status, applied_at, approved_by, comments, created_at, updated_at) VALUES
(9, 'SICK', '2026-03-28', '2026-03-30', 2, 'Medical appointment', 'APPROVED', NOW(), 3, 'Approved - Get well soon', NOW(), NOW());

-- Isha Verma - PAID Leave (Rejected)
INSERT INTO leave_request (employee_id, leave_type, start_date, end_date, total_days, reason, status, applied_at, approved_by, comments, created_at, updated_at) VALUES
(10, 'PAID', '2026-06-01', '2026-06-15', 15, 'International trip', 'REJECTED', NOW(), 4, 'Unable to approve due to project deadline', NOW(), NOW());

-- Rohan Das - CASUAL Leave (Cancelled)
INSERT INTO leave_request (employee_id, leave_type, start_date, end_date, total_days, reason, status, applied_at, approved_by, comments, created_at, updated_at) VALUES
(11, 'CASUAL', '2026-07-20', '2026-07-22', 3, 'Family event', 'CANCELLED', NOW(), NULL, 'Cancelled as per employee request', NOW(), NOW());

-- Anjali Roy - PAID Leave (Pending)
INSERT INTO leave_request (employee_id, leave_type, start_date, end_date, total_days, reason, status, applied_at, approved_by, comments, created_at, updated_at) VALUES
(12, 'PAID', '2026-08-15', '2026-08-20', 6, 'Summer vacation', 'PENDING', NOW(), NULL, NULL, NOW(), NOW());

-- Insert Sample Notifications
INSERT INTO notification (user_id, message, type, is_read, created_at) VALUES
(7, 'Your leave request for 2026-04-01 to 2026-04-05 has been approved', 'SYSTEM', TRUE, NOW()),
(8, 'Your leave request for 2026-05-10 to 2026-05-12 is pending approval', 'SYSTEM', FALSE, NOW()),
(9, 'Your leave request for 2026-03-28 to 2026-03-30 has been approved', 'SYSTEM', TRUE, NOW()),
(10, 'Your leave request for 2026-06-01 to 2026-06-15 has been rejected', 'SYSTEM', TRUE, NOW()),
(12, 'Your leave request for 2026-08-15 to 2026-08-20 is pending approval', 'SYSTEM', FALSE, NOW()),
(3, 'You have 2 pending leave approvals', 'SYSTEM', FALSE, NOW());

-- Insert Sample Audit Logs
INSERT INTO audit_log (action, performed_by, target_id, entity_type, old_value, new_value, timestamp) VALUES
('APPROVED', 3, 1, 'LeaveRequest', 'PENDING', 'APPROVED', NOW()),
('CREATED', 8, 2, 'LeaveRequest', NULL, '{"leave_type":"CASUAL","startDate":"2026-05-10"}', NOW()),
('APPROVED', 3, 3, 'LeaveRequest', 'PENDING', 'APPROVED', NOW()),
('REJECTED', 4, 4, 'LeaveRequest', 'PENDING', 'REJECTED', NOW()),
('CANCELLED', 11, 5, 'LeaveRequest', 'PENDING', 'CANCELLED', NOW());

-- Display summary
SELECT 'Database initialized successfully!' AS 'Status';
SELECT COUNT(*) AS 'Total Employees' FROM employee;
SELECT COUNT(*) AS 'Total Leave Requests' FROM leave_request;
SELECT COUNT(*) AS 'Total Holidays' FROM holiday;
SELECT COUNT(*) AS 'Total Notifications' FROM notification;
