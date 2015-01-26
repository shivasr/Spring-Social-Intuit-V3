package com.app.intuit.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

import com.app.intuit.api.CustomerOperations;
import com.app.intuit.domain.QbCustomer;
import com.app.intuit.util.QBToFMMapperUtilities;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.IntuitEntity;
import com.intuit.ipp.data.IntuitResponse;
import com.intuit.ipp.data.QueryResponse;


public class CustomerTemplate implements CustomerOperations {
	
	public static final Logger LOG = Logger.getLogger(CustomerTemplate.class);
	
	private final boolean isAuthorized;
	private final RestTemplate restTemplate;
	private final String companyId;
	private final String baseUrl;

	public CustomerTemplate(boolean isAuthorized, RestTemplate restTemplate, String companyId, String baseUrl) {
		super();
		this.isAuthorized = isAuthorized;
		this.restTemplate = restTemplate;
		this.companyId = companyId;
		this.baseUrl = baseUrl;
	}

	// Read	/company/:companyId/customer/:entityId
	public QbCustomer getCustomer(Long customerId) {
		requireAuthorization();
		QbCustomer qbCustomer = null;
		
		IntuitResponse response = restTemplate.getForObject("{baseURL}/v3/company/{companyId}/customer/{customerID}", IntuitResponse.class, baseUrl, companyId, customerId);
		if(response != null){
			Customer customer = null;
			customer = (Customer) response.getIntuitObject().getValue();
			qbCustomer = QBToFMMapperUtilities.convertToQbCustomer(customer);
			return qbCustomer;
		}
		return null;
	}
	
	// Query	/company/:companyId/query?query=:query
	public List<QbCustomer> getCustomers() {
		
		
		requireAuthorization();		
		IntuitResponse response = restTemplate.getForObject("{baseURL}/v3/company/{companyId}/query?query=select * from Customer", IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			QueryResponse queryResponse = response.getQueryResponse();
			List<JAXBElement<? extends IntuitEntity>> intuitObjects = queryResponse.getIntuitObject();
			
			List<QbCustomer> customers = new ArrayList<QbCustomer>(intuitObjects.size());
			for(JAXBElement<? extends IntuitEntity> element: intuitObjects){
				Customer customer = (Customer) element.getValue();
				QbCustomer qbCustomer = QBToFMMapperUtilities.convertToQbCustomer(customer);
				customers.add(qbCustomer);
			}
			return customers;
		}
		return null;
	}

	// Update	/company/:companyId/customer?operation=update
	public QbCustomer update(QbCustomer sparseCustomer) {
		requireAuthorization();
		
		QbCustomer qbCustomer = null;
		//return restTemplate.postForObject("{baseURL}/v3/company/{companyId}/customer?operation=update", customer, IntuitResponse.class, baseUrl, companyId);
		IntuitResponse response = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/customer?operation=update", sparseCustomer, IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			Customer customer = (Customer) response.getIntuitObject().getValue();
			qbCustomer = QBToFMMapperUtilities.convertToQbCustomer(customer);
			return qbCustomer;
		}
		
		return null;
	}

	// Create	/company/:companyId/customer
	public QbCustomer create(QbCustomer sparseCustomer) {
		requireAuthorization();
		
		// return restTemplate.postForObject("{baseURL}/v3/company/{companyId}/customer", customer, Customer.class, baseUrl, companyId);
		IntuitResponse response = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/customer", sparseCustomer, IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			Customer customer = (Customer) response.getIntuitObject().getValue();
			QbCustomer qbCustomer = QBToFMMapperUtilities.convertToQbCustomer(customer);
			return qbCustomer;
		}
		
		return null;
	}

	// Calls create and update
	public QbCustomer save(QbCustomer sparseCustomer) {
		requireAuthorization();
		if(sparseCustomer.getId() != null){
			return update(sparseCustomer);
		}
		else {
			return create(sparseCustomer);
		}
	}
	
	// DELETE
	public boolean delete(QbCustomer sparseCustomer) {
		requireAuthorization();
		sparseCustomer.setActive(false);
		
		QbCustomer updatedCustomer = update(sparseCustomer);
		boolean success = updatedCustomer.isActive();
		return success;
	}
	
	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("intuit");
		}
	}

}
