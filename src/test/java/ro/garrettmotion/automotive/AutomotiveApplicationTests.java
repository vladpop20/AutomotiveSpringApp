package ro.garrettmotion.automotive;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.garrettmotion.automotive.entity.*;
import ro.garrettmotion.automotive.service.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
class AutomotiveApplicationTests {
//
//	private final TestEntityManager entityManager;
//
//	VehicleService vehicleService;
//	VehicleTypeService vehicleTypeService;
//	VehiclePartService vehiclePartService;
//
//	AutomotiveApplicationTests(TestEntityManager entityManager) {
//		this.entityManager = entityManager;
//	}
//
//
//	@Test
//	public void should_find_all_vehicles() {
//		LocalDate dateOfRegistration = LocalDate.of(2010, 3, 10);
//		int sizeBeforeAdding = vehicleService.listAll().size();
//
//		Vehicle mk1 = new Vehicle("Tesla", "NY-4501", dateOfRegistration.plusMonths(1), new VehicleType(7, "Machines"));
//		entityManager.persist(mk1);
//
//		Vehicle mk2 = new Vehicle("Audi", "LA-1010", dateOfRegistration.plusMonths(2), new VehicleType(8, "Replicators"));
//		entityManager.persist(mk2);
//
//		Vehicle mk3 = new Vehicle("VW", "TX-4554", dateOfRegistration.plusMonths(3), new VehicleType(9, "Zeno"));
//		entityManager.persist(mk3);
//
//		Iterable<Vehicle> vehicles = vehicleService.listAll();
//		assertThat(vehicles).hasSize(sizeBeforeAdding + 3).contains(mk1, mk2, mk3);
//	}

	@Test
	public void contextLoad() {

	}
}
