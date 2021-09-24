package ro.garrettmotion.automotive.exception;

public class VehiclePartNotFoundException extends RuntimeException {

    public VehiclePartNotFoundException(String message) {
        super(message);
    }
}