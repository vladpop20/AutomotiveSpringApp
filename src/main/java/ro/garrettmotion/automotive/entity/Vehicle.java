package ro.garrettmotion.automotive.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Table(name = "vehicle")
@Entity
public class Vehicle {

//    @Transient
//    private Integer vehicleTypeId;

    @Id
    @Column(name = "vin", nullable = false, length = 17)
    private String id;

    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @Column(name = "plate_number", nullable = false, length = 10)
    private String plateNumber;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleTypeID;


//    public Vehicle(String id, String plateNumber, LocalDate dateOfRegistration, @JsonProperty("vehicleType") Integer vehicleType) {
//        this.id = id;
//        this.plateNumber = plateNumber;
//        this.dateOfRegistration = dateOfRegistration;
//
//        this.vehicleType = new VehicleType();
//        this.vehicleType.setId(vehicleType);
//    }

    @JsonProperty("vehicleTypeID")
    private void unpackNested(Integer vehicleTypeID) {
        this.vehicleTypeID = new VehicleType();
        this.vehicleTypeID.setId(vehicleTypeID);
    }

}