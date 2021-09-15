package ro.garrettmotion.automotive.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.repository.VehicleRepository;

@Service
@Transactional
public class VehicleService {

    private final VehicleRepository repo;

    public VehicleService(VehicleRepository repo) {
        this.repo = repo;
    }

    public List<Vehicle> listAll() {
        return repo.findAll();
    }

//    public Vehicle save(Vehicle product) {
//        repo.save(product);
//        return product;
//    }
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
