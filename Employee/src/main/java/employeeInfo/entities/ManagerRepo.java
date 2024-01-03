package employeeInfo.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepo extends JpaRepository<Manager,Integer> {

	

}
