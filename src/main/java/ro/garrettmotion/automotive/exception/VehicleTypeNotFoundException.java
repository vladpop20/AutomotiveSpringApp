package ro.garrettmotion.automotive.exception;


public class VehicleTypeNotFoundException  extends RuntimeException {

    public VehicleTypeNotFoundException(String message) {
        super(message);
    }
}
