# Leave Management System

A comprehensive **Enterprise-Level Leave Management System** built with Java Spring Boot, React, and MySQL. This system streamlines the complete leave lifecycle with role-based access control, real-time notifications, and advanced analytics.

## 🎯 Quick Start

### Prerequisites
- Java 21 (JDK)
- MySQL 8.0+
- Node.js 14+
- Maven 3.8.1+

### Setup Instructions

#### 1. **Database Setup**
```bash
# Create and setup database
mysql -u root -p
> CREATE DATABASE leave_management_system;
> exit;

# Load schema
mysql -u root -p leave_management_system < database/schema.sql

# Load sample data  
mysql -u root -p leave_management_system < database/data.sql
```

#### 2. **Backend (Spring Boot)**
```bash
cd LeaveManagementSystem
mvn clean install
mvn spring-boot:run
```
✅ Backend: http://localhost:8080
✅ API Docs: http://localhost:8080/swagger-ui.html

#### 3. **Frontend (React)**
```bash
cd frontend
npm install
npm start
```
✅ Frontend: http://localhost:3000

---

## 📊 System Architecture

```
Frontend (React)
    ↓
REST API (Spring Boot)
    ↓
Service Layer (Business Logic)
    ↓
Repository Layer (JPA)
    ↓
Database (MySQL)
```

**Technology Stack:**
- **Backend:** Spring Boot 4.0.5, Spring Security, JWT Authentication
- **Frontend:** React 18, Tailwind CSS, Axios
- **Database:** MySQL 8.0
- **API Docs:** OpenAPI 3.0 / Swagger UI
- **Package Manager:** Maven (Backend), npm (Frontend)

---

## ✨ Key Features

### 👨‍💼 Employee Features
- ✅ Apply for leave (Casual, Sick, Earned, Maternity, etc.)
- ✅ View leave history and current status
- ✅ Cancel approved leave requests
- ✅ Check remaining leave balance
- ✅ Receive instant notifications
- ✅ View personal analytics

### 👔 Manager Features  
- ✅ Approve/Reject leave requests from team
- ✅ View pending requests for subordinates
- ✅ Add comments while approving/rejecting
- ✅ View team analytics and trends
- ✅ Monitor team leave patterns

### 🔐 Admin Features
- ✅ Manage employees (Add/Edit/Delete)
- ✅ Configure leave policies
- ✅ Manage holidays and work calendars
- ✅ Generate comprehensive reports
- ✅ View complete audit logs
- ✅ System-wide analytics

---

## 📁 Project Structure

```
LeaveManagementSystem/
├── src/main/java/com/lms/
│   ├── controller/        # REST API endpoints
│   ├── service/           # Business logic
│   ├── repository/        # Data access
│   ├── model/             # Entity classes
│   ├── dto/               # Data transfer objects
│   ├── exception/         # Exception handling
│   ├── security/          # JWT & security
│   ├── config/            # Configuration
│   ├── validator/         # Custom validators
│   ├── mapper/            # Entity-DTO mapping
│   └── LeaveManagementApplication.java
│
├── src/main/resources/
│   └── application.properties
│
├── frontend/
│   ├── src/
│   │   ├── components/    # Reusable React components
│   │   ├── pages/         # Page components
│   │   ├── services/      # API service calls
│   │   ├── context/       # Global state (Auth)
│   │   ├── utils/         # Utility functions
│   │   └── styles/        # CSS files
│   └── package.json
│
├── database/
│   ├── schema.sql         # Database schema
│   └── data.sql           # Sample data
│
├── docs/
│   ├── API_Documentation.md    # API reference
│   ├── ER_Diagram.txt          # Database diagram
│   └── Architecture.txt        # System architecture
│
└── pom.xml                # Maven configuration
```

---

## 🗄️ Database Schema

**8 Tables with relationships:**

- **employees** - User accounts with roles
- **leave_requests** - Leave applications and approvals
- **leave_balances** - Leave balance tracking by type/year
- **notifications** - Real-time alerts
- **audit_logs** - Activity tracking
- **holidays** - Public holiday calendar
- **departments** - Organizational structure

See [ER_Diagram.txt](docs/ER_Diagram.txt) for complete diagram.

---

## 🔌 API Endpoints

### Authentication
- `POST /api/auth/register` - Register new employee
- `POST /api/auth/login` - Login and get JWT token
- `POST /api/auth/refresh` - Refresh JWT token

### Leaves
- `POST /api/leaves/apply/{employeeId}` - Apply for leave
- `PUT /api/leaves/{leaveId}/approve/{approverId}` - Approve leave
- `PUT /api/leaves/{leaveId}/reject/{approverId}` - Reject leave
- `PUT /api/leaves/{leaveId}/cancel` - Cancel leave
- `GET /api/leaves/employee/{employeeId}` - Get employee's leaves
- `GET /api/leaves/manager/{managerId}/pending` - Get pending for approval

### Employees
- `GET /api/employees` - Get all employees
- `GET /api/employees/{id}` - Get employee details
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee

### Notifications
- `GET /api/notifications/employee/{employeeId}` - Get notifications
- `GET /api/notifications/employee/{employeeId}/unread` - Get unread
- `PUT /api/notifications/{notificationId}/read` - Mark as read

### Admin
- `GET /api/admin/employees` - Get all employees
- `POST /api/admin/employees` - Create employee

Full API documentation: [API_Documentation.md](docs/API_Documentation.md)

---

## 🔐 Security

- **JWT Authentication** - Token-based authentication
- **Role-Based Access Control (RBAC)** - 4 roles: EMPLOYEE, MANAGER, ADMIN, HR
- **Password Encryption** - BCrypt hashing
- **CORS Enabled** - Secure cross-origin requests
- **Input Validation** - Server-side validation
- **Audit Logging** - Track all actions

---

## 📋 Sample Credentials

```
Admin User:
  Email: john.doe@company.com
  Password: password123

Manager User:
  Email: jane.smith@company.com
  Password: password123

Employee User:
  Email: bob.johnson@company.com
  Password: password123
```

---

## 🚀 Advanced Features (Implemented)

✅ **Real-Time Notifications** - Instant alerts for leave actions
✅ **Holiday Calendar Integration** - Automatic public holiday management
✅ **Multi-Level Approval** - Manager → Admin workflow
✅ **Role-Based Access** - Different permissions per role
✅ **Leave Conflict Detection** - Prevent overlapping leaves
✅ **Audit Trail** - Complete activity logging
✅ **Working Days Calculation** - Excludes weekends & holidays
✅ **Leave Balance Management** - Track by type & year

---

## 📊 Business Logic

### Leave Validation
- End date must be after start date
- Cannot apply for past dates
- No overlapping leave requests
- Sufficient balance required
- Working days calculated correctly

### Approval Workflow
1. Employee submits leave request (status: PENDING)
2. Manager receives notification
3. Manager approves/rejects request
4. Employee notified of decision
5. Audit log recorded

### Balance Management
- Initial balance set per employee/year
- Deducted when leave approved
- Refunded when leave cancelled
- Tracked by leave type (Casual, Sick, etc.)

---

## 🔧 Configuration

### Database Connection
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/leave_management_system
spring.datasource.username=root
spring.datasource.password=root
```

### JWT Settings
```properties
jwt.secret=mySecretKeyForJWTTokenGenerationAndValidationPurposeOnly12345678
jwt.expiration=86400000  # 24 hours
```

### Server Port
```properties
server.port=8080
```

---

## 🧪 Testing

### Test with Swagger UI
1. Go to http://localhost:8080/swagger-ui.html
2. Click endpoint to expand
3. Click "Try it out"
4. Fill parameters and execute

### Test with cURL
```bash
# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john.doe@company.com","password":"password123"}'

# Apply for leave
curl -X POST http://localhost:8080/api/leaves/apply/1 \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"leaveType":"CASUAL","startDate":"2024-02-15","endDate":"2024-02-17"}'
```

---

## 📦 Dependencies

### Backend (Maven)
- Spring Boot 4.0.5
- Spring Data JPA
- Spring Security
- JWT (jjwt 0.12.3)
- MySQL Connector
- Lombok
- Validation
- Swagger/OpenAPI

### Frontend (npm)
- React 18.2
- React Router DOM 6
- Axios
- Tailwind CSS
- date-fns

---

## 🐛 Troubleshooting

### Database Connection Error
```bash
# Check MySQL running
mysql -u root -p

# Verify credentials in application.properties
# Check database exists
> SHOW DATABASES;
> USE leave_management_system;
> SHOW TABLES;
```

### Port Already in Use
```bash
# Windows: Find and kill process
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Mac/Linux
lsof -i :8080
kill -9 <PID>
```

### Maven Build Failing
```bash
mvn clean
mvn dependency:resolve
mvn install
```

### React Build Issues
```bash
npm cache clean --force
rm -rf node_modules package-lock.json
npm install
```

---

## 📚 Documentation

- [Database Setup & Configuration](DATABASE_SETUP.md)
- [API Documentation & Endpoints](docs/API_Documentation.md)
- [Entity Relationship Diagram](docs/ER_Diagram.txt)
- [System Architecture](docs/Architecture.txt)

---

## 🔄 Git Workflow

```bash
# Clone repository
git clone https://github.com/thirumalai07/Leave-Management-System.git

# Create feature branch
git checkout -b feature/new-feature

# Make changes and commit
git add .
git commit -m "feat: add new feature"

# Push to main
git push origin main
```

---

## 🎓 Learning Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [JWT Guide](https://jwt.io)

---

## 📈 Performance Considerations

- Database indexes on frequently queried columns
- JPA lazy loading for related entities
- API response pagination (can be added)
- Caching strategies (can be added)
- Compression enabled
- Connection pooling configured

---

## 🚢 Deployment

### To AWS/Azure
1. Create VM instance
2. Install Java 21, MySQL, Node.js
3. Clone repository
4. Run setup commands
5. Configure domain & SSL
6. Deploy with CI/CD pipeline

### Production Checklist
- [ ] Change database password
- [ ] Change JWT secret
- [ ] Set `ddl-auto=validate`
- [ ] Enable HTTPS
- [ ] Setup backups
- [ ] Configure monitoring
- [ ] Enable logging
- [ ] Setup error tracking

---

## 📞 Support

For issues or questions:
1. Check [Troubleshooting](#-troubleshooting) section
2. Review API documentation
3. Check application logs
4. Contact development team

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## ✅ Project Status

**Backend:** ✅ Complete  
**Frontend:** ✅ Base structure ready  
**Database:** ✅ Schema created with sample data  
**API Documentation:** ✅ Complete  
**Testing:** 🔄 In Progress

---

## 🎯 Next Steps

1. Complete React component implementations
2. Add unit and integration tests
3. Implement additional analytics
4. Setup CI/CD pipeline
5. Deploy to production

---

**Version:** 1.0.0  
**Last Updated:** January 2025  
**Status:** Ready for Development 🚀
