package ro.garrettmotion.automotive.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ro.garrettmotion.automotive.entity.AutomotiveErrorResponse;

import java.util.Arrays;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler({VehicleNotFoundException.class})
//    protected ResponseEntity<Object> vehicleNotFound(Exception ex, WebRequest request) {
//        return handleExceptionInternal(ex, "Vehicle not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
//    }

    @ExceptionHandler({VehicleNotFoundException.class})
    protected ResponseEntity<Object> vehicleNotFound(VehicleNotFoundException ex) {
        AutomotiveErrorResponse automotiveResponse = new AutomotiveErrorResponse(System.currentTimeMillis(),"Vehicle not found", Arrays.asList(ex.getMessage()));
        return new ResponseEntity<Object>(automotiveResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({VehicleTypeNotFoundException.class})
    protected ResponseEntity<Object> vehicleTypeNotFound(VehicleTypeNotFoundException ex) {
        AutomotiveErrorResponse automotiveResponse = new AutomotiveErrorResponse(System.currentTimeMillis(),"Vehicle type not found", Arrays.asList(ex.getMessage()));
        return new ResponseEntity<Object>(automotiveResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({VehiclePartNotFoundException.class})
    protected ResponseEntity<Object> VehicleTypeNotFound(VehiclePartNotFoundException ex) {
        AutomotiveErrorResponse automotiveResponse = new AutomotiveErrorResponse(System.currentTimeMillis(),"Vehicle part not found", Arrays.asList(ex.getMessage()));
        return new ResponseEntity<Object>(automotiveResponse, HttpStatus.NOT_FOUND);
    }
}
