import api from './api';

// Authentication endpoints
export const authService = {
  register: (employeeData) => api.post('/auth/register', employeeData),
  login: (email, password) => api.post('/auth/login', { email, password }),
  refreshToken: () => api.post('/auth/refresh'),
};

// Employee endpoints
export const employeeService = {
  getEmployeeById: (id) => api.get(`/employees/${id}`),
  getAllEmployees: () => api.get('/employees'),
  getActiveEmployees: () => api.get('/employees/active'),
  getEmployeeByEmail: (email) => api.get(`/employees/email/${email}`),
  updateEmployee: (id, employeeData) => api.put(`/employees/${id}`, employeeData),
  deleteEmployee: (id) => api.delete(`/employees/${id}`),
};

// Leave endpoints
export const leaveService = {
  applyLeave: (employeeId, leaveData) => api.post(`/leaves/apply/${employeeId}`, leaveData),
  approveLeave: (leaveId, approverId) => api.put(`/leaves/${leaveId}/approve/${approverId}`),
  rejectLeave: (leaveId, approverId, reason) => 
    api.put(`/leaves/${leaveId}/reject/${approverId}?reason=${reason}`),
  cancelLeave: (leaveId) => api.put(`/leaves/${leaveId}/cancel`),
  getLeavesByEmployee: (employeeId) => api.get(`/leaves/employee/${employeeId}`),
  getPendingLeavesByManager: (managerId) => api.get(`/leaves/manager/${managerId}/pending`),
  getAllLeaves: () => api.get('/leaves'),
};

// Notification endpoints
export const notificationService = {
  getNotifications: (employeeId) => api.get(`/notifications/employee/${employeeId}`),
  getUnreadNotifications: (employeeId) => api.get(`/notifications/employee/${employeeId}/unread`),
  getUnreadCount: (employeeId) => api.get(`/notifications/employee/${employeeId}/unread/count`),
  markAsRead: (notificationId) => api.put(`/notifications/${notificationId}/read`),
};

// Admin endpoints
export const adminService = {
  getAllEmployees: () => api.get('/admin/employees'),
  getEmployeesByRole: (role) => api.get(`/admin/employees/role/${role}`),
  createEmployee: (employeeData) => api.post('/admin/employees', employeeData),
};
