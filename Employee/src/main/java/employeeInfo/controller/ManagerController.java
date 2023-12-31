package employeeInfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import employeeInfo.entities.Manager;
import employeeInfo.services.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	
	
	
	@GetMapping("/managers")  // This endpoint handles GET requests for "/api/managers"
	@Operation(summary = "Get a list of managers", description = "Returns a list of all managers.")  // Operation annotation provides metadata for the Swagger documentation
	@ApiResponses(value = {
			// 200 OK response indicates successful retrieval of the list of departments
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of managers"),
            
            // 500 Internal Server Error response indicates an issue on the server side
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })    // ApiResponses annotation provides information about possible responses
	public List<Manager> getManagers(){
		// Call the managerService to get the list of departments
		return managerService.getManagers();
	}

	


}
