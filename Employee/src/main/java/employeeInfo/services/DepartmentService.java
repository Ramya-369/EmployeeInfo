package employeeInfo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import employeeInfo.entities.Department;
import employeeInfo.entities.DepartmentRepo;

//Define the DepartmentService interface
public interface DepartmentService {

	// Method to get a list of departments
	List<Department> getDepartments();

	void deleteDepartment(String id);

}

//Implementation of the DepartmentService interface marked as a service
@Service
class DepartmentServiceImpl implements DepartmentService {

	// Autowire the DepartmentRepo for data access
	@Autowired
	private DepartmentRepo departmentRepo;

	// Implementation of the getDepartments method
	@Override
	public List<Department> getDepartments() {
		try {
			// Attempt to fetch the list of departments from the repository
			List<Department> departmentList = departmentRepo.findAll();

			// Check if the list is empty and throw an exception if no departments are found
			if (departmentList.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Departments found");
			}

			// Return the list of departments if retrieval is successful
			return departmentList;
		} catch (Exception ex) {
			// Catch any exceptions that might occur during the process
			// Log the error and throw a response status exception with an internal server
			// error status
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving departments", ex);
		}

	}

	// Implementation of the deleteDepartment method
	@Override
	public void deleteDepartment(String id) {
		try {
			// Attempt to fetch the department id from the repository
			var department = departmentRepo.findById(id);

			// Check if the departmentId is Present
			if (department.isPresent()) {
				// deleting a department
				departmentRepo.deleteById(id);
			} else {
				// If department id is not found throwing an exception
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No department found");
			}

		} catch (Exception ex) {
			// Catch any exceptions that might occur during the process
			// Log the error and throw a response status exception with an internal server
			// error status
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Deleting department", ex);
		}
	}

}
