package employeeInfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import employeeInfo.entities.Department;
import employeeInfo.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/departments") // This endpoint handles GET requests for "/api/departments"
	@Operation(summary = "Get a list of departments", description = "Returns a list of all departments.") // Operation
																											// annotation
																											// provides
																											// metadata
																											// for the
																											// Swagger
																											// documentation
	@ApiResponses(value = {
			// 200 OK response indicates successful retrieval of the list of departments
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the list of departments"),

			// 500 Internal Server Error response indicates an issue on the server side
			@ApiResponse(responseCode = "500", description = "Internal server error") }) // ApiResponses annotation
																							// provides information
																							// about possible responses
	public List<Department> getDepartments() {
		// Call the departmentService to get the list of departments
		return departmentService.getDepartments();
	}

	@DeleteMapping("/department/delete/{departmentId}") // This endpoint handles DELETE requests for "/api/department"
	@Operation(summary = "Delete department", description = "Deletes employees of giving Id")
	@ApiResponses(value = {
			// 200 OK response indicates successful deletion of the department.
			@ApiResponse(responseCode = "200", description = "Successfully Deleted"),
			// 404 NOT FOUND response indicates the ID of the department is not present.
			@ApiResponse(responseCode = "404", description = "employee Id NOT FOUND"),
			// 500 Internal Server Error response indicates an issue on the server side
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	// ApiResponses annotation provides information about possible responses
	public void deleteDepartment(
			@Parameter(description = "Department Id that is to be deleted", allowEmptyValue = false) @PathVariable("departmentId") String id) {
		departmentService.deleteDepartment(id);
	}

}
