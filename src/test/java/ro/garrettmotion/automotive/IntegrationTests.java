package ro.garrettmotion.automotive;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.entity.VehicleType;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ro.garrettmotion.automotive.AutomotiveApplication.class)
public class IntegrationTests {

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }


    private Vehicle createRandomVehicle() {
        Vehicle vehicle = new Vehicle();
        LocalDate dateOfRegistration = LocalDate.of(1998, 2, 19);
        vehicle.setId("BG-2041Z");
        vehicle.setPlateNumber("NY-5601");
        vehicle.setDateOfRegistration(dateOfRegistration);
        vehicle.setVehicleType(new VehicleType("Machines"));
        return vehicle;
    }

    @Test
    public void whenGetAllVehicles_thenOk() {
        Response response = RestAssured.get(getRootUrl() + "/vehicles/all");

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenCreateNewVehicle_thenCreated() {
//        Vehicle vehicle = createRandomVehicle();

        Vehicle vehicle = new Vehicle();
        LocalDate dateOfRegistration = LocalDate.of(1998, 2, 19);
        vehicle.setId("BG-2041Z");
        vehicle.setPlateNumber("NY-5601");
        vehicle.setDateOfRegistration(dateOfRegistration);
        vehicle.setVehicleType(new VehicleType("Machines"));

        Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(vehicle)
            .post(getRootUrl() + "/vehicles/add");

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

    }



}
