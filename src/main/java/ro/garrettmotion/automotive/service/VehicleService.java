package ro.garrettmotion.automotive.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.garrettmotion.automotive.entity.Vehicle;
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
        vehicleRepository.save(product);
        return product;
    }

    public Vehicle get(String id) {
        return vehicleRepository.findById(id).get();
    }

    public void delete(String id) {
        vehicleRepository.deleteById(id);
    }
}
