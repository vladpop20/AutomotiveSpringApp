package ro.garrettmotion.automotive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.exception.VehicleNotFoundException;
import ro.garrettmotion.automotive.repository.VehicleRepository;

@Service
@Transactional
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
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

}
