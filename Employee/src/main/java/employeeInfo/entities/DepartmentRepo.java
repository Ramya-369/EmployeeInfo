package employeeInfo.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import employeeInfo.entities.Department;

public interface DepartmentRepo extends JpaRepository<Department,String>{

	List<Department> findBymanagerId(int id);
}
