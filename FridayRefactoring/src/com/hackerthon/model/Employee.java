package com.hackerthon.model;

public class Employee {

	public String employeeID;
	public String fullName;
	public String Address;
	public String facultyName;
	public String Department;
	public String Designation;
	
	//Employee ID Getter
	public String getEmployeeID() {
		return employeeID;
	}
	//Employee ID Setter
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
	//FullName Getter
	public String getFullName() {
		return fullName;
	}
	//FullName Setter
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	//Address Getter
	public String getAddress() {
		return Address;
	}
	//Address Setter
	public void setAddress(String address) {
		Address = address;
	}
	
	//FacultyName Getter
	public String getFacultyName() {
		return facultyName;
	}
	//FacultyName Setter
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
	//Department Getter
	public String getDepartment() {
		return Department;
	}
	//Department Setter
	public void setDepartment(String department) {
		Department = department;
	}
	
	//Designation Getter
	public String getDesignation() {
		return Designation;
	}
	//Designation Setter
	public void setDesignation(String designation) {
		Designation = designation;
	}
	
	@Override
	public String toString() {
		
		return "Employee ID = " + employeeID + "\n" + "FullName = " + fullName + "\n" + "Address = " + Address + "\n"
				+ "Faculty Name = " + facultyName + "\n" + "Department = " + Department + "\n" + "Designation = "
				+ Designation;
	}
}
