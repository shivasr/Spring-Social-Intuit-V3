/**
 * 
 */
package com.app.intuit.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

import com.app.intuit.api.EmployeeOperations;
import com.intuit.ipp.data.Employee;
import com.intuit.ipp.data.IntuitEntity;
import com.intuit.ipp.data.IntuitResponse;

/**
 * @author shiva
 *
 */
public class EmployeeTemplate implements EmployeeOperations {

	private final boolean isAuthorized;
	private final RestTemplate restTemplate;
	private final String companyId;
	private final String baseUrl;

	public EmployeeTemplate(boolean isAuthorized, RestTemplate restTemplate, String companyId, String baseUrl) {
		super();
		this.isAuthorized = isAuthorized;
		this.restTemplate = restTemplate;
		this.companyId = companyId;
		this.baseUrl = baseUrl;
	}

	public Employee getEmployee(Long employeeId) {
		requireAuthorization();
		return restTemplate.getForObject("{baseURL}/v3/company/{companyId}/customer/{employeeId}", Employee.class, baseUrl, companyId, employeeId);
	}

	public List<Employee> getEmployees() {
		
		
		requireAuthorization();		
		IntuitResponse response = restTemplate.getForObject("{baseURL}/v3/company/{companyId}/query?query=select * from Employee where active = true", IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			List<JAXBElement<? extends IntuitEntity>> intuitObjects = response.getQueryResponse().getIntuitObject();
			
			List<Employee> employees = new ArrayList<Employee>(intuitObjects.size());
			for(JAXBElement<? extends IntuitEntity> element: intuitObjects){
				Employee employee = (Employee) element.getValue();
				employees.add(employee);
			}
			return employees;
		}
		return null;
	}

	public Employee update(Employee employee) {
		requireAuthorization();
		return restTemplate.postForObject("{baseURL}/v3/company/{companyId}/employee?operation=update", employee, Employee.class, baseUrl, companyId);
	}

	public Employee create(Employee employee) {
		requireAuthorization();
		return restTemplate.postForObject("{baseURL}/v3/company/{companyId}/employee", employee, Employee.class, baseUrl, companyId);
	}

	public Employee save(Employee employee) {
		requireAuthorization();
		if(employee.getId() != null){
			return update(employee);
		}
		else {
			return create(employee);
		}
	}
	
	public boolean delete(Employee employee) {
		requireAuthorization();
		employee.setActive(false);
		
		Employee updatedEmployee = update(employee);
		boolean success = updatedEmployee.isActive();
		return success;
	}
	
	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("intuit");
		}
	}

}
