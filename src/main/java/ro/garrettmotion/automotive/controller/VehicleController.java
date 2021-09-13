package ro.garrettmotion.automotive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Autowired
    private VehiclePartService vehiclePartService;


    @GetMapping(value = RestConstants.VEHICLES + RestConstants.ALL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> listVehicles = vehicleService.listAll();

        return new ResponseEntity<List<Vehicle>>(listVehicles, HttpStatus.OK);
    }

    @GetMapping(value = RestConstants.VEHICLETYPES + RestConstants.ALL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<VehicleType>> getAllVehicleType() {
        List<VehicleType> listVehicleTypes = vehicleTypeService.listAll();

        return new ResponseEntity<List<VehicleType>>(listVehicleTypes, HttpStatus.OK);
    }

    @GetMapping(value = RestConstants.VEHICLEPARTS + RestConstants.ALL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<VehiclePart>> getAllVehicleParts() {
        List<VehiclePart> listVehicleParts = vehiclePartService.listAll();

        return new ResponseEntity<List<VehiclePart>>(listVehicleParts, HttpStatus.OK);
    }

//    @RequestMapping("/all")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }
//
//    @RequestMapping("/all")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }
//
//    @RequestMapping("/all")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }
//
//    @RequestMapping("/all")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }
//
//    @RequestMapping("/all")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }
//
//    @RequestMapping("/all")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }
//
//    @RequestMapping("/all")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }
//
//    @RequestMapping("/all")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }
//
//    @RequestMapping("/all")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }
//
//    @RequestMapping("/all")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
//        model.addAttribute("listProducts", listProducts);
//
//        return "index";
//    }
}
