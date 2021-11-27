package com.tushar.emp.employeeexceptions;

public class EmployeeNotPresentException extends RuntimeException {
    public EmployeeNotPresentException(String message) {
        super(message);
    }
}
