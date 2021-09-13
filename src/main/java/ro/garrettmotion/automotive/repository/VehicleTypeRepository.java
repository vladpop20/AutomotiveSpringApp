package ro.garrettmotion.automotive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.garrettmotion.automotive.entity.VehicleType;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {

}


