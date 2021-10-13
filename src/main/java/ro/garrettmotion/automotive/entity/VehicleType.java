package ro.garrettmotion.automotive.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Table(name = "vehicle_type")
@Entity
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private  String name;

    @JsonCreator
    public VehicleType(String name) {
        this.name = name;
    }
}