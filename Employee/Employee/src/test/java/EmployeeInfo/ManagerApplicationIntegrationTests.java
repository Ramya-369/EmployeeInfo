package EmployeeInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import employeeInfo.entities.Employee;
import employeeInfo.entities.Manager;
import employeeInfo.services.ManagerService;

@SpringBootTest(classes = employeeInfo.EmployeeApplication.class)
public class ManagerApplicationIntegrationTests {

		@Autowired
		private ManagerService managerService;

		@Test
		void testGetManager() {

			// Call the service method that fetches managers
			List<Manager> managers = managerService.getManagers();

			// Assert
			assertEquals(100, managers.get(0).getManagerId());
			assertEquals("Venu", managers.get(0).getManagerName());
			assertEquals("venu@gmail.com", managers.get(0).getEmail());
		}

		@Test
		void testAddNewManager() {
			// Create a new manager
			Manager newManager= new Manager();
//			newManager.setManagerId(5);
			newManager.setManagerName("dg");
			newManager.setEmail("hjm@gmail.com");
			
			// Call the service method to add the new manager
			Manager addedManager = managerService.addNewManager(newManager);

			// Call the service method that fetches managers after adding a new one
			List<Manager> updatedManager = managerService.getManagers();

			// Assert
			assertEquals(16, updatedManager.size()); // Assuming you had 4 managers before adding the new one
//			assertEquals(newManager.getManagerId(), addedManager.getManagerId());
			assertEquals(newManager.getManagerName(), addedManager.getManagerName());
			assertEquals(newManager.getEmail(), addedManager.getEmail());

			// Ensure that adding the same manager again results in a BAD_REQUEST
			assertThrows(ResponseStatusException.class, () -> managerService.addNewManager(newManager));
		}

		@Test
		void testUpdateManager() {
			// Call the service method that fetches managers
			List<Manager> managerBeforeUpdate = managerService.getManagers();

			Manager managerToUpdate = managerBeforeUpdate.get(6);

			// Create a new manager with updated details
			Manager updatedManager= new Manager();
			updatedManager.setManagerId(managerToUpdate.getManagerId());
			updatedManager.setManagerName("rathan");
			updatedManager.setEmail("tata@gmail.com");

			// Call the service method to update the manager
			Manager result = managerService.updateManager(managerToUpdate.getManagerId(), updatedManager);

			// Assert
			assertEquals(managerToUpdate.getManagerId(), result.getManagerId());
			assertEquals("rathan", result.getManagerName());
			assertEquals("tata@gmail.com", result.getEmail());

		}

		@Test
		void testDeleteManager() {
			// Call the service method that fetches manager before deletion
			List<Manager> managerBeforeDeletion = managerService.getManagers();

			Manager managerToDelete = managerBeforeDeletion.get(5);

			// Call the service method to delete the manager
			managerService.deleteManager(managerToDelete.getManagerId());

			// Call the service method that fetches manager after deletion
			List<Manager> managerAfterDeletion = managerService.getManagers();

			// Assert
			assertEquals(managerBeforeDeletion.size() - 1, managerAfterDeletion.size());

		}

	}

