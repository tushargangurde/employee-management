package com.tushar.emp.utils;

import com.tushar.emp.entities.Employee;
import com.tushar.emp.repositories.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Log4j2
public class EmployeeUtils {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Boolean isEmailExist(String employeeEmail) {
        log.info("Inside EmployeeUtils ----> isEmailExist");
        Boolean flag = false;
        Optional<Employee> emailExistedEmployee = employeeRepository.findByEmployeeEmail(employeeEmail);
        if (emailExistedEmployee.isPresent()) {
            flag = true;
        }
        return flag;
    }
}
