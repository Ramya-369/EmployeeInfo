package employeeInfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import employeeInfo.entities.Department;
import employeeInfo.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/departments")   // This endpoint handles GET requests for "/api/departments"
	@Operation(summary = "Get a list of departments", description = "Returns a list of all departments.")  // Operation annotation provides metadata for the Swagger documentation
	 @ApiResponses(value = {
	            // 200 OK response indicates successful retrieval of the list of departments
	            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of departments"),
	            
	            // 500 Internal Server Error response indicates an issue on the server side
	            @ApiResponse(responseCode = "500", description = "Internal server error")
	    })  // ApiResponses annotation provides information about possible responses
	public List<Department> getDepartments(){
		// Call the departmentService to get the list of departments
		return departmentService.getDepartments();
	}

}
