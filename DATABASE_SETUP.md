# Leave Management System - Complete Setup Guide

## Quick Start

### 1. Database Setup
```bash
# Create database
mysql -u root -p
> CREATE DATABASE leave_management_system;
> exit;

# Run schema
mysql -u root -p leave_management_system < database/schema.sql

# Load sample data
mysql -u root -p leave_management_system < database/data.sql
```

**Database Configuration** (Update if needed in `application.properties`):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/leave_management_system?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
```

### 2. Backend Setup
```bash
cd c:\Users\thiru\Downloads\LeaveManagementSystem
mvn clean install
mvn spring-boot:run
```
Backend runs on: http://localhost:8080
API Documentation: http://localhost:8080/swagger-ui.html

### 3. Frontend Setup
```bash
cd frontend
npm install
npm start
```
Frontend runs on: http://localhost:3000

## Directory Structure

## ⭐ Features

### Employee Features
- ✅ Apply for leave (Sick, Casual, Paid)
- ✅ View leave history and status
- ✅ Cancel leave requests
- ✅ Check leave balance
- ✅ Receive real-time notifications
- ✅ View personal leave analytics

### Manager Features
- ✅ Approve/Reject leave requests
- ✅ View team leave requests
- ✅ Add comments while approving/rejecting
- ✅ View team analytics and trends
- ✅ Manage subordinate leave information

### Admin Features
- ✅ Manage employees (Add/Remove/Update)
- ✅ Assign managers to employees
- ✅ Configure leave policies
- ✅ Generate comprehensive reports
- ✅ View audit logs
- ✅ Manage holidays and work calendars

## 🛠 Tech Stack

### Backend
- **Java 21** - Latest LTS version
- **Spring Boot 4.0.5** - Web Framework
- **Spring Data JPA** - ORM
- **Hibernate** - Persistence Framework
- **MySQL 8.0** - Database

### Build & Dependencies
- **Maven** - Build Tool
- **Lombok** - Boilerplate Reduction
- **Spring Validation** - Input Validation
- **SpringDoc OpenAPI** - API Documentation

## 🏗 System Architecture

```
┌─────────────────────────────────────────────────┐
│           Frontend (React.js)                    │
│  (To be developed - UI for Leave Management)     │
└────────────┬──────────────────────────┬──────────┘
             │                          │
  ┌──────────▼───────────────────────────▼──────────┐
  │  Backend (Spring Boot REST APIs)                 │
  │  ├─ Employee Service                            │
  │  ├─ Leave Request Service                       │
  │  ├─ Leave Balance Service                       │
  │  ├─ Notification Service                        │
  │  ├─ Audit Service                              │
  │  └─ Holiday Service                            │
  └────────────┬────────────────────────────────────┘
               │
  ┌────────────▼────────────────────────────────────┐
  │  Database (MySQL)                               │
  │  ├─ Employee Table                             │
  │  ├─ LeaveRequest Table                         │
  │  ├─ LeaveBalance Table                         │
  │  ├─ Notification Table                         │
  │  ├─ AuditLog Table                            │
  │  ├─ Holiday Table                             │
  │  ├─ Department Table                          │
  │  └─ LeavePolicy Table                         │
  └─────────────────────────────────────────────────┘
```

## 🗄 Database Schema

### Tables Overview

#### 1. **Employee** Table
```
id (PK) → Auto-increment
name → VARCHAR(100)
email → VARCHAR(100) UNIQUE
role → ENUM(EMPLOYEE, MANAGER, ADMIN)
department → VARCHAR(50)
manager_id → INT (FK → Employee.id) - Self Join
created_at, updated_at → TIMESTAMPS
```

#### 2. **LeaveRequest** Table
```
id (PK) → Auto-increment
employee_id (FK) → Employee.id
leave_type → ENUM(SICK, CASUAL, PAID)
start_date, end_date → DATE
total_days → INT
reason → TEXT
status → ENUM(PENDING, APPROVED, REJECTED, CANCELLED)
applied_at → TIMESTAMP
approved_by (FK) → Employee.id
comments → TEXT
created_at, updated_at → TIMESTAMPS
```

#### 3. **LeaveBalance** Table
```
id (PK) → Auto-increment
employee_id (FK) → Employee.id
leave_type → ENUM(SICK, CASUAL, PAID)
total_allocated → INT
used_leaves → INT
remaining_leaves → INT
created_at, updated_at → TIMESTAMPS
UNIQUE(employee_id, leave_type)
```

#### 4. **Notification** Table
```
id (PK) → Auto-increment
user_id (FK) → Employee.id
message → TEXT
type → ENUM(EMAIL, SYSTEM)
is_read → BOOLEAN
created_at → TIMESTAMP
```

#### 5. **AuditLog** Table
```
id (PK) → Auto-increment
action → VARCHAR(100)
performed_by (FK) → Employee.id
target_id → INT
entity_type → VARCHAR(50)
old_value, new_value → TEXT
timestamp → TIMESTAMP
```

#### 6. **Holiday** Table
```
id (PK) → Auto-increment
name → VARCHAR(100)
holiday_date → DATE UNIQUE
is_optional → BOOLEAN
created_at → TIMESTAMP
```

#### 7. **Department** Table
```
id (PK) → Auto-increment
name → VARCHAR(100) UNIQUE
description → TEXT
created_at, updated_at → TIMESTAMPS
```

#### 8. **LeavePolicy** Table
```
id (PK) → Auto-increment
name → VARCHAR(100)
leave_type → ENUM(SICK, CASUAL, PAID) UNIQUE
total_days_per_year → INT
carry_forward_days → INT
is_active → BOOLEAN
created_at, updated_at → TIMESTAMPS
```

## 📦 Installation & Setup

### Prerequisites
- **Java 21** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **Git**

### Step 1: Clone Repository
```bash
git clone https://github.com/thirumalai07/Leave-Management-System.git
cd Leave-Management-System
```

### Step 2: Create Database
```sql
-- Connect to MySQL
mysql -u root -p

-- Create database
CREATE DATABASE leave_management_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use the database
USE leave_management_system;
```

### Step 3: Configure Database Connection
Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/leave_management_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
```

### Step 4: Add Dependencies
The project includes the following key dependencies (already in pom.xml):
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- MySQL Connector Java
- Lombok
- Spring Validation
- SpringDoc OpenAPI (Swagger)

### Step 5: Build & Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080/api`

### Step 6: Verify Installation
- **Health Check**: `http://localhost:8080/api/actuator/health`
- **API Documentation**: `http://localhost:8080/api/swagger-ui.html`

## 📊 ER Diagram

```
                    ┌─────────────────────┐
                    │   Employee (PK: id) │
                    │───────────────────  │
                    │ id                  │
                    │ name                │
                    │ email (UNIQUE)      │
                    │ role                │
                    │ department          │
                    │ manager_id (FK)     │◄──────┐
                    └──────────┬──────────┘       │
                               │                  │
                        ┌──────┴──────┐           │
                        │ Self Join   │           │
                        │ (Manager)   │───────────┘
                        └─────────────┘
                               │
                ┌──────────────┼──────────────┐
                │              │              │
                ▼              ▼              ▼
    ┌──────────────────┐ ┌──────────────┐ ┌──────────────────┐
    │  LeaveRequest    │ │ LeaveBalance │ │  Notification    │
    │ (PK: id)         │ │ (PK: id)     │ │ (PK: id)         │
    │──────────────────│ │──────────────│ │──────────────────│
    │ id               │ │ id           │ │ id               │
    │ employee_id (FK) │ │ employee_id  │ │ user_id (FK)     │
    │ leave_type       │ │ leave_type   │ │ message          │
    │ start_date       │ │ total_alloc  │ │ type             │
    │ end_date         │ │ used_leaves  │ │ is_read          │
    │ total_days       │ │ remaining    │ │ created_at       │
    │ reason           │ │ created_at   │ └──────────────────┘
    │ status           │ │ updated_at   │
    │ approved_by (FK) │ └──────────────┘
    │ comments         │
    │ created_at       │
    └──────────────────┘

    ┌──────────────┐  ┌──────────────┐  ┌──────────────┐
    │   AuditLog   │  │   Holiday    │  │  Department  │
    │  (PK: id)    │  │  (PK: id)    │  │  (PK: id)    │
    │──────────────│  │──────────────│  │──────────────│
    │ id           │  │ id           │  │ id           │
    │ action       │  │ name         │  │ name (UNQ)   │
    │ performed_by │  │ holiday_date │  │ description  │
    │ target_id    │  │ is_optional  │  │ created_at   │
    │ entity_type  │  │ created_at   │  │ updated_at   │
    │ old_value    │  └──────────────┘  └──────────────┘
    │ new_value    │
    │ timestamp    │
    └──────────────┘
```

## 🧠 Core Entities

### 1. Employee
Represents an employee in the system with role-based permissions.
- **Roles**: EMPLOYEE, MANAGER, ADMIN
- **Self-Relationship**: Manager managing subordinates

### 2. LeaveRequest
Core entity for leave management with complete lifecycle.
- **Leave Types**: SICK, CASUAL, PAID
- **Statuses**: PENDING, APPROVED, REJECTED, CANCELLED
- **Tracking**: Applied timestamp, approval details, comments

### 3. LeaveBalance
Tracks leave balance per employee per leave type.
- Auto-calculated remaining leaves
- Prevents over-applying leaves

### 4. Notification
Real-time notifications for leave-related events.
- **Types**: EMAIL, SYSTEM
- Tracks read/unread status

### 5. AuditLog
Comprehensive audit trail for compliance and monitoring.
- Tracks all actions and changes
- Records who did what and when

## ✅ Business Logic & Validation Rules

### Validation Rules
1. **Date Validation**
   - End date must be after start date
   - Cannot apply leave for past dates (configurable)

2. **Leave Conflict Detection**
   - Cannot apply overlapping leaves
   - System auto-detects conflicts within team

3. **Balance Validation**
   - Cannot exceed available leave balance
   - Real-time balance calculation

4. **Authorization**
   - Manager cannot approve their own leave
   - Multi-level approval workflow (Manager → HR → Admin)

### Smart Logic
- **Auto-Calculate Working Days**: Excludes weekends and holidays
- **Team Conflict Detection**: Identifies team coverage issues
- **Multi-Level Approval**: Enforces approval hierarchy
- **Auto-Expiry**: Leave automatically expires at year-end
- **Priority Handling**: Medical emergencies get priority processing

## 🔐 Security Features

### Role-Based Access Control (RBAC)
- **Employee**: Limited access (own data only)
- **Manager**: Team access (subordinates' data)
- **Admin**: Full system access

### Audit Trail
- All operations logged with timestamp
- Track who performed what action
- Complete change history for compliance

## 🚀 Advanced Features (Optional Enhancements)

### 1. Real-Time Notifications
- Email alerts via SMTP/SendGrid
- In-app notifications
- Push notifications (future)

### 2. Holiday Calendar Integration
- Public holidays auto-excluded from leave count
- Region-based holiday management
- Configurable optional holidays

### 3. Dashboard & Analytics
- Leave taken per employee
- Department-wise trends
- Monthly/quarterly reports
- Utilization analytics

### 4. Leave Cancellation Workflow
- Approved leaves require manager approval to cancel
- Audit trail for cancellations
- Notification on cancellation

### 5. AI Features (Future)
- Predict leave trends
- Suggest optimal leave dates
- Workload analytics

## 📝 API Endpoints (To be developed)

### Employee Endpoints
```
POST   /api/employees                  - Create employee
GET    /api/employees/:id              - Get employee details
PUT    /api/employees/:id              - Update employee
DELETE /api/employees/:id              - Delete employee
GET    /api/employees                  - List all employees
GET    /api/employees/:id/subordinates - Get subordinates
```

### Leave Request Endpoints
```
POST   /api/leave-requests             - Apply for leave
GET    /api/leave-requests/:id         - Get leave request details
PUT    /api/leave-requests/:id         - Update leave request
DELETE /api/leave-requests/:id         - Cancel leave request
GET    /api/leave-requests             - List leave requests
GET    /api/leave-requests?emp_id=X    - Employee's leaves
GET    /api/leave-requests?manager_id=X - Manager's pending approvals
```

### Leave Balance Endpoints
```
GET    /api/leave-balance/:emp_id      - Get employee balance
GET    /api/leave-balance              - List all balances
PUT    /api/leave-balance/:id          - Update balance
```

### Holiday Endpoints
```
POST   /api/holidays                   - Add holiday
GET    /api/holidays                   - List holidays
DELETE /api/holidays/:id               - Remove holiday
```

### Analytics Endpoints
```
GET    /api/analytics/employee/:emp_id - Employee analytics
GET    /api/analytics/department      - Department analytics
GET    /api/analytics/reports         - Generate reports
```

## 🔗 Key Database Relationships

### 1:N Relationships
- Employee → LeaveRequest (1 employee, many leave requests)
- Employee → LeaveBalance (1 employee, multiple leave types)
- Employee → Notification (1 employee, many notifications)

### Self-Join
- Employee → Employee (Manager relationship)

### Foreign Keys
- LeaveRequest.employee_id → Employee.id
- LeaveRequest.approved_by → Employee.id
- Employee.manager_id → Employee.id
- All other tables follow similar patterns

## 📖 File Structure

```
Leave-Management-System/
├── src/
│   ├── main/
│   │   ├── java/org/example/leavemanagementsystem/
│   │   │   ├── LeaveManagementSystemApplication.java
│   │   │   ├── entity/          (JPA Entities)
│   │   │   ├── repository/      (Data Access Layer)
│   │   │   ├── service/         (Business Logic)
│   │   │   ├── controller/      (REST Controllers)
│   │   │   ├── dto/            (Data Transfer Objects)
│   │   │   ├── exception/      (Custom Exceptions)
│   │   │   └── util/           (Utilities)
│   │   └── resources/
│   │       ├── schema.sql       (Database Schema)
│   │       ├── data.sql         (Sample Data)
│   │       └── application.properties
│   └── test/
│       └── java/.../            (Unit Tests)
├── pom.xml
└── README.md
```

## 📚 Dependencies

Key Maven dependencies included:
- `spring-boot-starter-web` - Web development
- `spring-boot-starter-data-jpa` - ORM support
- `mysql-connector-java` - Database driver
- `lombok` - Code generation
- `spring-boot-starter-validation` - Input validation
- `springdoc-openapi-starter-webmvc-ui` - API docs

## 🆘 Troubleshooting

### Database Connection Issues
```
Error: Communications link failure
Solution: Ensure MySQL is running and credentials are correct
```

### Entity Mapping Issues
```
Error: Unknown entity
Solution: Ensure @Entity annotation is present and package is scanned
```

### Lombok Not Working
```
Error: getters/setters not found
Solution: Install Lombok plugin in IDE and enable annotation processing
```

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## 📄 License

This project is licensed under the MIT License.

## 📞 Support

For issues, questions, or suggestions, please create an issue in the GitHub repository.

---

**Last Updated**: March 27, 2026
