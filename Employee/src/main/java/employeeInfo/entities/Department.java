package employeeInfo.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Departments")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Departmentid")
	private String departmentId;

	@NotBlank(message = "Department name cannot be blank")
	@Size(min = 2, max = 20, message = "Department name must be between 2 and 30 characters")
	@Column(name = "Departmentname")
	private String departmentName;

	@Positive(message = "Manager ID must be a positive integer")
	@Column(name = "Managerid")
	private int managerId;

	@JsonIgnore
	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
	private List<Employee> employees;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "Managerid", referencedColumnName = "Managerid", insertable = false, updatable = false)
	private Manager manager;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", managerId="
				+ managerId + ", employees=" + employees + ", manager=" + manager + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentId, departmentName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(departmentId, other.departmentId) && Objects.equals(departmentName, other.departmentName);
	}

}
