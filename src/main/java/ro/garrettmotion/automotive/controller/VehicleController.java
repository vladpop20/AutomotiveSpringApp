package ro.garrettmotion.automotive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.garrettmotion.automotive.RestConstants;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.entity.VehiclePart;
import ro.garrettmotion.automotive.entity.VehicleType;
import ro.garrettmotion.automotive.service.VehiclePartService;
import ro.garrettmotion.automotive.service.VehicleService;
import ro.garrettmotion.automotive.service.VehicleTypeService;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class VehicleController {

    private final VehicleService vehicleService;

    private final VehicleTypeService vehicleTypeService;

    private final VehiclePartService vehiclePartService;

    public VehicleController(VehicleService vehicleService, VehicleTypeService vehicleTypeService, VehiclePartService vehiclePartService) {
        this.vehicleService = vehicleService;
        this.vehicleTypeService = vehicleTypeService;
        this.vehiclePartService = vehiclePartService;
    }



//   Retrive all individual record from DB
    @GetMapping("vehicles/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> listVehicles = vehicleService.listAll();

        return new ResponseEntity<>(listVehicles, HttpStatus.OK);
    }

    @GetMapping("/vehicletypes/all")
    public ResponseEntity<List<VehicleType>> getAllVehicleType() {
        List<VehicleType> listVehicleTypes = vehicleTypeService.listAll();

        return new ResponseEntity<>(listVehicleTypes, HttpStatus.OK);
    }

    @GetMapping("/vehicleparts/all")
    public ResponseEntity<List<VehiclePart>> getAllVehicleParts() {
        List<VehiclePart> listVehicleParts = vehiclePartService.listAll();

        return new ResponseEntity<>(listVehicleParts, HttpStatus.OK);
    }



//   Creates new records on DB

//    @PostMapping("/vehicles/add")
//    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
//        try {
//            Vehicle _vehicle = vehicleService
//                    .save(new Vehicle(vehicle.getId(), vehicle.getPlateNumber(), vehicle.getDateOfRegistration(), vehicle.getVehicleType()));
//            return new ResponseEntity<>(_vehicle, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        //return vehicleService.save(vehicle);
//    }

    @PostMapping("/vehicles/add")
    public String createVehicle(@Valid @RequestBody Vehicle vehicle) {
        vehicleService.save(vehicle);

        return "redirect:/vehicles/all";
    }

    @PostMapping("/vehicletypes/add")
    public String createVehicleType(@Valid @RequestBody VehicleType vehicleType) {
        vehicleTypeService.save(vehicleType);

        return "redirect:/vehicletypes/all";
    }

    @PostMapping("/vehicleparts/add")
    public String createVehiclePart(@Valid @RequestBody VehiclePart vehiclePart) {
        vehiclePartService.save(vehiclePart);

        return "redirect:/vehicleparts/all";
    }



//  Updates one record at a time from DB
    @PutMapping("/vehicles/update/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable(name = "vehicleId") String vehicleId, @Valid @RequestBody Vehicle vehicleDetails) {
        Vehicle vehicle = vehicleService.get(vehicleId);

        vehicle.setDateOfRegistration(vehicleDetails.getDateOfRegistration());
        vehicle.setPlateNumber(vehicleDetails.getPlateNumber());
        vehicle.setVehicleType(vehicleDetails.getVehicleType());

        return new ResponseEntity<Vehicle>(vehicleService.save(vehicle), HttpStatus.OK);
    }

    @PutMapping("/vehicletypes/update/{vehicleTypeId}")
    public ResponseEntity<VehicleType> updateVehicleType(@PathVariable(name = "vehicleTypeId") int vehicleTypeId) {
        VehicleType vehicleType = vehicleTypeService.get(vehicleTypeId);

        return new ResponseEntity<VehicleType>(vehicleTypeService.save(vehicleType), HttpStatus.OK);
    }

    @PutMapping("/vehicleparts/update/{vehiclePartId}")
    public ResponseEntity<VehiclePart> updateVehiclePart(@PathVariable(name = "vehiclePartId") int vehiclePartId) {
        VehiclePart vehiclePart = vehiclePartService.get(vehiclePartId);

        return new ResponseEntity<VehiclePart>(vehiclePartService.save(vehiclePart), HttpStatus.OK);
    }



//  Deletes one record at a time from DB
    @DeleteMapping("/vehicles/delete/{vehicleId}")
    public String deleteVehicle(@PathVariable(name = "VIN") String vehicleId) {

        vehicleService.delete(vehicleId);

        return "redirect:/vehicles/all";
    }

    @DeleteMapping("/vehicletypes/delete/{vehicleTypeId}")
    public String deleteVehicleType(@PathVariable(name = "ID") int vehicleTypeId) {

        vehicleTypeService.delete(vehicleTypeId);

        return "redirect:/vehicletypes/all";
    }

    @DeleteMapping("/vehicleparts/delete/{vehiclePartId}")
    public String deleteVehiclePart(@PathVariable(name = "ID") int vehiclePartId) {

        vehiclePartService.delete(vehiclePartId);

        return "redirect:/vehicleparts/all";
    }


}
