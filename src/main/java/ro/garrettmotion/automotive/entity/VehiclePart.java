package ro.garrettmotion.automotive.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
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
    private VehicleType vehicleTypeID;

    @JsonCreator
    public VehiclePart(String name, VehicleType vehicleType) {
        this.name = name;
        this.vehicleTypeID = vehicleType;
    }

    @JsonProperty("vehicleTypeID")
    private void unpackNested(Integer vehicleTypeID) {
        this.vehicleTypeID = new VehicleType();
        this.vehicleTypeID.setId(vehicleTypeID);
    }

}