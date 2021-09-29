package ro.garrettmotion.automotive.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "vehicle_type")
@Entity
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @JsonCreator
    public VehicleType(String name) {
        //this.id = id;
        this.name = name;
    }

    public VehicleType() {
        super();
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
        if (!(o instanceof VehicleType)) return false;
        VehicleType that = (VehicleType) o;
        return getId().equals(that.getId()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "VehicleType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}