package employeeInfo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import employeeInfo.entities.Employee;
import employeeInfo.entities.EmployeeRepo;

//Define the EmployeeService interface
public interface EmployeeService {
	// Method to get a list of Employees
	List<Employee> getEmployees();

	// Method to add the Employees
	Employee addNewEmployee(Employee employee);

	// Method to Update the Employees
	Employee updateEmployee(Integer employeeId, Employee employee);

}

//Implementation of the EmployeeService interface marked as a service
@Service
class EmployeeServiceImpl implements EmployeeService {

	// Autowire the EmployeeRepo for data access
	@Autowired
	private EmployeeRepo employeeRepo;

	// Implementation of the GETEmployees method
	@Override
	public List<Employee> getEmployees() {
		try {
			// Attempt to fetch the list of employees from the repository
			List<Employee> employeeList = employeeRepo.findAll();

			// Check if the list is empty and throw an exception if no employees are found
			if (employeeList.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employees found");
			}

			// Return the list of employees if retrieval is successful
			return employeeList;
		} catch (Exception ex) {
			// Catch any exceptions that might occur during the process
			// Log the error and throw a response status exception with an internal server
			// error status
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving employees", ex);
		}
	}

	// Implementation of the postEmployees method
	@Override
	public Employee addNewEmployee(Employee employee) {
		try {
			// Attempt to fetch the list of employees from the repository
			List<Employee> existingemployee = employeeRepo.findAll();
			// Check if the list is contains the employee and throw an exception if already
			// employees are found
			if (existingemployee.contains(employee)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"A employee with the same details already exists.");
			}
			// saves the employee if there is no error
			employeeRepo.save(employee);
			// Return the employee details after successful addition
			return employee;
		} catch (Exception ex) {
			// Catch any exceptions that might occur during the process
			// Log the error and throw a response status exception with an internal server
			// error status
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

	// Implementation of the putEmployees method
	@Override
	public Employee updateEmployee(Integer employeeId, Employee employee) {
		// Attempt to fetch the list of employees from the repository
		Optional<Employee> optEmployee = employeeRepo.findById(employeeId);
		// If no employees are found it throws an exception
		if (!optEmployee.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EmployeeId Not Found..!");
		}
		try {
			// saves the Employee i there is no error.
			Employee updatedEmployee = employeeRepo.save(employee);
			// Return the employee details after successful updation.
			return updatedEmployee;
		} catch (Exception ex) {
			// Catch any exceptions that might occur during the process
			// Log the error and throw a response status exception with an internal server
			// error status
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

}
