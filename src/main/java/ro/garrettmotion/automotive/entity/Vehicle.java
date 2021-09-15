package ro.garrettmotion.automotive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "vehicle", schema = "dbo")
@Entity
public class Vehicle {
    @Id
    @Column(name = "VIN", nullable = false, length = 17)
    private String id;

    @ManyToOne
    @JoinColumn(name = "VehicleType_ID")
    private VehicleType vehicleType;

    @Column(name = "PlateNumber", nullable = false, length = 10)
    private String plateNumber;

    @Column(name = "DateOfRegistration")
    private LocalDate dateOfRegistration;

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

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}