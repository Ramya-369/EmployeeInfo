package EmployeeInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import employeeInfo.entities.Employee;
import employeeInfo.services.EmployeeService;

@SpringBootTest(classes = employeeInfo.EmployeeApplication.class)
class EmployeeApplicationIntegrationTests {

	@Autowired
	private EmployeeService employeeService;

	@Test
	void testGetEmployees() {

		// Call the service method that fetches employees
		List<Employee> employees = employeeService.getEmployees();

		// Assert
		assertEquals("Ramya", employees.get(0).getFirstName());
		assertEquals("Menda", employees.get(0).getLastName());
	}

	@Test
	void testAddNewEmployee() {
		// Create a new employee
		Employee newEmployee = new Employee();
		newEmployee.setEmployeeId(10);
		newEmployee.setFirstName("Mahi");
		newEmployee.setLastName("Dasi");
		newEmployee.setGender("F");
		newEmployee.setAddress("Sklm");
		newEmployee.setEmail("mahi@gmail.com");
		newEmployee.setPhoneNo(990088776);
		newEmployee.setDepartmentId("EDI11");
		// Call the service method to add the new employee
		Employee addedEmployee = employeeService.addNewEmployee(newEmployee);

		// Call the service method that fetches employees after adding a new one
		List<Employee> updatedEmployees = employeeService.getEmployees();

		// Assert
		assertEquals(newEmployee.getEmployeeId(), addedEmployee.getEmployeeId());
		assertEquals(newEmployee.getFirstName(), addedEmployee.getFirstName());
		assertEquals(newEmployee.getLastName(), addedEmployee.getLastName());

		// Ensure that adding the same employee again results in a BAD_REQUEST
		assertThrows(ResponseStatusException.class, () -> employeeService.addNewEmployee(newEmployee));
	}

	@Test
	void testUpdateEmployee() {
		// Call the service method that fetches employees
		List<Employee> employeesBeforeUpdate = employeeService.getEmployees();

		Employee employeeToUpdate = employeesBeforeUpdate.get(6);

		// Create a new employee with updated details
		Employee updatedEmployee = new Employee();
		updatedEmployee.setEmployeeId(employeeToUpdate.getEmployeeId());
		updatedEmployee.setFirstName("priyanka");
		updatedEmployee.setLastName("dasari");
		updatedEmployee.setGender("F");
		updatedEmployee.setAddress("Sklm");
		updatedEmployee.setEmail("priya@gmail.com");
		updatedEmployee.setPhoneNo(99876552);
		updatedEmployee.setDepartmentId("CSM22");

		// Call the service method to update the employee
		Employee result = employeeService.updateEmployee(employeeToUpdate.getEmployeeId(), updatedEmployee);

		// Assert
		assertEquals(employeeToUpdate.getEmployeeId(), result.getEmployeeId());
		assertEquals("priyanka", result.getFirstName());
		assertEquals("dasari", result.getLastName());

	}

	@Test
	void testDeleteEmployee() {
		// Call the service method that fetches employees before deletion
		List<Employee> employeesBeforeDeletion = employeeService.getEmployees();

		Employee employeeToDelete = employeesBeforeDeletion.get(6);

		// Call the service method to delete the employee
		employeeService.deleteEmployee(employeeToDelete.getEmployeeId());

		// Call the service method that fetches employees after deletion
		List<Employee> employeesAfterDeletion = employeeService.getEmployees();

		// Assert
		assertEquals(employeesBeforeDeletion.size() - 1, employeesAfterDeletion.size());

	}

}
