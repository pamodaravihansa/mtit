package com.hackerthon.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.hackerthon.common.UtilTransform;
import com.hackerthon.model.Employee;
import java.sql.ResultSet;
import java.sql.Statement;
import com.hackerthon.common.UtilQ;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hackerthon.common.UtilC;

public class getEmpService extends UtilC {
	final Logger logger = Logger.getLogger(getEmpService.class.getName());
	private final ArrayList<Employee> emp = new ArrayList<Employee>();

	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preparedStatement;

	public getEmpService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(property.getProperty("url"), property.getProperty("username"),
					property.getProperty("password"));
			logger.log(Level.INFO, "Database Conneted");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Database Failure", e);
			e.printStackTrace();
		} 
	}

	public void employeesFromXML() {

		try {
			int s = UtilTransform.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> l = UtilTransform.XMLXPATHS().get(i);
				Employee employee = new Employee();
				employee.setEmpID(l.get(property.getProperty("xPathEmployeeIDKey")));
				employee.setEmpName(l.get(property.getProperty("xPathEmployeeNameKey")));
				employee.setAddress(l.get(property.getProperty("xPathEmployeeAddressKey")));
				employee.setFacultyName(l.get(property.getProperty("xPathFacultyNameKey")));
				employee.setDepartment(l.get(property.getProperty("xPathDepartmentKey")));
				employee.setDesignation(l.get(property.getProperty("xPathDesignationKey")));
				emp.add(employee);
				System.out.println(employee.toString() + "\n");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Employees loading fail from XXL", e);
			e.printStackTrace();
		}
	}

	public void empTableCreate() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(UtilQ.Q("q2"));
			statement.executeUpdate(UtilQ.Q("q1"));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "failed to create employee table", e);
			e.printStackTrace();
		}
	}

	public void empAdd() {
		try {
			preparedStatement = connection.prepareStatement(UtilQ.Q("q3"));
			connection.setAutoCommit(false);
			for(int i = 0; i < emp.size(); i++){
				Employee employee = emp.get(i);
				preparedStatement.setString(1, employee.getEmpID());
				preparedStatement.setString(2, employee.getEmpName());
				preparedStatement.setString(3, employee.getAddress());
				preparedStatement.setString(4, employee.getFacultyName());
				preparedStatement.setString(5, employee.getDepartment());
				preparedStatement.setString(6, employee.getDesignation());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "employee adding failure", e);
			e.printStackTrace();
		}
	}

	public void empById(String eid) {

		Employee employee = new Employee();
		try {
			preparedStatement = connection.prepareStatement(UtilQ.Q("q4"));
			preparedStatement.setString(1, eid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee.setEmpID(resultSet.getString(1));
				employee.setEmpName(resultSet.getString(2));
				employee.setAddress(resultSet.getString(3));
				employee.setFacultyName(resultSet.getString(4));
				employee.setDepartment(resultSet.getString(5));
				employee.setDesignation(resultSet.getString(6));
			}
			ArrayList<Employee> emp = new ArrayList<Employee>();
			emp.add(employee);
			empOutput(emp);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed getting employee using Id", e);
			e.printStackTrace();
		}
	}

	public void empDelete(String eid) {

		try {
			preparedStatement = connection.prepareStatement(UtilQ.Q("q6"));
			preparedStatement.setString(1, eid);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed deleting employee", e);
			e.printStackTrace();
		}
	}

	public void empDisplay() {

		ArrayList<Employee> emp = new ArrayList<Employee>();
		try {
			preparedStatement = connection.prepareStatement(UtilQ.Q("q5"));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmpID(resultSet.getString(1));
				employee.setEmpName(resultSet.getString(2));
				employee.setAddress(resultSet.getString(3));
				employee.setFacultyName(resultSet.getString(4));
				employee.setDepartment(resultSet.getString(5));
				employee.setDesignation(resultSet.getString(6));
				emp.add(employee);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed displaying employees", e);
			e.printStackTrace();
		}
		empOutput(emp);
	}
	
	public void empOutput(ArrayList<Employee> emp){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(Employee employee :emp){
			System.out.println(employee.getEmpID() + "\t" + employee.getEmpName()+ "\t\t"
					+ employee.getAddress() + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
					+ employee.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
}
