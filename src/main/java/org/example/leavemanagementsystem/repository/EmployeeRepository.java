package org.example.leavemanagementsystem.repository;

import org.example.leavemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    Optional<Employee> findByEmail(String email);
    
    List<Employee> findByDepartment(String department);
    
    List<Employee> findByRole(Employee.Role role);
    
    List<Employee> findByManagerId(Integer managerId);
    
    @Query("SELECT e FROM Employee e WHERE e.manager.id = :managerId")
    List<Employee> findSubordinatesByManagerId(@Param("managerId") Integer managerId);
}
