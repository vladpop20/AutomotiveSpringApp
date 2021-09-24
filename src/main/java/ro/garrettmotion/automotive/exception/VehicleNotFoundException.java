package ro.garrettmotion.automotive.exception;

public class VehicleNotFoundException extends RuntimeException {

//    public VehicleNotFoundException(String message, Throwable cause) {
//        super(message, cause);
//    }

        public VehicleNotFoundException(String message) {
        super(message);
    }
}
