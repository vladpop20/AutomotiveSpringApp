package ro.garrettmotion.automotive.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.garrettmotion.automotive.entity.VehiclePart;
import ro.garrettmotion.automotive.repository.VehiclePartRepository;


import java.util.List;

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
        vehiclePartRepository.save(product);
        return product;
    }

    public VehiclePart get(Integer id) {
        return vehiclePartRepository.findById(id).get();
    }

    public void delete(Integer id) {
        vehiclePartRepository.deleteById(id);
    }
}
