package com.tushar.emp.employeeexceptions;

public class EmployeeIDNotPresentException extends RuntimeException {
    public EmployeeIDNotPresentException(String message) {
        super(message);
    }
}
