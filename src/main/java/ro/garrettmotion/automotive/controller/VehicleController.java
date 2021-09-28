package ro.garrettmotion.automotive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.entity.VehiclePart;
import ro.garrettmotion.automotive.entity.VehicleType;
import ro.garrettmotion.automotive.exception.VehicleNotFoundException;
import ro.garrettmotion.automotive.repository.VehicleRepository;
import ro.garrettmotion.automotive.service.VehiclePartService;
import ro.garrettmotion.automotive.service.VehicleService;
import ro.garrettmotion.automotive.service.VehicleTypeService;


import javax.validation.Valid;
import java.util.Optional;
import java.util.Optional.*;
import java.util.List;

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

//----------------------------------------------------------------------------------------------------------------------
//   Retrive all individual record from DB

    @GetMapping("vehicles/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> listVehicles = vehicleService.listAll();

        return new ResponseEntity<>(listVehicles, HttpStatus.OK);
    }

    @GetMapping("vehicletypes/all")
    public ResponseEntity<List<VehicleType>> getAllVehicleType() {
        List<VehicleType> listVehicleTypes = vehicleTypeService.listAll();

        return new ResponseEntity<>(listVehicleTypes, HttpStatus.OK);
    }

    @GetMapping("vehicleparts/all")
    public ResponseEntity<List<VehiclePart>> getAllVehicleParts() {
        List<VehiclePart> listVehicleParts = vehiclePartService.listAll();

        return new ResponseEntity<>(listVehicleParts, HttpStatus.OK);
    }

//----------------------------------------------------------------------------------------------------------------------
//   Creates new records on DB

//    @PostMapping("vehicles/add")
//    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
//        try {
//            Vehicle _vehicle = vehicleService
//                    .save(new Vehicle(vehicle.getId(), vehicle.getPlateNumber(), vehicle.getDateOfRegistration(), vehicle.getVehicleType()));
//            return new ResponseEntity<>(_vehicle, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("vehicles/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @PostMapping("vehicletypes/add")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleType createVehicleType(@Valid @RequestBody VehicleType vehicleType) {
        return vehicleTypeService.save(vehicleType);
    }

    @PostMapping("vehicleparts/add")
    @ResponseStatus(HttpStatus.CREATED)
    public VehiclePart createVehiclePart(@Valid @RequestBody VehiclePart vehiclePart) {
        return vehiclePartService.save(vehiclePart);
    }

//----------------------------------------------------------------------------------------------------------------------
//  Updates one record at a time from DB

    @PutMapping("vehicles/update/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable(name = "vehicleId") String vehicleId, @Valid @RequestBody Vehicle vehicleDetails) throws Exception {
//        Vehicle vehicle = vehicleService.get(vehicleId).orElseThrow(VehicleNotFoundException);
        Vehicle vehicle = vehicleService.get(vehicleId);

        vehicle.setDateOfRegistration(vehicleDetails.getDateOfRegistration());
        vehicle.setPlateNumber(vehicleDetails.getPlateNumber());
        vehicle.setVehicleType(vehicleDetails.getVehicleType());

        return new ResponseEntity<>(vehicleService.save(vehicle), HttpStatus.OK);

//        return ResponseEntity.ok(vehicleService.save(vehicle));
    }

    @PutMapping("vehicletypes/update/{vehicleTypeId}")
    public ResponseEntity<VehicleType> updateVehicleType(@PathVariable(name = "vehicleTypeId") int vehicleTypeId, @Valid @RequestBody VehicleType vehicleTypeDetails) throws Exception {
        VehicleType vehicleType = vehicleTypeService.get(vehicleTypeId);

        vehicleType.setName(vehicleTypeDetails.getName());

        return new ResponseEntity<>(vehicleTypeService.save(vehicleType), HttpStatus.OK);
    }

    @PutMapping("vehicleparts/update/{vehiclePartId}")
    public ResponseEntity<VehiclePart> updateVehiclePart(@PathVariable(name = "vehiclePartId") int vehiclePartId, @Valid @RequestBody VehiclePart vehiclePartDetails) throws Exception {
        VehiclePart vehiclePart = vehiclePartService.get(vehiclePartId);

        vehiclePart.setName(vehiclePartDetails.getName());
        vehiclePart.setVehicleType(vehiclePartDetails.getVehicleType());

        return new ResponseEntity<>(vehiclePartService.save(vehiclePart), HttpStatus.OK);
    }

//----------------------------------------------------------------------------------------------------------------------
//  Deletes one record at a time from DB

    @DeleteMapping("vehicles/delete/{vehicleId}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable(name = "vehicleId") String vehicleId) {
        try {
            vehicleService.delete(vehicleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("vehicletypes/delete/{vehicleTypeId}")
    public ResponseEntity<HttpStatus> deleteVehicleType(@PathVariable(name = "vehicleTypeId") int vehicleTypeId) {
        try {
            vehicleTypeService.delete(vehicleTypeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("vehicleparts/delete/{vehiclePartId}")
    public ResponseEntity<HttpStatus> deleteVehiclePart(@PathVariable(name = "vehiclePartId") int vehiclePartId) {
        try {
            vehiclePartService.delete(vehiclePartId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//---------------------------------------------------------------------------------------------------------------------
//    Get arguments by ID

    @GetMapping("vehicles/get/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable(name = "vehicleId") String vehicleId) throws Exception {
        try {
            Vehicle vehicle = vehicleService.get(vehicleId);
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } catch(VehicleNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("vehicletypes/get/{vehicleTypeId}")
    public ResponseEntity<VehicleType> getVehicleTypeById(@PathVariable(name = "vehicleTypeId") Integer vehicleTypeId) throws Exception {
        try {
            VehicleType vehicleType = vehicleTypeService.get(vehicleTypeId);
            return new ResponseEntity<>(vehicleType, HttpStatus.OK);
        } catch(VehicleNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("vehicleparts/get/{vehiclePartId}")
    public ResponseEntity<VehiclePart> getVehiclePartById(@PathVariable(name = "vehiclePartId") Integer vehiclePartId) throws Exception {
        try {
            VehiclePart vehiclePart = vehiclePartService.get(vehiclePartId);
            return new ResponseEntity<>(vehiclePart, HttpStatus.OK);
        } catch(VehicleNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
