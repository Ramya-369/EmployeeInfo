//package EmployeeInfo.junit;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import employeeInfo.services.EmployeeService;
//
//@SpringBootTest
//public class EmployeeServiceUnitTest {
//
//	EmployeeService employeeService;
//
//	@BeforeAll
//	void beforeAllInit() {
//		System.out.println("This needs to run before all");
//	}
//
//	@BeforeEach
//	void init() {
//		employeeService = new EmployeeService();
//	}
//
//	@AfterAll
//	static void afterAllInit() {
//		System.out.println("This needs to run after all");
//	}
//
//	@AfterEach
//	void cleanup() {
//		System.out.println("cleaning up");
//	}
//
//	@Test
//	void testgetEmployees() {
//
//		int expected = -2;
//		int actual=-2;
//		assertEquals(expected,actual);
//	}
//}
