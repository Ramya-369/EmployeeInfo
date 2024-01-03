package employeeInfo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import employeeInfo.entities.Manager;
import employeeInfo.entities.ManagerRepo;

// Define the ManagerService interface
public interface ManagerService {
    // Method to get a list of managers
    List<Manager> getManagers();
    Manager addNewManager(Manager manager);
    Manager updateManager(Integer managerId, Manager manager);
}

// Implementation of the ManagerService interface marked as a service
@Service
class ManagerServiceImpl implements ManagerService {

    // Autowire the ManagerRepo for data access
    @Autowired
    private ManagerRepo managerRepo;

    // Implementation of the getManagers method
    @Override
    public List<Manager> getManagers() {
        try {
            // Attempt to fetch the list of managers from the repository
            List<Manager> managerList = managerRepo.findAll();

            // Check if the list is empty and throw an exception if no managers are found
            if (managerList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No managers found");
            }

            // Return the list of managers if retrieval is successful
            return managerList;
        } catch (Exception ex) {
            // Catch any exceptions that might occur during the process
            // Log the error and throw a response status exception with an internal server error status
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving managers", ex);
        }
    }
    
    // Implementation of the postManagers method
    @Override
	public Manager addNewManager(Manager manager) {
    	try {
    		// Attempt to fetch the list of managers from the repository
		List<Manager> existingManager = managerRepo.findAll();
		// Check if the list is contains and throw an exception if already managers are found
		if (existingManager.contains(manager)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"A manager with the same details already exists.");
		}
		//saves the manager if there is no errors
		managerRepo.save(manager);
		//Return the manager after successful addtion
		return manager;
	}catch (Exception ex) {
		// Catch any exceptions that might occur during the process
        // Log the error and throw a response status exception with an internal server error status
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving managers", ex);
	}}
 // Implementation of the putManagers method
	 @Override
	    public Manager updateManager(Integer managerId, Manager manager) {
		// Attempt to fetch the list of managers from the repository
	        Optional<Manager> optManager = managerRepo.findById(managerId);
	        // Check if the list is contains and throw an exception if  managers are not found
	        if (!optManager.isPresent()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ManagerId Not Found..!");
	        }
	        try {
	        	//saves the manager if there is no errors 
	            Manager updatedManager = managerRepo.save(manager);
	            //Returns the manager after successful updation
	            return updatedManager;
	        } catch (Exception ex) {
	        	// Catch any exceptions that might occur during the process
	            // Log the error and throw a response status exception with an internal server error status
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	        }
	    }
    
    
    
    
    
    
    
}