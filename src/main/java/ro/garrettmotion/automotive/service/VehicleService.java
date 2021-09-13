package ro.garrettmotion.automotive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.repository.VehicleRepository;

@Service
@Transactional
public class VehicleService {

    @Autowired
    private VehicleRepository repo;

    public List<Vehicle> listAll() {
        return repo.findAll();
    }

    public void save(Vehicle product) {
        repo.save(product);
    }

    public Vehicle get(String id) {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
