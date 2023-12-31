package employeeInfo.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Employeeid")
	private int employeeId;

	@NotBlank(message = "First name cannot be blank")
	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	@Column(name = "Firstname")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	@Column(name = "Lastname")
	private String lastName;

	@NotNull(message = "Gender cannot be null")
	@Column(name = "Gender")
	private Character gender;

	@NotBlank(message = "Address cannot be blank")
	@Column(name = "Address")
	private String address;

	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Invalid email format")
	@Column(name = "Email")
	private String email;

	@Pattern(regexp = "[6-9]\\d{9}", message = "Phone number must start with 6, 7, 8, or 9 and have 10 digits")
	@Positive(message = "Phone number must be positive")
	@Column(name = "Phoneno")
	private int phoneNo;

	@NotBlank(message = "Department ID cannot be blank")
	@Column(name = "Departmentid")
	private String departmentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "Departmentid", insertable = false, updatable = false)
	private Department department;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", address=" + address + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", departmentId=" + departmentId + ", department=" + department + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, phoneNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& phoneNo == other.phoneNo;
	}

}
