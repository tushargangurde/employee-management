package com.tushar.emp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String employeeId;
    private String employeeName;
    private String employeeAddress;
    private String employeeEmail;
    private String gender;
    private String contactNo;
    private LocalDate dateOfBirth;
    private Integer employeeAge;

}
