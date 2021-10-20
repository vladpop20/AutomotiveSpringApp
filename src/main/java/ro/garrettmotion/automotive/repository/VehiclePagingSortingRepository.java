package ro.garrettmotion.automotive.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.garrettmotion.automotive.entity.Vehicle;

@Repository
public interface VehiclePagingSortingRepository extends JpaRepository<Vehicle, String> {

    @Query("SELECT veh FROM Vehicle veh where veh.vehicleTypeID.id = :id") //  ORDER BY veh.id DESC
    Page<Vehicle> getVehiclesByVehicleTypeIDAndOrderByIdDesc(@Param("id") int vehicleTypeID, Pageable pageRequest);
}
