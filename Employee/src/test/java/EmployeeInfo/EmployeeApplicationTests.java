package EmployeeInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import employeeInfo.entities.Employee;
import employeeInfo.entities.EmployeeRepo;
import employeeInfo.services.EmployeeService;


@ExtendWith(MockitoExtension.class)
class EmployeeApplicationTests {

	@Mock
	private EmployeeRepo employeeRepo;
    @InjectMocks
	private EmployeeService employeeService;

	@BeforeEach
	public void init() {
		employeeService = new EmployeeService(employeeRepo);
	}

	@Test
    void testGetEmployees() {
        
        when(employeeRepo.findAll()).thenReturn(List.of(
                new Employee(1, "Ramya", "Menda", "F", "Srikakulam", "ramyamenda369@gmail.com", 630334054, "CSM22")
        ));

        List<Employee> employees = employeeService.getEmployees();
        assertEquals(1, employees.size());
       
        assertEquals(1, employees.get(0).getEmployeeId());
        assertEquals("Ramya", employees.get(0).getFirstName());
        assertEquals("Menda", employees.get(0).getLastName());
    }
}
