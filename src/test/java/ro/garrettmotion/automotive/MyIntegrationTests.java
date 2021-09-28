package ro.garrettmotion.automotive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.entity.VehicleType;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ro.garrettmotion.automotive.AutomotiveApplication.class)
@AutoConfigureMockMvc
public class MyIntegrationTests {

//    @Autowired
//    private MockMvc mvc;

    @Autowired
    private TestRestTemplate restTemplate;

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
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/vehicles/all",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
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

        ResponseEntity<Vehicle> postResponse = restTemplate.postForEntity(getRootUrl() + "/vehicles/add", vehicle, Vehicle.class);

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.CREATED.value(), postResponse.getStatusCodeValue());
    }

//    @Test
//    public void whenUpdateCreatedVehicle_thenUpdated() {
//        Vehicle vehicle = createRandomVehicle();
//        vehicle.setPlateNumber("TTT-23");
//
//        restTemplate.put(getRootUrl() + "/vehicles/add/" + vehicle.getId(), vehicle);
//        Vehicle updatedVehicle = restTemplate.getForObject(getRootUrl() + "vehicles/get/" + id, Vehicle.class);
//        assertNotNull(updatedEmployee);
//    }
//
//    @Test
//    public void whenDeleteCreatedVehicle_thenOk() {
//
//        Vehicle vehicle = restTemplate.getForObject(getRootUrl() + "vehicles/get/" + id, Vehicle.class);
//        assertNotNull(vehicle);
//        restTemplate.delete(getRootUrl() + "/vehicles/delete/" + id);
//        try {
//            vehicle = restTemplate.getForObject(getRootUrl() + "vehicles/get/" + id, Employee.class);
//        } catch (final HttpClientErrorException e) {
//            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
//        }
//    }



}
