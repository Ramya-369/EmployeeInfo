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

	void deleteManager(int id);

	// public Manager addNewManager(Manager manager);

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
			// Log the error and throw a response status exception with an internal server
			// error status
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving managers", ex);
		}
	}

	// Implementation of the deleteManagers method
	@Override
	public void deleteManager(int id) {
		try {
			// Attempt to fetch the manager id from the repository
			var manager = managerRepo.findById(id);

			// Check if the managerId is Present
			if (manager.isPresent()) {
				// deleting a manager
				managerRepo.deleteById(id);
			} else {
				// If Manager id is not found throwing an exception
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No managers found");
			}

		} catch (ResponseStatusException ex) {
			// throw response status error
			throw ex;
		} catch (Exception ex) {
			// Catch any exceptions that might occur during the process
			// Log the error and throw a response status exception with an internal server
			// error status
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
		}
	}

//	@Override
//	public Manager addNewManager(Manager manager) {
//		List<Manager> existingManager = managerRepo.findAll();
//		if (existingManager.contains(manager)) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//					"A manager with the same details already exists.");
//		}
//		managerRepo.save(manager);
//		return manager;
//	}
