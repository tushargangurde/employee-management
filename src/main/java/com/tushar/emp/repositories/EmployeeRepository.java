package com.tushar.emp.repositories;

import com.tushar.emp.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmployeeId(String employeeId);

    Optional<Employee> findByEmployeeEmail(String employeeEmail);
}
