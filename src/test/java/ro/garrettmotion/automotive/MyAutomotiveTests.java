package ro.garrettmotion.automotive;

import java.time.LocalDate;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.garrettmotion.automotive.entity.Vehicle;
import ro.garrettmotion.automotive.entity.VehiclePart;
import ro.garrettmotion.automotive.entity.VehicleType;
import ro.garrettmotion.automotive.service.VehiclePartService;
import ro.garrettmotion.automotive.service.VehicleService;
import ro.garrettmotion.automotive.service.VehicleTypeService;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(classes = AutomotiveApplication.class)
@RunWith(SpringRunner.class)
//@DataJpaTest
public class MyAutomotiveTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleTypeService vehicleTypeService;

    @Autowired
    VehiclePartService vehiclePartService;

//    private final TestEntityManager entityManager;
//
//    public MyAutomotiveTests(TestEntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    public VehicleService vehicleService;
//    public VehicleTypeService vehicleTypeService;
//    public VehiclePartService vehiclePartService;

    @Test
    public void contextLoad() {

    }

    @Test
    public void should_find_all_vehicles() {
        LocalDate dateOfRegistration = LocalDate.of(2010, 3, 10);
        int sizeBeforeAdding = vehicleService.listAll().size();

        Vehicle mk1 = new Vehicle("Tesla", "NY-4501", dateOfRegistration.plusMonths(1), new VehicleType(7, "Machines"));
        entityManager.persist(mk1);

        Vehicle mk2 = new Vehicle("Audi", "LA-1010", dateOfRegistration.plusMonths(2), new VehicleType(8, "Replicators"));
        entityManager.persist(mk2);

        Vehicle mk3 = new Vehicle("VW", "TX-4554", dateOfRegistration.plusMonths(3), new VehicleType(9, "Zeno"));
        entityManager.persist(mk3);

        Iterable<Vehicle> vehicles = vehicleService.listAll();
        assertThat(vehicles).hasSize(sizeBeforeAdding + 3).contains(mk1, mk2, mk3);
    }


    @Test
    public void should_find_all_vehicle_types() {
        int sizeBeforeAdding = vehicleTypeService.listAll().size();

        VehicleType mk1 = new VehicleType(7, "Machines");
        entityManager.persist(mk1);

        VehicleType mk2 = new VehicleType(8, "Replicators");
        entityManager.persist(mk2);

        VehicleType mk3 = new VehicleType(9, "Zeno");
        entityManager.persist(mk3);

        Iterable<VehicleType> vehicleTypes = vehicleTypeService.listAll();
        assertThat(vehicleTypes).hasSize(sizeBeforeAdding + 3).contains(mk1, mk2, mk3);
    }

    @Test
    public void should_find_all_vehicles_parts() {
        int sizeBeforeAdding = vehiclePartService.listAll().size();

        VehiclePart mk1 = new VehiclePart(8, "Door", new VehicleType(7, "Machines"));
        entityManager.persist(mk1);

        VehiclePart mk2 = new VehiclePart(9, "SunRoof", new VehicleType(8, "Replicators"));
        entityManager.persist(mk2);

        VehiclePart mk3 = new VehiclePart(10, "Hood", new VehicleType(9, "Zeno"));
        entityManager.persist(mk3);

        Iterable<VehiclePart> vehicleParts = vehiclePartService.listAll();
        assertThat(vehicleParts).hasSize(sizeBeforeAdding + 3).contains(mk1, mk2, mk3);
    }

    @Test
    public void should_create_a_vehicle() {
        LocalDate dateOfRegistration = LocalDate.of(2004, 3, 13);
        VehicleType temp = new VehicleType(6, "Robots");
        Vehicle vehicle = vehicleService.save(new Vehicle("SPX-1122", "VL23CAP", dateOfRegistration, temp));

        assertThat(vehicle).hasFieldOrPropertyWithValue("id", "SPX-1122");
        assertThat(vehicle).hasFieldOrPropertyWithValue("plateNumber", "VL23CAP");
        assertThat(vehicle).hasFieldOrPropertyWithValue("dateOfRegistration", dateOfRegistration);
        assertThat(vehicle).hasFieldOrPropertyWithValue("vehicleType", temp);
    }

    @Test
    public void should_create_a_vehicle_type() {
        VehicleType vehicleType = vehicleTypeService.save(new VehicleType(6, "Robots"));

        assertThat(vehicleType).hasFieldOrPropertyWithValue("id", 6);
        assertThat(vehicleType).hasFieldOrPropertyWithValue("name", "Robots");
    }

    @Test
    public void should_create_a_vehicle_part() {
        VehicleType temp = new VehicleType(8, "Replicators");
        VehiclePart vehiclePart = vehiclePartService.save(new VehiclePart(8, "SunRoof", temp));

        assertThat(vehiclePart).hasFieldOrPropertyWithValue("id", 8);
        assertThat(vehiclePart).hasFieldOrPropertyWithValue("name", "SunRoof");
        assertThat(vehiclePart).hasFieldOrPropertyWithValue("vehicleType", temp);
    }

    @Test
    public void should_update_vehicle_by_id() {
        LocalDate dateOfRegistration = LocalDate.of(2004, 3, 13);
        VehicleType vehicleType = new VehicleType(6, "Robots");

        Vehicle gt2 = new Vehicle("SPX-1122", "VL23CAP", dateOfRegistration, vehicleType);
        entityManager.persist(gt2);

        Vehicle updatedGt = new Vehicle("PSX-3344", "B999GRE", dateOfRegistration.plusMonths(5), vehicleType);

        Vehicle gt = vehicleService.get(gt2.getId());
        gt.setId(updatedGt.getId());
        gt.setPlateNumber(updatedGt.getPlateNumber());
        gt.setDateOfRegistration(updatedGt.getDateOfRegistration());
        gt.setVehicleType(updatedGt.getVehicleType());
        vehicleService.save(gt);

        Vehicle checkGt = vehicleService.get(gt2.getId());

        assertThat(checkGt.getId()).isEqualTo(gt2.getId());
        assertThat(checkGt.getPlateNumber()).isEqualTo(updatedGt.getPlateNumber());
        assertThat(checkGt.getDateOfRegistration()).isEqualTo(updatedGt.getDateOfRegistration());
        assertThat(checkGt.getVehicleType()).isEqualTo(updatedGt.getVehicleType());
    }

    @Test
    public void should_update_vehicle_type_by_id() {
        VehicleType vt2 = new VehicleType(6, "Robots");
        entityManager.persist(vt2);

        VehicleType updatedVt = new VehicleType(13, "Headphones");

        VehicleType vt = vehicleTypeService.get(vt2.getId());
        vt.setId(updatedVt.getId());
        vt.setName(updatedVt.getName());
        vehicleTypeService.save(vt);

        VehicleType checkVt = vehicleTypeService.get(vt2.getId());

        assertThat(checkVt.getId()).isEqualTo(vt2.getId());
        assertThat(checkVt.getName()).isEqualTo(updatedVt.getName());
    }

    @Test
    public void should_update_vehicle_part_by_id() {
        VehicleType vehicleType = new VehicleType(6, "Robots");

        VehiclePart pt2 = new VehiclePart(8, "SunRoof", vehicleType);
        entityManager.persist(pt2);

        VehiclePart updatedPt = new VehiclePart(9, "DiskBrakes", vehicleType);

        VehiclePart pt = vehiclePartService.get(pt2.getId());
        pt.setId(updatedPt.getId());
        pt.setName(updatedPt.getName());
        pt.setVehicleType(updatedPt.getVehicleType());
        vehiclePartService.save(pt);

        VehiclePart checkPt = vehiclePartService.get(pt2.getId());

        assertThat(checkPt.getId()).isEqualTo(pt2.getId());
        assertThat(checkPt.getName()).isEqualTo(updatedPt.getName());
        assertThat(checkPt.getVehicleType()).isEqualTo(updatedPt.getVehicleType());
    }

    @Test
    public void should_delete_vehicle_by_id() {
        LocalDate dateOfRegistration = LocalDate.of(2010, 3, 10);
        int sizeBeforeAdding = vehicleService.listAll().size();

        Vehicle mk1 = new Vehicle("Tesla", "NY-4501", dateOfRegistration.plusMonths(1), new VehicleType(7, "Machines"));
        entityManager.persist(mk1);

        Vehicle mk2 = new Vehicle("Audi", "LA-1010", dateOfRegistration.plusMonths(2), new VehicleType(8, "Replicators"));
        entityManager.persist(mk2);

        Vehicle mk3 = new Vehicle("VW", "TX-4554", dateOfRegistration.plusMonths(3), new VehicleType(9, "Zeno"));
        entityManager.persist(mk3);

        vehicleService.delete(mk2.getId());

        Iterable<Vehicle> vehicles = vehicleService.listAll();
        assertThat(vehicles).hasSize(sizeBeforeAdding + 2).contains(mk1, mk3);
    }

    @Test
    public void should_delete_vehicle_type_by_id() {
        int sizeBeforeAdding = vehicleTypeService.listAll().size();

        VehicleType mk1 = new VehicleType(7, "Machines");
        entityManager.persist(mk1);

        VehicleType mk2 = new VehicleType(8, "Replicators");
        entityManager.persist(mk2);

        VehicleType mk3 = new VehicleType(9, "Zeno");
        entityManager.persist(mk3);

        vehicleTypeService.delete(mk2.getId());

        Iterable<VehicleType> vehicleTypes = vehicleTypeService.listAll();
        assertThat(vehicleTypes).hasSize(sizeBeforeAdding + 2).contains(mk1, mk3);
    }

    @Test
    public void should_delete_vehicle_part_by_id() {
        int sizeBeforeAdding = vehiclePartService.listAll().size();

        VehiclePart mk1 = new VehiclePart(8, "Door", new VehicleType(7, "Machines"));
        entityManager.persist(mk1);

        VehiclePart mk2 = new VehiclePart(9, "SunRoof", new VehicleType(8, "Replicators"));
        entityManager.persist(mk2);

        VehiclePart mk3 = new VehiclePart(10, "Hood", new VehicleType(9, "Zeno"));
        entityManager.persist(mk3);

        vehiclePartService.delete(mk2.getId());

        Iterable<VehiclePart> vehicleParts = vehiclePartService.listAll();
        assertThat(vehicleParts).hasSize(sizeBeforeAdding + 3).contains(mk1, mk3);
    }


}
