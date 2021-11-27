package com.tushar.emp.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateEmployeeResponse {

    private String employeeId;
    private String employeeName;
    private String employeeAddress;
    private String employeeEmail;
    private String gender;
    private String contactNo;
    private LocalDate dateOfBirth;
    private Integer employeeAge;

}
