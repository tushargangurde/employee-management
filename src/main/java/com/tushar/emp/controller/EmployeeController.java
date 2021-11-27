package com.tushar.emp.controller;

import com.tushar.emp.dto.request.AddNewEmployeeRequest;
import com.tushar.emp.dto.response.AddNewEmployeeResponse;
import com.tushar.emp.dto.response.GetAllEmployeesResponse;
import com.tushar.emp.dto.response.UpdateEmployeeResponse;
import com.tushar.emp.entities.Employee;
import com.tushar.emp.services.EmployeeServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
@Log4j2
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping("/getAll")
    public ResponseEntity<List<GetAllEmployeesResponse>> getAllEmployees() {
        log.info("Inside EmployeeController ----> getAllEmployees");
        List<GetAllEmployeesResponse> allEmployees = employeeServices.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AddNewEmployeeResponse> saveEmployee(@Valid @RequestBody AddNewEmployeeRequest employee) {
        log.info("Inside EmployeeController ----> saveEmployee");
        AddNewEmployeeResponse savedEmployee = employeeServices.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String employeeId) {
        log.info("Inside EmployeeController ----> getEmployee");
        Employee employee = employeeServices.getEmployee(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PatchMapping("/update/{employeeId}")
    public ResponseEntity<UpdateEmployeeResponse> updateEmployee(@PathVariable String employeeId, @RequestBody Map<Object, Object> fields) {
        log.info("Inside EmployeeController ----> updateEmployee");
        UpdateEmployeeResponse updatedEmployee = employeeServices.updateEmployee(employeeId, fields);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable String employeeId) {
        log.info("Inside EmployeeController ----> deleteEmployee");
        Map<String, String> deleteMessage = employeeServices.deleteEmployee(employeeId);
        return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
    }

}
