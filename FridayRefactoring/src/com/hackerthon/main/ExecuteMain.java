package com.hackerthon.main;

import com.hakerthon.common.UtilTransform;
import com.hackerthon.service.DBConnection;


public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DBConnection employeeService = new DBConnection();
		
		try {
			UtilTransform.requestTransform();
			employeeService.employeesFromXML();
			employeeService.employeeTableCreate();
			employeeService.employeesAdd();		
			employeeService.employeeDisplay();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
