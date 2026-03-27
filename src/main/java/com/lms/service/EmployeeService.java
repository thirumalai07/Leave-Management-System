package com.lms.service;

import com.lms.dto.EmployeeDTO;
import com.lms.exception.ResourceNotFoundException;
import com.lms.mapper.EntityMapper;
import com.lms.model.Employee;
import com.lms.model.Role;
import com.lms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final EntityMapper entityMapper;
    private final PasswordEncoder passwordEncoder;
    
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = entityMapper.toEmployee(employeeDTO);
        employee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        employee.setActive(true);
        Employee savedEmployee = employeeRepository.save(employee);
        return entityMapper.toEmployeeDTO(savedEmployee);
    }
    
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return entityMapper.toEmployeeDTO(employee);
    }
    
    public EmployeeDTO getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + email));
        return entityMapper.toEmployeeDTO(employee);
    }
    
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(entityMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
    
    public List<EmployeeDTO> getEmployeesByRole(Role role) {
        return employeeRepository.findByRole(role)
                .stream()
                .map(entityMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
    
    public List<EmployeeDTO> getEmployeesByManager(Long managerId) {
        return employeeRepository.findActiveSubordinatesByManagerId(managerId)
                .stream()
                .map(entityMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
    
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setUpdatedAt(LocalDateTime.now());
        
        Employee updatedEmployee = employeeRepository.save(employee);
        return entityMapper.toEmployeeDTO(updatedEmployee);
    }
    
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employee.setActive(false);
        employee.setUpdatedAt(LocalDateTime.now());
        employeeRepository.save(employee);
    }
    
    public List<EmployeeDTO> getAllActiveEmployees() {
        return employeeRepository.findByActiveTrue()
                .stream()
                .map(entityMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }
}
