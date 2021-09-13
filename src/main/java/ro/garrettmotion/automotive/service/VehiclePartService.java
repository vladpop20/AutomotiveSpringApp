package ro.garrettmotion.automotive.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.garrettmotion.automotive.entity.VehiclePart;
import ro.garrettmotion.automotive.repository.VehiclePartRepository;


import java.util.List;

@Service
@Transactional
public class VehiclePartService {


    private  final VehiclePartRepository repo;

    public VehiclePartService(VehiclePartRepository repo) {
        this.repo = repo;
    }

    public List<VehiclePart> listAll() {
        return repo.findAll();
    }

    public void save(VehiclePart product) {
        repo.save(product);
    }

    public VehiclePart get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
