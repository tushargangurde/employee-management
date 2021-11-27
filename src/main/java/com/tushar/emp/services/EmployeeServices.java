package com.tushar.emp.services;

import com.tushar.emp.dto.request.AddNewEmployeeRequest;
import com.tushar.emp.dto.response.AddNewEmployeeResponse;
import com.tushar.emp.dto.response.EmployeeResponse;
import com.tushar.emp.dto.response.UpdateEmployeeResponse;

import java.util.List;
import java.util.Map;

public interface EmployeeServices {

    List<EmployeeResponse> getAllEmployees();

    AddNewEmployeeResponse saveEmployee(AddNewEmployeeRequest employee);

    EmployeeResponse getEmployee(String employeeId);

    UpdateEmployeeResponse updateEmployee(String employeeId, Map<Object, Object> fields);

    Map<String, String> deleteEmployee(String employeeId);
}
