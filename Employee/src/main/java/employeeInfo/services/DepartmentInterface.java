package employeeInfo.services;

import java.util.List;

import employeeInfo.entities.Department;

public interface DepartmentInterface {
	
	List<Department> getDepartments();

	Department addNewDepartment(Department department);

	Department updateDepartment(String departmentId, Department department);

	List<Department> findDepartmentByManagerId(int id);

	void deleteDepartment(String id);


}
