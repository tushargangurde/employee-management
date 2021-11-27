package com.tushar.emp.employeeexceptions;

public class EmployeeSaveException extends RuntimeException {
    public EmployeeSaveException(String message) {
        super(message);
    }
}
