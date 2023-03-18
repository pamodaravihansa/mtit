package com.hackerthon.model;

public class Employee {

	public String empID;
	public String empName;
	public String address;
	public String facultyName;
	public String department;
	public String designation;

	// default Constructor
	public Employee() {
		super();
	}

	// overloaded Constructor
	public Employee(String empID, String empName, String address, String facultyName, String department,
			String designation) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.address = address;
		this.facultyName = facultyName;
		this.department = department;
		this.designation = designation;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {

		return "Employee ID = " + empID + "\n" + "FullName = " + empName + "\n" + "Address = " + address + "\n"
				+ "Faculty Name = " + facultyName + "\n" + "Department = " + department + "\n" + "Designation = "
				+ designation;
	}

}
