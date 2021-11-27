package com.tushar.emp.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AddNewEmployeeRequest {

    @NotNull(message = "Employee Name must be present")
    @Size(min = 6, max = 50, message = "Name must be length of min 2 & max 50 characters")
    private String employeeName;

    @NotNull(message = "Employee Address must be present")
    @Size(min = 6, max = 100, message = "Address must be length of min 2 & max 100 characters")
    private String employeeAddress;

    @NotNull(message = "Employee Email must be present")
    @Email(message = "Invalid Email")
    private String employeeEmail;

    @NotNull(message = "Gender must be present")
    private String gender;

    @NotNull(message = "Contact no must be present")
    @Pattern(regexp = "(^[7-9][0-9]{9}$)", message = "Contact no requires 10 digits & starts with 7,8,and 9 only")
    private String contactNo;

    @NotNull(message = "Date of Birth must be present")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate dateOfBirth;

    @NotNull(message = "Employee Age must be present")
    @Min(value = 18, message = "Employee age must be greater than 18")
    private Integer employeeAge;

}
