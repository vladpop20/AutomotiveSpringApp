package ro.garrettmotion.automotive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table(name = "vehicle")
@Entity
public class Vehicle {

    @Transient
    private Integer vehicleTypeId;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return getId().equals(vehicle.getId()) && getDateOfRegistration().equals(vehicle.getDateOfRegistration()) &&
                getPlateNumber().equals(vehicle.getPlateNumber()) && getVehicleType().equals(vehicle.getVehicleType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateOfRegistration(), getPlateNumber(), getVehicleType());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                ", plateNumber='" + plateNumber + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}