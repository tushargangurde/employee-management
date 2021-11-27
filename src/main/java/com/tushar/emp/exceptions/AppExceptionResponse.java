package com.tushar.emp.exceptions;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppExceptionResponse {

    private String status;
    private String message;

}
