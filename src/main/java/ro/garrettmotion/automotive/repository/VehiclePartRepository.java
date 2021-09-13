package ro.garrettmotion.automotive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.garrettmotion.automotive.entity.VehiclePart;

public interface VehiclePartRepository extends JpaRepository<VehiclePart, Integer> {

}


