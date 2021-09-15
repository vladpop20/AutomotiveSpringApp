package ro.garrettmotion.automotive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.garrettmotion.automotive.entity.VehicleType;
import ro.garrettmotion.automotive.repository.VehicleTypeRepository;

import java.util.List;

@Service
@Transactional
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepository repo;

    public List<VehicleType> listAll() {
        return repo.findAll();
    }

    public VehicleType save(VehicleType product) {
        repo.save(product);
        return product;
    }

    public VehicleType get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
