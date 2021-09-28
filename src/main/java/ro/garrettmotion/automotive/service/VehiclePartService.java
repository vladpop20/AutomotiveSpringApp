package ro.garrettmotion.automotive.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.garrettmotion.automotive.entity.VehiclePart;
import ro.garrettmotion.automotive.exception.VehiclePartNotFoundException;
import ro.garrettmotion.automotive.repository.VehiclePartRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehiclePartService {
    private  final VehiclePartRepository vehiclePartRepository;

    public VehiclePartService(VehiclePartRepository vehiclePartRepository) {
        this.vehiclePartRepository = vehiclePartRepository;
    }

    public List<VehiclePart> listAll() {
        return vehiclePartRepository.findAll();
    }

    public VehiclePart save(VehiclePart product) {
        return vehiclePartRepository.save(product);
    }

    public VehiclePart get(Integer id) throws Exception {
        Optional<VehiclePart> vehiclePart = vehiclePartRepository.findById(id);
        if(!vehiclePart.isPresent()) {
            throw new VehiclePartNotFoundException("Vehicle part not found, with id: " + id);
        }
        return vehiclePart.get();
    }

    public void delete(Integer id) {
        vehiclePartRepository.deleteById(id);
    }
}
