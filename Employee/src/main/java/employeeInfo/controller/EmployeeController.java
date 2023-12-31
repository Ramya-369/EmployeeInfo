package employeeInfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import employeeInfo.entities.Employee;
import employeeInfo.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
    
	@GetMapping("/employees")  // This endpoint handles GET requests for "/api/employees"
    @Operation(summary = "Get a list of employees", description = "Returns a list of all employees.")  // Operation annotation provides metadata for the Swagger documentation
    @ApiResponses(value = {
            // 200 OK response indicates successful retrieval of the list of employees
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of employees"),
            // 500 Internal Server Error response indicates an issue on the server side
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })  // ApiResponses annotation provides information about possible responses
    public List<Employee> getEmployees() {
        // Call the employeeService to get the list of employees
        return employeeService.getEmployees();
    }
}
