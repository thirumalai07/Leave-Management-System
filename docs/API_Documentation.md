# API Documentation - Leave Management System

## Base URL
```
http://localhost:8080/api
```

## Authentication
All endpoints (except `/auth/*`) require a JWT token in the Authorization header:
```
Authorization: Bearer <token>
```

---

## 1. Authentication Endpoints

### 1.1 Register
- **Endpoint:** `POST /auth/register`
- **Description:** Register a new employee
- **Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@company.com",
  "password": "password123",
  "phoneNumber": "9876543210",
  "role": "EMPLOYEE",
  "department": "IT",
  "designation": "Software Developer",
  "joinDate": "2024-01-15"
}
```
- **Response (201 Created):**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@company.com",
  "phoneNumber": "9876543210",
  "role": "EMPLOYEE",
  "department": "IT",
  "designation": "Software Developer",
  "joinDate": "2024-01-15",
  "active": true
}
```

### 1.2 Login
- **Endpoint:** `POST /auth/login`
- **Description:** Authenticate user and get JWT token
- **Request Body:**
```json
{
  "email": "john.doe@company.com",
  "password": "password123"
}
```
- **Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9...",
  "email": "john.doe@company.com",
  "id": 1
}
```

### 1.3 Refresh Token
- **Endpoint:** `POST /auth/refresh`
- **Description:** Refresh JWT token
- **Headers:** `Authorization: Bearer <token>`
- **Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9..."
}
```

---

## 2. Employee Endpoints

### 2.1 Get Employee by ID
- **Endpoint:** `GET /employees/{id}`
- **Description:** Get employee details by ID
- **Response (200 OK):** Employee object

### 2.2 Get All Employees
- **Endpoint:** `GET /employees`
- **Description:** Get all employees
- **Response (200 OK):** Array of employee objects

### 2.3 Get Active Employees
- **Endpoint:** `GET /employees/active`
- **Description:** Get all active employees
- **Response (200 OK):** Array of active employee objects

### 2.4 Get Employee by Email
- **Endpoint:** `GET /employees/email/{email}`
- **Description:** Get employee by email
- **Response (200 OK):** Employee object

### 2.5 Update Employee
- **Endpoint:** `PUT /employees/{id}`
- **Description:** Update employee details
- **Request Body:**
```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "phoneNumber": "9876543210",
  "department": "HR",
  "designation": "HR Manager"
}
```
- **Response (200 OK):** Updated employee object

### 2.6 Delete Employee
- **Endpoint:** `DELETE /employees/{id}`
- **Description:** Delete (deactivate) employee
- **Response (204 No Content)**

---

## 3. Leave Request Endpoints

### 3.1 Apply for Leave
- **Endpoint:** `POST /leaves/apply/{employeeId}`
- **Description:** Apply for leave
- **Request Body:**
```json
{
  "leaveType": "CASUAL",
  "startDate": "2024-02-15",
  "endDate": "2024-02-17",
  "reason": "Personal vacation",
  "numberOfDays": 3,
  "isWorkingDays": true
}
```
- **Response (201 Created):** Leave request object

### 3.2 Approve Leave
- **Endpoint:** `PUT /leaves/{leaveId}/approve/{approverId}`
- **Description:** Approve a leave request (by manager/admin)
- **Response (200 OK):** Updated leave request object

### 3.3 Reject Leave
- **Endpoint:** `PUT /leaves/{leaveId}/reject/{approverId}?reason=reason_text`
- **Description:** Reject a leave request
- **Query Parameters:** `reason` - reason for rejection
- **Response (200 OK):** Updated leave request object

### 3.4 Cancel Leave
- **Endpoint:** `PUT /leaves/{leaveId}/cancel`
- **Description:** Cancel an approved leave request
- **Response (200 OK):** Cancelled leave request object

### 3.5 Get Leaves by Employee
- **Endpoint:** `GET /leaves/employee/{employeeId}`
- **Description:** Get all leave requests for an employee
- **Response (200 OK):** Array of leave request objects

### 3.6 Get Pending Leaves for Manager
- **Endpoint:** `GET /leaves/manager/{managerId}/pending`
- **Description:** Get all pending leave requests for subordinates of a manager
- **Response (200 OK):** Array of pending leave request objects

### 3.7 Get All Leaves
- **Endpoint:** `GET /leaves`
- **Description:** Get all leave requests (admin only)
- **Response (200 OK):** Array of all leave request objects

---

## 4. Notification Endpoints

### 4.1 Get Employee Notifications
- **Endpoint:** `GET /notifications/employee/{employeeId}`
- **Description:** Get all notifications for an employee
- **Response (200 OK):** Array of notification objects

### 4.2 Get Unread Notifications
- **Endpoint:** `GET /notifications/employee/{employeeId}/unread`
- **Description:** Get unread notifications for an employee
- **Response (200 OK):** Array of unread notification objects

### 4.3 Get Unread Notification Count
- **Endpoint:** `GET /notifications/employee/{employeeId}/unread/count`
- **Description:** Get count of unread notifications
- **Response (200 OK):**
```json
5
```

### 4.4 Mark Notification as Read
- **Endpoint:** `PUT /notifications/{notificationId}/read`
- **Description:** Mark a notification as read
- **Response (200 OK):** Updated notification object

---

## 5. Admin Endpoints

### 5.1 Get All Employees (Admin)
- **Endpoint:** `GET /admin/employees`
- **Description:** Get all employees (admin only)
- **Response (200 OK):** Array of employee objects

### 5.2 Get Employees by Role
- **Endpoint:** `GET /admin/employees/role/{role}`
- **Description:** Get employees by role (EMPLOYEE, MANAGER, ADMIN, HR)
- **Response (200 OK):** Array of employee objects

### 5.3 Create Employee (Admin)
- **Endpoint:** `POST /admin/employees`
- **Description:** Create a new employee (admin only)
- **Request Body:** Employee object
- **Response (200 OK):** Created employee object

---

## Error Responses

### 400 Bad Request
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Error description",
  "path": "/api/endpoint"
}
```

### 404 Not Found
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found",
  "path": "/api/endpoint"
}
```

### 401 Unauthorized
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid token",
  "path": "/api/endpoint"
}
```

### 409 Conflict
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 409,
  "error": "Conflict",
  "message": "Leave dates overlap with existing leave request",
  "path": "/api/leaves/apply/1"
}
```

---

## Leave Types
- `CASUAL` - Casual leave
- `SICK` - Sick leave
- `EARNED` - Earned leave
- `MATERNITY` - Maternity leave
- `PATERNITY` - Paternity leave
- `UNPAID` - Unpaid leave
- `PUBLIC_HOLIDAY` - Public holiday

## Employee Roles
- `EMPLOYEE` - Regular employee
- `MANAGER` - Manager/Team lead
- `ADMIN` - System administrator
- `HR` - HR personnel

## Leave Status
- `PENDING` - Awaiting approval
- `APPROVED` - Approved by manager
- `REJECTED` - Rejected by manager
- `CANCELLED` - Cancelled by employee or admin

</ Notification Types
- `LEAVE_REQUEST_SUBMITTED` - Leave request created
- `LEAVE_REQUEST_APPROVED` - Leave approved
- `LEAVE_REQUEST_REJECTED` - Leave rejected
- `LEAVE_REQUEST_CANCELLED` - Leave cancelled
- `LEAVE_BALANCE_LOW` - Low balance warning
- `NEW_LEAVE_REQUEST_FOR_APPROVAL` - New request for manager approval
- `SYSTEM_MESSAGE` - General system messages
