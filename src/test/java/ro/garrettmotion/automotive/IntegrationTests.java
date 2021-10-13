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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class IntegrationTests {


    @BeforeClass
    public static void beforeClass() throws Exception {
        RestAssured.port = 8080;
//        RestAssured.basePath = "/vehicles";
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
        vehicle.setVehicleTypeID(vehType);
        return vehicle;
    }


    @Test
    public void whenGetAllVehicles_thenOk() {
        Response response = RestAssured.get( "/vehicles/all");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetAllVehicleTypes_thenOk() {
        Response response = RestAssured.get( "/vehicletypes/all");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetAllVehicleParts_thenOk() {
        Response response = RestAssured.get( "/vehicleparts/all");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
// -----------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void invalidVehicleByID() {
        given().when().get("/vehicles/get/999").then().statusCode(404);
    }

    @Test
    public void invalidVehicleTypeByID() {
        given().when().get("/vehicletypes/get/999").then().statusCode(404);
    }

    @Test
    public void invalidVehiclePartByID() {
        given().when().get("/vehicleparts/get/999").then().statusCode(404);
    }

// -----------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void whenCreateNewVehicle_thenCreated() {
        Map<String, String> car = new HashMap<>();
        car.put("id", "CZ-2041Z");
        LocalDate dateOfRegistration = LocalDate.of(1998, 2, 19);
        car.put("dateOfRegistration", dateOfRegistration.toString());
        car.put("plateNumber", "NY-5601");
        car.put("vehicleTypeID", "2");
        given().contentType("application/json").body(car).when().post("/vehicles/add").then().statusCode(201);
    }

    @Test
    public void whenCreateNewVehicleType_thenCreated() {
        Map<String, String> carType = new HashMap<>();
        carType.put("name", "Black Keyboard");
        given().contentType("application/json").body(carType).when().post("/vehicletypes/add").then().statusCode(201);
    }

    @Test
    public void whenCreateNewVehiclePart_thenCreated() {
        Map<String, String> carPart = new HashMap<>();
        carPart.put("name", "CentralConsole");
        carPart.put("vehicleTypeID", "2");
        given().contentType("application/json").body(carPart).when().post("/vehicleparts/add").then().statusCode(201);
    }

// -----------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void whenUpdateCreatedVehicle_thenUpdated() {
        Map<String, String> car = new HashMap<>();
        LocalDate dateOfRegistration = LocalDate.of(1998, 2, 19);
        car.put("dateOfRegistration", dateOfRegistration.toString());
        car.put("vehicleTypeID", "2");
        car.put("plateNumber", "odeNumber");       // I updated the plateNumber with a new value

        String vehiclePlateNumber = given().contentType("application/json")
                .body(car)
                .when()
                .put("vehicles/update/1AAAABBBBCCCCDDDD")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .path("plateNumber");

        assertThat(vehiclePlateNumber).isEqualTo("odeNumber");
    }

    @Test
    public void whenUpdateCreatedVehicleType_thenUpdated() {
        Map<String, String> carType = new HashMap<>();
        carType.put("name", "StrangeType");    // I updated the name with a new value

        String vehicleTypeName = given().contentType("application/json")
                .body(carType)
                .when()
                .put("vehicletypes/update/3")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .path("name");

        assertThat(vehicleTypeName).isEqualTo("StrangeType");
    }

    @Test
    public void whenUpdateCreatedVehiclePart_thenUpdated() {
        Map<String, String> carPart = new HashMap<>();
        carPart.put("name", "BackSeat");      // I updated the name of the VehiclePart with a new value
        carPart.put("vehicleTypeID", "2");

        String carPartName = given().contentType("application/json")
                .body(carPart)
                .when()
                .put("vehicleparts/update/3")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .path("name");

        assertThat(carPartName).isEqualTo("BackSeat");
    }

//  -----------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void whenDeleteCreatedVehicle_thenOk() {
//        given().pathParam("VIN", "BG-2041Z")
//                .when().delete("/vehicles/delete/{VIN}")
//                .then().statusCode(HttpStatus.OK.value());

        Response response = RestAssured.delete("/vehicles/delete/CZ-2041Z");
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode());

//        response = RestAssured.get("/vehicles/delete/CZ-2041Z");
//        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void whenDeleteCreatedVehicleType_thenOk() {
        given().pathParam("vehicleTypeId", 3)
                .when().delete("/vehicletypes/delete/{vehicleTypeId}")
                .then().statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void whenDeleteCreatedVehiclePart_thenOk() {
        given().pathParam("vehiclePartId", 4)
                .when().delete("/vehicleparts/delete/{vehiclePartId}")
                .then().statusCode(HttpStatus.NO_CONTENT.value());
    }

}
