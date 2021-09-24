package ro.garrettmotion.automotive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.garrettmotion.automotive.entity.VehicleType;
import ro.garrettmotion.automotive.exception.VehicleTypeNotFoundException;
import ro.garrettmotion.automotive.repository.VehicleTypeRepository;

@Service
@Transactional
public class VehicleTypeService {
    private final VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeService(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    public List<VehicleType> listAll() {
        return vehicleTypeRepository.findAll();
    }

    public VehicleType save(VehicleType product) {
        vehicleTypeRepository.save(product);
        return product;
    }

    public VehicleType get(Integer id) throws Exception {
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(id);
        if(!vehicleType.isPresent()) {
            throw new VehicleTypeNotFoundException("Vehicle type not found, with id: " + id);
        }
        return vehicleType.get();
    }

    public void delete(Integer id) {
        vehicleTypeRepository.deleteById(id);
    }
}
