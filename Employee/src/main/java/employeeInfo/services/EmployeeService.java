package employeeInfo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import employeeInfo.entities.Employee;
import employeeInfo.entities.EmployeeRepo;

//Define the EmployeeService interface
public interface EmployeeService {
	// Method to get a list of employees
	List<Employee> getEmployees();

}

//Implementation of the EmployeeService interface marked as a service
@Service
class EmployeeServiceImpl implements EmployeeService {

 // Autowire the EmployeeRepo for data access
 @Autowired
 private EmployeeRepo employeeRepo;

 // Implementation of the getEmployees method
 @Override
 public List<Employee> getEmployees() {
     try {
         // Attempt to fetch the list of employees from the repository
         List<Employee> employeeList = employeeRepo.findAll();

         // Check if the list is empty and throw an exception if no employees are found
         if (employeeList.isEmpty()) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employees found");
         }

         // Return the list of employees if retrieval is successful
         return employeeList;
     } catch (Exception ex) {
         // Catch any exceptions that might occur during the process
         // Log the error and throw a response status exception with an internal server error status
         throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving employees", ex);
     }
 }
}
