package ro.garrettmotion.automotive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.garrettmotion.automotive.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

}


