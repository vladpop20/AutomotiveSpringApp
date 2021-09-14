package ro.garrettmotion.automotive.entity;

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