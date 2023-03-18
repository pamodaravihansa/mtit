package com.hackerthon.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.hackerthon.common.UtilTransform;
import com.hackerthon.service.getEmpService;

public class ExecuteMain {

	public static void main(String[] args) {
		final Logger logger = Logger.getLogger(ExecuteMain.class.getName());
		getEmpService employeeService = new getEmpService();
		logger.log(Level.INFO, "ExecuteMain class");
		try {
			UtilTransform.RequestTransform();
			employeeService.employeesFromXML();
			employeeService.empTableCreate();
			employeeService.empAdd();
			employeeService.empById("EMP10004");
			employeeService.empDelete("EMP10001");
			employeeService.empDisplay();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "", e);
			e.printStackTrace();
		}

	}

}
