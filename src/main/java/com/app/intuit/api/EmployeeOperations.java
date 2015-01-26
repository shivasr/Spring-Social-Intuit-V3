package com.app.intuit.api;

import java.util.List;

import com.intuit.ipp.data.Employee;

public interface EmployeeOperations {
	
	public Employee getEmployee(Long id);
	public List<Employee> getEmployees();
	public Employee update(Employee employee);
	public Employee create(Employee employee);
	/**
	 * Depending on if there is a idType create or update is called. Essentially
	 * a wrapper around create and update to hide the logic.
	 * @param customer Customer item to be persisted to Intuit.
	 * @return Saved Intuit object containing the idType of saved Intuit item.
	 */
	public Employee save(Employee employee);		
	public boolean delete(Employee employee);

}
