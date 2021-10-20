package ro.garrettmotion.automotive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.entity.VehicleType;
import ro.garrettmotion.automotive.exception.VehicleNotFoundException;
import ro.garrettmotion.automotive.repository.VehiclePagingSortingRepository;
import ro.garrettmotion.automotive.repository.VehicleRepository;

@Service
@Transactional
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehiclePagingSortingRepository vehiclePagingSortingRepository;

    public VehicleService(VehicleRepository vehicleRepository, VehiclePagingSortingRepository vehiclePagingSortingRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehiclePagingSortingRepository = vehiclePagingSortingRepository;
    }

    public List<Vehicle> listAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle save(Vehicle product) {
        return vehicleRepository.save(product);
    }

    public Vehicle get(String id) throws Exception {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(!vehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle not found, with id: " + id);
        }
        return vehicle.get();
    }

    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }




// Last improvement: create a hql query which will return the values in a paginated way ordered by IDs descending.

    public Page<Vehicle> getVehicleByVin(int vehicleTypeID, int page, int pageSize) {
        Pageable sortedByVinDesc = PageRequest.of(page, pageSize, Sort.by("id").descending());

        return vehiclePagingSortingRepository.getVehiclesByVehicleTypeIDAndOrderByIdDesc(vehicleTypeID, sortedByVinDesc);
    }

}
