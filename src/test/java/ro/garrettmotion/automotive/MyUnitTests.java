package ro.garrettmotion.automotive;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.entity.VehiclePart;
import ro.garrettmotion.automotive.entity.VehicleType;
import ro.garrettmotion.automotive.service.VehiclePartService;
import ro.garrettmotion.automotive.service.VehicleService;
import ro.garrettmotion.automotive.service.VehicleTypeService;

import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ro.garrettmotion.automotive.AutomotiveApplication.class)
public class MyUnitTests {

    @Autowired()
    private VehicleService vehicleService;

    @Autowired()
    private VehicleTypeService vehicleTypeService;

    @Autowired()
    private VehiclePartService vehiclePartService;

    @Test
    public void contextLoad() {
    }

    @Test
    public void should_find_all_vehicles() {
        LocalDate dateOfRegistration = LocalDate.of(2010, 3, 10);
        int sizeBefore = vehicleService.listAll().size();

        VehicleType temp1 = vehicleTypeService.save(new VehicleType("Machines"));
        VehicleType temp2 = vehicleTypeService.save(new VehicleType("Replicators"));
        VehicleType temp3 = vehicleTypeService.save(new VehicleType("Rockets"));

        Vehicle mk1 = vehicleService.save(new Vehicle("Bugatti", "KN-5601", dateOfRegistration.plusMonths(1), temp1));
        Vehicle mk2 = vehicleService.save(new Vehicle("Lamborghini", "GE-3710", dateOfRegistration.plusMonths(2), temp2));
        Vehicle mk3 = vehicleService.save(new Vehicle("Peugeot", "NM-9054", dateOfRegistration.plusMonths(3), temp3));

        List<Vehicle> vehicles = vehicleService.listAll();

        assertThat(vehicles).hasSize(sizeBefore + 3).contains(mk1, mk2, mk3);
    }

    @Test
    public void should_find_all_vehicle_types() {
        int sizeBeforeAdding = vehicleTypeService.listAll().size();

        VehicleType mk1 = vehicleTypeService.save(new VehicleType("Machines"));
        VehicleType mk2 = vehicleTypeService.save(new VehicleType("Replicators"));
        VehicleType mk3 = vehicleTypeService.save(new VehicleType("Zeno"));

        List<VehicleType> vehicleTypes = vehicleTypeService.listAll();
        assertThat(vehicleTypes).hasSize(sizeBeforeAdding + 3).contains(mk1, mk2, mk3);
    }

    @Test
    public void should_find_all_vehicles_parts() {
        int sizeBefore = vehiclePartService.listAll().size();
        VehicleType temp1 = vehicleTypeService.save(new VehicleType("Machines"));
        VehicleType temp2 = vehicleTypeService.save(new VehicleType("Replicators"));
        VehicleType temp3 = vehicleTypeService.save(new VehicleType("Rockets"));

        VehiclePart mk1 = vehiclePartService.save(new VehiclePart("Door", temp1));
        VehiclePart mk2 = vehiclePartService.save(new VehiclePart("SunRoof", temp2));
        VehiclePart mk3 = vehiclePartService.save(new VehiclePart("Hood", temp3));

        List<VehiclePart> vehicleParts = vehiclePartService.listAll();
        assertThat(vehicleParts).hasSize(sizeBefore + 3).contains(mk1, mk2, mk3);
    }
//-----------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void should_create_a_vehicle() {
        LocalDate dateOfRegistration = LocalDate.of(2004, 3, 13);
        VehicleType temp = vehicleTypeService.save(new VehicleType("Robots"));

        Vehicle vehicle = vehicleService.save(new Vehicle("SPX-1122", "VL23CAP", dateOfRegistration, temp));

        assertNotNull(vehicle);
        assertThat(vehicle).hasFieldOrPropertyWithValue("id", "SPX-1122");
        assertThat(vehicle).hasFieldOrPropertyWithValue("plateNumber", "VL23CAP");
        assertThat(vehicle).hasFieldOrPropertyWithValue("dateOfRegistration", dateOfRegistration);
        assertThat(vehicle).hasFieldOrPropertyWithValue("vehicleType", temp);
    }

    @Test
    public void should_create_a_vehicle_type() {
        VehicleType vehicleType = vehicleTypeService.save(new VehicleType("Robots"));

        assertNotNull(vehicleType);
        assertThat(vehicleType).hasFieldOrPropertyWithValue("id", vehicleType.getId());
        assertThat(vehicleType).hasFieldOrPropertyWithValue("name", "Robots");
    }

    @Test
    public void should_create_a_vehicle_part() {
        VehicleType temp = vehicleTypeService.save(new VehicleType("Replicators"));

        VehiclePart vehiclePart = vehiclePartService.save(new VehiclePart("SunRoof", temp));

        assertNotNull(vehiclePart);
        assertThat(vehiclePart).hasFieldOrPropertyWithValue("id", vehiclePart.getId());
        assertThat(vehiclePart).hasFieldOrPropertyWithValue("name", "SunRoof");
        assertThat(vehiclePart).hasFieldOrPropertyWithValue("vehicleType", temp);
    }

//-----------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void should_update_vehicle_by_id() throws Exception {
        LocalDate dateOfRegistration = LocalDate.of(2004, 3, 13);
        VehicleType vehicleType = vehicleTypeService.save(new VehicleType("Robots"));

        Vehicle firstVehicle = vehicleService.save(new Vehicle("SPX-1122", "VL23CAP", dateOfRegistration, vehicleType));
        Vehicle secondVehicle = new Vehicle("SPX-1122", "B999GRE", dateOfRegistration.plusMonths(5), vehicleType);

        Vehicle firstVehUpdated = vehicleService.get(firstVehicle.getId());
        firstVehUpdated.setPlateNumber(secondVehicle.getPlateNumber());
        firstVehUpdated.setDateOfRegistration(secondVehicle.getDateOfRegistration());
        firstVehUpdated.setVehicleType(secondVehicle.getVehicleType());
        vehicleService.save(firstVehUpdated);

        Vehicle checkVeh = vehicleService.get(firstVehicle.getId());

        assertThat(checkVeh.getPlateNumber()).isNotNull().isEqualTo(secondVehicle.getPlateNumber());
        assertThat(checkVeh.getDateOfRegistration()).isNotNull().isEqualTo(secondVehicle.getDateOfRegistration());
        assertThat(checkVeh.getVehicleType()).isNotNull().isEqualTo(secondVehicle.getVehicleType());
    }

    @Test
    public void should_update_vehicle_type_by_id() throws Exception {
        VehicleType firstVehTp = vehicleTypeService.save(new VehicleType("Robots"));
        VehicleType secVehTp = vehicleTypeService.save(new VehicleType("Headphones"));

        VehicleType firstUpdated = vehicleTypeService.get(firstVehTp.getId());
        firstUpdated.setName(secVehTp.getName());
        vehicleTypeService.save(firstUpdated);

        VehicleType checkVehicleType = vehicleTypeService.get(firstVehTp.getId());

        assertThat(checkVehicleType.getName()).isNotNull().isEqualTo(secVehTp.getName());
    }

    @Test
    public void should_update_vehicle_part_by_id() throws Exception {
        VehicleType vehicleType = vehicleTypeService.save(new VehicleType("Robots"));

        VehiclePart firstVehPt = vehiclePartService.save(new VehiclePart("SunRoof", vehicleType));
        VehiclePart secVehPt = vehiclePartService.save(new VehiclePart("DiskBrakes", vehicleType));

        VehiclePart firstUpdated = vehiclePartService.get(firstVehPt.getId());
        firstUpdated.setName(secVehPt.getName());
        firstUpdated.setVehicleType(secVehPt.getVehicleType());
        vehiclePartService.save(firstUpdated);

        VehiclePart checkPt = vehiclePartService.get(firstVehPt.getId());

        assertThat(checkPt.getName()).isNotNull().isEqualTo(secVehPt.getName());
        assertThat(checkPt.getVehicleType()).isNotNull().isEqualTo(secVehPt.getVehicleType());
    }

//-----------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void should_delete_vehicle_by_id() {
        int sizeBeforeAdding = vehicleService.listAll().size();

        LocalDate dateOfRegistration = LocalDate.of(2010, 3, 10);
        VehicleType vehType1 = vehicleTypeService.save(new VehicleType("Machines"));
        VehicleType vehType2 = vehicleTypeService.save(new VehicleType("Replicators"));


        Vehicle mk1 = vehicleService.save(new Vehicle("Tesla", "NY-4501", dateOfRegistration, vehType1));
        Vehicle mk2 = vehicleService.save(new Vehicle("Audi", "LA-1010", dateOfRegistration.plusMonths(2), vehType1));
        Vehicle mk3 = vehicleService.save(new Vehicle("VW", "TX-4554", dateOfRegistration.plusMonths(3), vehType2));

        vehicleService.delete(mk2.getId());

        List<Vehicle> vehicles = vehicleService.listAll();
        assertThat(vehicles).hasSize(sizeBeforeAdding + 2).contains(mk1, mk3);
    }

    @Test
    public void should_delete_vehicle_type_by_id() {
        int sizeBeforeAdding = vehicleTypeService.listAll().size();

        VehicleType mk1 = vehicleTypeService.save(new VehicleType("Machines"));
        VehicleType mk2 = vehicleTypeService.save(new VehicleType("Replicators"));
        VehicleType mk3 = vehicleTypeService.save(new VehicleType("Zeno"));

        vehicleTypeService.delete(mk2.getId());

        List<VehicleType> vehicleTypes = vehicleTypeService.listAll();
        assertThat(vehicleTypes).hasSize(sizeBeforeAdding + 2).contains(mk1, mk3);
    }

    @Test
    public void should_delete_vehicle_part_by_id() {
        int sizeBeforeAdding = vehiclePartService.listAll().size();

        VehicleType vehType1 = vehicleTypeService.save(new VehicleType("Machines"));
        VehicleType vehType2 = vehicleTypeService.save(new VehicleType("Replicators"));

        VehiclePart mk1 = vehiclePartService.save(new VehiclePart("Door", vehType1));
        VehiclePart mk2 = vehiclePartService.save(new VehiclePart("SunRoof", vehType2));
        VehiclePart mk3 = vehiclePartService.save(new VehiclePart("Hood", vehType2));

        vehiclePartService.delete(mk2.getId());

        List<VehiclePart> vehicleParts = vehiclePartService.listAll();
        assertThat(vehicleParts).hasSize(sizeBeforeAdding + 2).contains(mk1, mk3);
    }
}
