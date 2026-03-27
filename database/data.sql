-- ============================================
-- Sample Data for Leave Management System
-- ============================================

-- Insert Departments
INSERT INTO departments (name, description) VALUES
('Human Resources', 'Human Resources Department'),
('Information Technology', 'Information Technology Department'),
('Finance', 'Finance Department'),
('Operations', 'Operations Department'),
('Sales', 'Sales Department');

-- Insert Employees
INSERT INTO employees (first_name, last_name, email, password, phone_number, role, department, designation, join_date, manager_id, active) VALUES
('John', 'Doe', 'john.doe@company.com', '$2a$10$ux6YP0JqNZr9Hd1J8.8dCO5T9Fhd4Zh9Y3G7K4L1M2N6O9P0Q1R', '9876543210', 'ADMIN', 'Human Resources', 'HR Manager', '2022-01-15', NULL, TRUE),
('Jane', 'Smith', 'jane.smith@company.com', '$2a$10$ux6YP0JqNZr9Hd1J8.8dCO5T9Fhd4Zh9Y3G7K4L1M2N6O9P0Q1R', '9876543211', 'MANAGER', 'Information Technology', 'IT Manager', '2022-02-20', 1, TRUE),
('Bob', 'Johnson', 'bob.johnson@company.com', '$2a$10$ux6YP0JqNZr9Hd1J8.8dCO5T9Fhd4Zh9Y3G7K4L1M2N6O9P0Q1R', '9876543212', 'EMPLOYEE', 'Information Technology', 'Software Developer', '2022-03-10', 2, TRUE),
('Alice', 'Williams', 'alice.williams@company.com', '$2a$10$ux6YP0JqNZr9Hd1J8.8dCO5T9Fhd4Zh9Y3G7K4L1M2N6O9P0Q1R', '9876543213', 'EMPLOYEE', 'Information Technology', 'Software Developer', '2022-04-05', 2, TRUE),
('David', 'Brown', 'david.brown@company.com', '$2a$10$ux6YP0JqNZr9Hd1J8.8dCO5T9Fhd4Zh9Y3G7K4L1M2N6O9P0Q1R', '9876543214', 'MANAGER', 'Finance', 'Finance Manager', '2022-05-12', 1, TRUE),
('Emma', 'Davis', 'emma.davis@company.com', '$2a$10$ux6YP0JqNZr9Hd1J8.8dCO5T9Fhd4Zh9Y3G7K4L1M2N6O9P0Q1R', '9876543215', 'EMPLOYEE', 'Finance', 'Accountant', '2022-06-08', 5, TRUE),
('Frank', 'Miller', 'frank.miller@company.com', '$2a$10$ux6YP0JqNZr9Hd1J8.8dCO5T9Fhd4Zh9Y3G7K4L1M2N6O9P0Q1R', '9876543216', 'HR', 'Human Resources', 'HR Executive', '2022-07-15', 1, TRUE);

-- Insert Leave Balances for 2024
INSERT INTO leave_balances (employee_id, leave_type, total_days, used_days, remaining_days, year) VALUES
-- Bob Johnson (Employee from IT)
(3, 'CASUAL', 12, 2, 10, 2024),
(3, 'SICK', 8, 1, 7, 2024),
(3, 'EARNED', 15, 0, 15, 2024),

-- Alice Williams (Employee from IT)
(4, 'CASUAL', 12, 0, 12, 2024),
(4, 'SICK', 8, 0, 8, 2024),
(4, 'EARNED', 15, 0, 15, 2024),

-- Emma Davis (Employee from Finance)
(6, 'CASUAL', 12, 3, 9, 2024),
(6, 'SICK', 8, 2, 6, 2024),
(6, 'EARNED', 15, 0, 15, 2024),

-- Managers and Admins
(1, 'CASUAL', 12, 0, 12, 2024),
(1, 'SICK', 8, 0, 8, 2024),
(1, 'EARNED', 15, 0, 15, 2024),
(2, 'CASUAL', 12, 1, 11, 2024),
(2, 'SICK', 8, 0, 8, 2024),
(2, 'EARNED', 15, 0, 15, 2024),
(5, 'CASUAL', 12, 0, 12, 2024),
(5, 'SICK', 8, 0, 8, 2024),
(5, 'EARNED', 15, 0, 15, 2024),
(7, 'CASUAL', 12, 0, 12, 2024),
(7, 'SICK', 8, 0, 8, 2024),
(7, 'EARNED', 15, 0, 15, 2024);

-- Insert Public Holidays for 2024
INSERT INTO holidays (name, date, description, year) VALUES
('New Year Day', '2024-01-01', 'New Year Day', 2024),
('Republic Day', '2024-01-26', 'Republic Day', 2024),
('Good Friday', '2024-03-29', 'Good Friday', 2024),
('Easter Monday', '2024-04-01', 'Easter Monday', 2024),
('ANZAC Day', '2024-04-25', 'ANZAC Day', 2024),
('Queen\'s Birthday', '2024-06-10', 'Queen\'s Birthday', 2024),
('Independence Day', '2024-07-04', 'Independence Day (Optional)', 2024),
('Christmas Day', '2024-12-25', 'Christmas Day', 2024),
('Boxing Day', '2024-12-26', 'Boxing Day', 2024);

-- Sample Leave Request (Pending)
INSERT INTO leave_requests (employee_id, leave_type, start_date, end_date, reason, status, number_of_days, is_working_days) VALUES
(3, 'CASUAL', DATE_ADD(CURDATE(), INTERVAL 7 DAY), DATE_ADD(CURDATE(), INTERVAL 10 DAY), 'Personal vacation', 'PENDING', 4, TRUE);

-- Sample Leave Request (Approved)
INSERT INTO leave_requests (employee_id, leave_type, start_date, end_date, reason, status, approved_by, number_of_days, is_working_days, approved_at) VALUES
(4, 'SICK', DATE_ADD(CURDATE(), INTERVAL 2 DAY), DATE_ADD(CURDATE(), INTERVAL 3 DAY), 'Medical appointment', 'APPROVED', 2, 2, TRUE, NOW());

-- Insert sample notifications
INSERT INTO notifications (employee_id, title, message, type, is_read, related_entity_id) VALUES
(3, 'Leave Request Submitted', 'Your leave request has been submitted for approval', 'LEAVE_REQUEST_SUBMITTED', FALSE, '1'),
(2, 'New Leave Request', 'Bob Johnson has requested leave for approval', 'NEW_LEAVE_REQUEST_FOR_APPROVAL', FALSE, '1'),
(4, 'Leave Request Approved', 'Your leave request has been approved', 'LEAVE_REQUEST_APPROVED', TRUE, '2'),
(3, 'Low Leave Balance', 'Your casual leave balance is running low', 'LEAVE_BALANCE_LOW', FALSE, NULL);

-- Insert sample audit logs
INSERT INTO audit_logs (employee_id, action, entity_type, entity_id, old_values, new_values, ip_address) VALUES
(1, 'CREATE', 'LEAVE_REQUEST', 1, NULL, 'employee_id=3, leave_type=CASUAL, status=PENDING', '192.168.1.1'),
(2, 'APPROVE', 'LEAVE_REQUEST', 2, 'status=PENDING', 'status=APPROVED', '192.168.1.2'),
(1, 'CREATE', 'EMPLOYEE', 3, NULL, 'email=bob.johnson@company.com, role=EMPLOYEE', '192.168.1.1');
