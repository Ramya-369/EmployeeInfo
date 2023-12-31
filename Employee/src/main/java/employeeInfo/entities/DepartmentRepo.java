package employeeInfo.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import employeeInfo.entities.Department;

public interface DepartmentRepo extends JpaRepository<Department,String>{

}
