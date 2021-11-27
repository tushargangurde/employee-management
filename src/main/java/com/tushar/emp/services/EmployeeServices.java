package com.tushar.emp.services;

import com.tushar.emp.dto.request.AddNewEmployeeRequest;
import com.tushar.emp.dto.response.AddNewEmployeeResponse;
import com.tushar.emp.dto.response.GetAllEmployeesResponse;
import com.tushar.emp.dto.response.UpdateEmployeeResponse;
import com.tushar.emp.entities.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeServices {

    List<GetAllEmployeesResponse> getAllEmployees();

    AddNewEmployeeResponse saveEmployee(AddNewEmployeeRequest employee);

    Employee getEmployee(String employeeId);

    UpdateEmployeeResponse updateEmployee(String employeeId, Map<Object, Object> fields);

    Map<String, String> deleteEmployee(String employeeId);
}
