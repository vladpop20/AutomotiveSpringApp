package ro.garrettmotion.automotive;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.entity.VehicleType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        classes = ro.garrettmotion.automotive.AutomotiveApplication.class)
public class IntegrationTests {

//    @LocalServerPort
//    private int portOfApp;
//
//    private String getRootUrl() {
//        return "http://localhost:" + portOfApp;
//    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        RestAssured.port = 8080;
        RestAssured.basePath = "/vehicles";
        RestAssured.baseURI = "http://localhost";
    }

    private Vehicle createRandomVehicle() {
        Vehicle vehicle = new Vehicle();
        LocalDate dateOfRegistration = LocalDate.of(1998, 2, 19);
        vehicle.setId("BG-2041Z");
        vehicle.setPlateNumber("NY-5601");
        vehicle.setDateOfRegistration(dateOfRegistration);
        VehicleType vehType = new VehicleType("Machines");
        vehType.setId(2);
        vehicle.setVehicleType(vehType);
        return vehicle;
    }

    @Test
    public void basicPingTest() {
        given().when().get("/all").then().statusCode(200);
//        given().when().get("/vehicles/all").then().statusCode(200);
    }

    @Test
    public void whenGetAllVehicles_thenOk() {
//        Response response = RestAssured.get(getRootUrl() + "/vehicles/all");
        Response response = RestAssured.get( "/all");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

//    @Test
//    public void whenCreateNewVehicle_thenCreated() {
//        Vehicle vehicle = createRandomVehicle();
//
////        Response response = given()
////            .contentType(MediaType.APPLICATION_JSON_VALUE)
////            .body(vehicle)
////            .post(getRootUrl() + "/vehicles/add");
//
//        Response response = given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(vehicle)
//                .post("/add");
//
//        assertNotNull(response);
//        assertNotNull(response.getBody());
//        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
//    }


    @Test
    public void whenCreateNewVehicle_thenCreated() {

        Map<String, String> car = new HashMap<>();
        car.put("id", "BG-2041Z");
        LocalDate dateOfRegistration = LocalDate.of(1998, 2, 19);
        car.put("dateOfRegistration", dateOfRegistration.toString());
        car.put("plateNumber", "NY-5601");
        car.put("vehicleType", "2");
        given().contentType("application/json").body(car).when().post("/add").then().statusCode(201);
    }





}
