package employeeInfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import employeeInfo.entities.Manager;
import employeeInfo.services.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@GetMapping("/managers") // This endpoint handles GET requests for "/api/managers"
	@Operation(summary = "Get a list of managers", description = "Returns a list of all managers.") // Operation
																									// annotation
																									// provides metadata
																									// for the Swagger
																									// documentation
	@ApiResponses(value = {
			// 200 OK response indicates successful retrieval of the list of departments
			@ApiResponse(responseCode = "200", description = "Successfully retrieved the list of managers"),

			// 500 Internal Server Error response indicates an issue on the server side
			@ApiResponse(responseCode = "500", description = "Internal server error") }) // ApiResponses annotation
																							// provides information
																							// about possible responses
	public List<Manager> getManagers() {
		// Call the managerService to get the list of departments
		return managerService.getManagers();
	}

	@DeleteMapping("/managers/delete/{managerId}") // This endpoint handles DELETE requests for "/api/managers"
	@Operation(summary = "Delete managers", description = "Deletes managers of giving Id")
	@ApiResponses(value = {
			// 200 OK response indicates successful deletion of the manager.
			@ApiResponse(responseCode = "200", description = "Successfully Deleted"),
			// 404 NOT FOUND response indicates the ID of the manager is not present.
			@ApiResponse(responseCode = "404", description = "manager Id NOT FOUND"),
			// 500 Internal Server Error response indicates an issue on the server side
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	// ApiResponses annotation provides information about possible responses
	public void deleteManager(
			@Parameter(description = "managerId that is to be deleted", allowEmptyValue = false) @PathVariable("managerId") Integer id) {
		managerService.deleteManager(id);
	}

}
