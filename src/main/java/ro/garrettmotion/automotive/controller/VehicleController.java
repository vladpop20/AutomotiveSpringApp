package ro.garrettmotion.automotive.controller;

import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.garrettmotion.automotive.RestConstants;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.entity.VehiclePart;
import ro.garrettmotion.automotive.entity.VehicleType;
import ro.garrettmotion.automotive.service.VehiclePartService;
import ro.garrettmotion.automotive.service.VehicleService;
import ro.garrettmotion.automotive.service.VehicleTypeService;

import java.util.List;

@RequestMapping(RestConstants.BASE_PATH)
@Controller
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

        return new ResponseEntity<List<Vehicle>>(listVehicles, HttpStatus.OK);
    }

    @GetMapping("/vehicletypes/all")
    public ResponseEntity<List<VehicleType>> getAllVehicleType() {
        List<VehicleType> listVehicleTypes = vehicleTypeService.listAll();

        return new ResponseEntity<List<VehicleType>>(listVehicleTypes, HttpStatus.OK);
    }

    @GetMapping("/vehicleparts/all")
    public ResponseEntity<List<VehiclePart>> getAllVehicleParts() {
        List<VehiclePart> listVehicleParts = vehiclePartService.listAll();

        return new ResponseEntity<List<VehiclePart>>(listVehicleParts, HttpStatus.OK);
    }



//   Creates new records on DB
    @PostMapping("/vehicles/add")
    public String addVehicle(Vehicle vehicle) {
        vehicleService.save(vehicle);

        return "redirect:/vehicles/all";
    }

    @PostMapping("/vehicletypes/add")
    public String addVehicleType(VehicleType vehicleType) {
        vehicleTypeService.save(vehicleType);

        return "redirect:/vehicletypes/all";
    }

    @PostMapping("/vehicleparts/add")
    public String addVehiclePart(VehiclePart vehiclePart) {
        vehiclePartService.save(vehiclePart);

        return "redirect:/vehicleparts/all";
    }



//  Updates one record at a time from DB
    @PutMapping("/vehicles/update/{vehicleId}")
    public String updateVehicle(@PathVariable(name = "VIN") String vehicleId) {

        Vehicle vehicle = vehicleService.get(vehicleId);

        return "redirect:/vehicles/all";
    }

    @PutMapping("/vehicletypes/update/{vehicleTypeId}")
    public String updateVehicleType(@PathVariable(name = "ID") int vehicleTypeId) {

        VehicleType vehicleType = vehicleTypeService.get(vehicleTypeId);

        return "redirect:/vehicletypes/all";
    }

    @PutMapping("/vehicleparts/update/{vehiclePartId}")
    public String updateVehiclePart(@PathVariable(name = "ID") int vehiclePartId) {

        VehiclePart vehiclePart = vehiclePartService.get(vehiclePartId);

        return "redirect:/vehicleparts/all";
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
