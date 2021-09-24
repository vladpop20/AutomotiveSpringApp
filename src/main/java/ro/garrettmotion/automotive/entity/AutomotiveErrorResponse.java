package ro.garrettmotion.automotive.entity;

import java.util.List;

public class AutomotiveErrorResponse {

    private long timestamp;
    private String message;
    private List<String> errors;
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<String> getErrors() {
        return errors;
    }
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    public AutomotiveErrorResponse(long timestamp, String message, List<String> errors) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }
    public AutomotiveErrorResponse() {
        super();
        // TODO Auto-generated constructor stub
    }
}
