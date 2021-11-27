package com.tushar.emp.serviceimpl;

import com.tushar.emp.dto.request.AddNewEmployeeRequest;
import com.tushar.emp.dto.response.AddNewEmployeeResponse;
import com.tushar.emp.dto.response.EmployeeResponse;
import com.tushar.emp.dto.response.UpdateEmployeeResponse;
import com.tushar.emp.employeeexceptions.EmployeeIDNotPresentException;
import com.tushar.emp.employeeexceptions.EmployeeNotPresentException;
import com.tushar.emp.employeeexceptions.EmployeeSaveException;
import com.tushar.emp.entities.Employee;
import com.tushar.emp.repositories.EmployeeRepository;
import com.tushar.emp.services.EmployeeServices;
import com.tushar.emp.utils.EmployeeUtils;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
public class EmployeeServiceImpl implements EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeUtils employeeUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        log.info("Inside EmployeeServiceImpl ----> getAllEmployees");
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeesResponse = employees.stream()
                .map(e -> modelMapper.map(e, EmployeeResponse.class))
                .collect(Collectors.toList());
        return employeesResponse;
    }

    @Override
    public AddNewEmployeeResponse saveEmployee(AddNewEmployeeRequest employee) {
        Employee employeeToBeSaved = modelMapper.map(employee, Employee.class);
        log.info("Inside EmployeeServiceImpl ----> saveEmployee");
        if (employeeToBeSaved.getEmployeeId() == null) {
            employeeToBeSaved.setEmployeeId(UUID.randomUUID().toString());
        } else {
            throw new EmployeeSaveException("Employee ID must null for New Employee");
        }
        if (employeeUtils.isEmailExist(employeeToBeSaved.getEmployeeEmail())) {
            throw new EmployeeSaveException("Email ID: " + employee.getEmployeeEmail() + " is already exist, please use different Email");
        }
        if (employee.getEmployeeAge() != (LocalDate.now().getYear() - employee.getDateOfBirth().getYear())) {
            throw new EmployeeSaveException("Age must be " + (LocalDate.now().getYear() - employee.getDateOfBirth().getYear()) + " years, but data you provided in Age is " + employee.getEmployeeAge() + " years");
        }
        Employee savedEmployee = employeeRepository.save(employeeToBeSaved);
        AddNewEmployeeResponse responseSavedEmployee = modelMapper.map(savedEmployee, AddNewEmployeeResponse.class);
        return responseSavedEmployee;
    }

    @Override
    public EmployeeResponse getEmployee(String employeeId) {
        log.info("Inside EmployeeServiceImpl ----> getEmployee");
        if (employeeId == null || employeeId.length() == 0) {
            throw new EmployeeIDNotPresentException("Employee ID is null or not present");
        } else {
            Optional<Employee> employee = employeeRepository.findByEmployeeId(employeeId);
            if (employee.isEmpty()) {
                throw new EmployeeNotPresentException("Employee with Employee ID " + employeeId + " is not available");
            } else {
                EmployeeResponse employeeResponse = modelMapper.map(employee.get(), EmployeeResponse.class);
                return employeeResponse;
            }
        }
    }

    @Override
    public UpdateEmployeeResponse updateEmployee(String employeeId, Map<Object, Object> fields) {
        log.info("Inside EmployeeServiceImpl ----> updateEmployee");
        EmployeeResponse employeeResponse = getEmployee(employeeId);
        Employee employee = modelMapper.map(employeeResponse, Employee.class);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Employee.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, employee, v);
        });
        Employee updatedEmployee = employeeRepository.save(employee);
        UpdateEmployeeResponse response = modelMapper.map(updatedEmployee, UpdateEmployeeResponse.class);
        return response;
    }

    @Override
    public Map<String, String> deleteEmployee(String employeeId) {
        log.info("Inside EmployeeServiceImpl ----> deleteEmployee");
        Map<String, String> messageResponse = new HashMap<>();
        EmployeeResponse employeeResponse = getEmployee(employeeId);
        Employee employeeToBeDeleted = modelMapper.map(employeeResponse, Employee.class);
        Integer empId = employeeToBeDeleted.getId();
        employeeRepository.deleteById(empId);
        messageResponse.put("message", "Successfully deleted employee with Employee ID" + employeeId);
        return messageResponse;
    }

}
