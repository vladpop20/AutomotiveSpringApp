package ro.garrettmotion.automotive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "vehicle")
@Entity
public class Vehicle {
    @Id
    @Column(name = "vin", nullable = false, length = 17)
    private String id;

    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @Column(name = "plate_number", nullable = false, length = 10)
    private String plateNumber;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    public Vehicle(String id, String plateNumber, LocalDate dateOfRegistration, VehicleType vehicleType) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.dateOfRegistration = dateOfRegistration;
        this.vehicleType = vehicleType;
    }

    public Vehicle() {

    }

    @JsonProperty("vehicleType")
    private void unpackNested(Integer vehicleType) {
        this.vehicleType = new VehicleType();
        this.vehicleType.setId(vehicleType);
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}