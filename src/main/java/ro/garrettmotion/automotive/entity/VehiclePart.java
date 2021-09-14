package ro.garrettmotion.automotive.entity;

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

    @Column(name = "VehicleType_ID")
    private Integer vehicletypeId;

    public Integer getVehicletypeId() {
        return vehicletypeId;
    }

    public void setVehicletypeId(Integer vehicletypeId) {
        this.vehicletypeId = vehicletypeId;
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