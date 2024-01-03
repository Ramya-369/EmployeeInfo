package employeeInfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import employeeInfo.entities.Employee;
import employeeInfo.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees") // This endpoint handles GET requests for "/api/employees"
	@Operation(summary = "Get a list of employees", description = "Returns a list of all employees.") 
	// Operation annotation provides metadata for the Swagger documentation																									
	@ApiResponses(value = {
			// 200 OK response indicates successful retrieval of the list of employees
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the list of employees"),
			// 500 Internal Server Error response indicates an issue on the server side
			@ApiResponse(responseCode = "500", description = "Internal server error") }) // ApiResponses annotation
																							// provides information
																							// about possible responses
	public List<Employee> getEmployees() {
		// Call the employeeService to get the list of employees
		return employeeService.getEmployees();
	}

	@PostMapping("/addEmployee") // This endpoint handles POST requests for "/api/employees"
	@Operation(summary = "Adds a new employee", description = "To create and add a new employee to the system.") 
	// Operation annotation provides metadata for the Swagger documentation																					
	@ApiResponses(value = {
			// 200 OK response indicates successful creation of the employee
			@ApiResponse(responseCode = "201", description = "Employee created successfully"),
			// 400 Bad request Error response indicates an Invalid input data
			@ApiResponse(responseCode = "400", description = "Bad request. Invalid input data"),
			// 500 Internal Server Error response indicates an issue on the server side
			@ApiResponse(responseCode = "500", description = "Internal server error") }) // ApiResponses annotation
																							// provides information
																							// about possible responses
	public Employee addNewEmployee(@Valid @RequestBody Employee employee) {
		// Call the employeeService to add the employee
		return employeeService.addNewEmployee(employee);
	}

	@PutMapping("/updateEmployee/{employeeId}") // This endpoint handles PUT requests for "/api/employees"
	@Operation(summary = "Updates an existing employee", description = "To modify the details of an existing employee") 
	// Operation annotation provides metadata for the Swagger documentation																							
	@ApiResponses(value = {
			// 200 OK response indicates successful updation of the department
			@ApiResponse(responseCode = "200", description = "Department updated successfully"),
			// 400 Bad request Error response indicates an Invalid input data
			@ApiResponse(responseCode = "400", description = "Bad request. Invalid input data"),
			// 404 NOT found Error response indicates that employee not found
			@ApiResponse(responseCode = "404", description = "Department not found"),
			// 500 Internal Server Error response indicates an issue on the server side
			@ApiResponse(responseCode = "500", description = "Internal server error") }) // ApiResponses annotation
																							// provides information
																							// about possible responses
	public Employee updateEmployee(@PathVariable("employeeId") Integer employeeId,
			@Valid @RequestBody Employee employee) {
		// Call the employeeService to update the employee
		return employeeService.updateEmployee(employeeId, employee);
	}

}
