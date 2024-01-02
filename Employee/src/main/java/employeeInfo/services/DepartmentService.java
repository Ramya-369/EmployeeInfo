package employeeInfo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import employeeInfo.entities.Department;
import employeeInfo.entities.DepartmentRepo;


//Define the DepartmentService interface
public interface DepartmentService {

	// Method to get a list of departments
	List<Department> getDepartments();
	//Method to add the Departments
	Department addNewDepartment(Department department) ;
	//Method to update the Departments
	Department updateDepartment(String departmentId, Department department);

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
            // Log the error and throw a response status exception with an internal server error status
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving departments", ex);
        }

	}
	
	 // Implementation of the PostDepartments method
		@Override
		public Department addNewDepartment(Department department) {
			try {
				// Attempt to fetch the list of departments from the repository
			List<Department> existingdepartment = departmentRepo.findAll();
			// Check if the list contains the details and throw an exception if already  department is  found
			if (existingdepartment.contains(department)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"A department with the same details already exists.");
			}
			//saves the department if there is no error.
			departmentRepo.save(department);
			//Return the  department details after successful addition.
			return department;
		}catch (Exception ex) {
			  // Catch any exceptions that might occur during the process
            // Log the error and throw a response status exception with an internal server error status
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
			}
		
	//Implementation of the PutDepartments method
		 @Override
		    public Department updateDepartment(String departmentId, Department department) {
			    // Attempt to fetch the list of departments from the repository
		        Optional<Department> optDepartment = departmentRepo.findById(departmentId);
		        //If no departments are found it throws an exception
		        if (!optDepartment.isPresent()) {
		            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DepartmentId Not Found..!");
		        }
		        try {
		        	//If department is found it updates the details 
		            Department updatedDepartment = departmentRepo.save(department);
		            //Return the updatedDepartment details after successful updation.
		            return updatedDepartment;
		        } catch (DataAccessException ex) {
		        	  // Catch any exceptions that might occur during the process
		            // Log the error and throw a response status exception with an internal server error status
		            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		        }
		    }
	
	
	
	
	
	
	

}
