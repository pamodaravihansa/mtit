package com.hackerthon.common;

import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

public class UtilTransform extends UtilC {

	private static final ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();

	private static Map<String, String> map = null;

	public static void RequestTransform() throws Exception {

		Source reqXml = new StreamSource(new File(property.getProperty("empReqXml")));
		Source modXml = new StreamSource(new File(property.getProperty("empModiXml")));
		Result result = new StreamResult(new File(property.getProperty("empResponse")));
		TransformerFactory.newInstance().newTransformer(modXml).transform(reqXml, result);
	}

	public static ArrayList<Map<String, String>> XMLXPATHS() throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(property.getProperty("empResponse"));
		XPath xPath = XPathFactory.newInstance().newXPath();
		int n = Integer.parseInt(
				(String) xPath.compile("count(//Employees/Employee)").evaluate(document, XPathConstants.STRING));
		for (int i = 1; i <= n; i++) {
			map = new HashMap<String, String>();
			map.put("XpathEmployeeIDKey", (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathEmployeeNameKey",
					(String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()").evaluate(document,
							XPathConstants.STRING));
			map.put("XpathEmployeeAddressKey",
					(String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()")
							.evaluate(document, XPathConstants.STRING));
			map.put("XpathFacultyNameKey", (String) xPath.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathDepartmentKey", (String) xPath.compile("//Employees/Employee[" + i + "]/Department/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathDesignationKey", (String) xPath.compile("//Employees/Employee[" + i + "]/Designation/text()")
					.evaluate(document, XPathConstants.STRING));
			list.add(map);
		}
		return list;
	}
}
