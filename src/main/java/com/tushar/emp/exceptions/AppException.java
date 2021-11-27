package com.tushar.emp.exceptions;

import com.tushar.emp.employeeexceptions.EmployeeIDNotPresentException;
import com.tushar.emp.employeeexceptions.EmployeeNotPresentException;
import com.tushar.emp.employeeexceptions.EmployeeSaveException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class AppException extends ResponseEntityExceptionHandler {

    @Autowired
    private AppExceptionResponse appExceptionResponse;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        log.info("Inside AppException ----> handleMethodArgumentNotValid");
        Map<String, List<String>> body = new HashMap<>();
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("errors", errors);
        log.error(body);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        log.info("Inside AppException ----> handleHttpMessageNotReadable");
        log.error(exception.getMessage());
        appExceptionResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
        appExceptionResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(appExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeSaveException.class)
    public ResponseEntity<AppExceptionResponse> handleEmployeeSaveException(EmployeeSaveException exception) {
        log.info("Inside AppException ----> handleEmployeeSaveException");
        log.error(exception.getMessage());
        appExceptionResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.toString());
        appExceptionResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(appExceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EmployeeIDNotPresentException.class)
    public ResponseEntity<AppExceptionResponse> handleEmployeeIDNotPresentException(EmployeeIDNotPresentException exception) {
        log.info("Inside AppException ----> handleEmployeeIDNotPresentException");
        log.error(exception.getMessage());
        appExceptionResponse.setStatus(HttpStatus.NOT_FOUND.toString());
        appExceptionResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(appExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotPresentException.class)
    public ResponseEntity<AppExceptionResponse> handleEmployeeNotPresentException(EmployeeNotPresentException exception) {
        log.info("Inside AppException ----> handleEmployeeNotPresentException");
        log.error(exception.getMessage());
        appExceptionResponse.setStatus(HttpStatus.NOT_FOUND.toString());
        appExceptionResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(appExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AppExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.info("Inside AppException ----> handleIllegalArgumentException");
        log.error(exception.getMessage());
        appExceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        appExceptionResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(appExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<AppExceptionResponse> handleUnexpectedTypeException(UnexpectedTypeException exception) {
        log.info("Inside AppException ----> handleUnexpectedTypeException");
        log.error(exception.getMessage());
        appExceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        appExceptionResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(appExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
