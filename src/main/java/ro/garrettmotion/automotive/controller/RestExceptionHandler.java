package ro.garrettmotion.automotive.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.garrettmotion.automotive.exception.VehicleNotFoundException;
import ro.garrettmotion.automotive.exception.VehiclePartNotFoundException;
import ro.garrettmotion.automotive.exception.VehicleTypeNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({VehicleNotFoundException.class})
    protected ResponseEntity<Object> vehicleNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Vehicle not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({VehicleTypeNotFoundException.class})
    protected ResponseEntity<Object> vehicleTypeNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Vehicle type not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({VehiclePartNotFoundException.class})
    protected ResponseEntity<Object> VehicleTypeNotFound(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Vehicle part not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
