package com.hackerthon.service;

import com.hackerthon.model.Employee;
import com.hakerthon.common.CommonUtil;
import com.hakerthon.common.QueryUtil;
import com.hakerthon.common.UtilTransform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class DBConnection extends CommonUtil {

	private final ArrayList<Employee> el = new ArrayList<Employee>();

	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preparedStatement;

	//Create the database connection
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"),
					p.getProperty("password"));
		} catch (Exception e) {
		} 
	}

	//getting employees data from XML File and set
	public void employeesFromXML() {

		try {
			int s = UtilTransform.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> l = UtilTransform.XMLXPATHS().get(i);
				Employee EMPLOYEE = new Employee();
				EMPLOYEE.setEmployeeID(l.get("XpathEmployeeIDKey"));
				EMPLOYEE.setFullName(l.get("XpathEmployeeNameKey"));
				EMPLOYEE.setAddress(l.get("XpathEmployeeAddressKey"));
				EMPLOYEE.setFacultyName(l.get("XpathFacultyNameKey"));
				EMPLOYEE.setDepartment(l.get("XpathDepartmentKey"));
				EMPLOYEE.setDesignation(l.get("XpathDesignationKey"));
				el.add(EMPLOYEE);
				System.out.println(EMPLOYEE.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	//Creating employees table
	public void employeeTableCreate() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(QueryUtil.Q("q2"));
			statement.executeUpdate(QueryUtil.Q("q1"));
		} catch (Exception e) {
		}
	}

	//Adding an employee details to the database
	public void employeesAdd() {
		try {
			preparedStatement = connection.prepareStatement(QueryUtil.Q("q3"));
			connection.setAutoCommit(false);
			for(int i = 0; i < el.size(); i++){
				Employee employee = el.get(i);
				preparedStatement.setString(1, employee.getEmployeeID());
				preparedStatement.setString(2, employee.getFullName());
				preparedStatement.setString(3, employee.getAddress());
				preparedStatement.setString(4, employee.getFacultyName());
				preparedStatement.setString(5, employee.getDepartment());
				preparedStatement.setString(6, employee.getDesignation());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (Exception e) {
		}
	}

	//Get the employee details by ID from the database and assign
	public void employeesGetByID(String eid) {

		Employee employee = new Employee();
		try {
			preparedStatement = connection.prepareStatement(QueryUtil.Q("q4"));
			preparedStatement.setString(1, eid);
			ResultSet R = preparedStatement.executeQuery();
			while (R.next()) {
				employee.setEmployeeID(R.getString(1));
				employee.setFullName(R.getString(2));
				employee.setAddress(R.getString(3));
				employee.setFacultyName(R.getString(4));
				employee.setDepartment(R.getString(5));
				employee.setDesignation(R.getString(6));
			}
			ArrayList<Employee> l = new ArrayList<Employee>();
			l.add(employee);
			employeesOutput(l);
		} catch (Exception ex) {
		}
	}

	//Delete the employee by ID
	public void employeeDelete(String eid) {

		try {
			try {
			preparedStatement = connection.prepareStatement(QueryUtil.Q("q6"));
			}catch(NullPointerException e) {
				e.printStackTrace();
			}
			preparedStatement.setString(1, eid);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Display the employee details
	public void employeeDisplay() {

		ArrayList<Employee> l = new ArrayList<Employee>();
		try {
			preparedStatement = connection.prepareStatement(QueryUtil.Q("q5"));
			ResultSet r = preparedStatement.executeQuery();
			while (r.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(r.getString(1));
				employee.setFullName(r.getString(2));
				employee.setAddress(r.getString(3));
				employee.setFacultyName(r.getString(4));
				employee.setDepartment(r.getString(5));
				employee.setDesignation(r.getString(6));
				l.add(employee);
			}
		} catch (Exception e) {
		}
		employeesOutput(l);
	}
	
	//Display the output from an array list
	public void employeesOutput(ArrayList<Employee> l){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(int i = 0; i < l.size(); i++){
			Employee employee = l.get(i);
			System.out.println(employee.getEmployeeID() + "\t" + employee.getFullName() + "\t\t"
					+ employee.getAddress() + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
					+ employee.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
}
