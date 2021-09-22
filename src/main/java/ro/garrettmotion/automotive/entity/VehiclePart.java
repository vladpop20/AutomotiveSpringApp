package ro.garrettmotion.automotive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

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

    public VehiclePart(String name, VehicleType vehicleType) {
        //this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehiclePart)) return false;
        VehiclePart that = (VehiclePart) o;
        return getId().equals(that.getId()) && getName().equals(that.getName()) && getVehicleType().equals(that.getVehicleType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getVehicleType());
    }

    @Override
    public String toString() {
        return "VehiclePart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }
}