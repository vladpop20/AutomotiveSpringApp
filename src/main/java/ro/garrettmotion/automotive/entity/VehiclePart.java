package ro.garrettmotion.automotive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table(name = "vehicle_part")
@Entity
public class VehiclePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    public VehiclePart(Integer id, String name, VehicleType vehicleType) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
    }

    public VehiclePart() {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}