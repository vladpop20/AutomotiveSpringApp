package ro.garrettmotion.automotive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.garrettmotion.automotive.entity.VehiclePart;

@Repository
public interface VehiclePartRepository extends JpaRepository<VehiclePart, Integer> {

}


