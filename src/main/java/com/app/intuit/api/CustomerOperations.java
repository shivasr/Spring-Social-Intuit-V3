package com.app.intuit.api;

import java.util.List;

import com.app.intuit.domain.QbCustomer;

public interface CustomerOperations {
	
	public QbCustomer getCustomer(Long id);
	public List<QbCustomer> getCustomers();
	public QbCustomer update(QbCustomer customer);
	public QbCustomer create(QbCustomer customer);
	/**
	 * Depending on if there is a idType create or update is called. Essentially
	 * a wrapper around create and update to hide the logic.
	 * @param customer Customer item to be persisted to Intuit.
	 * @return Saved Intuit object containing the idType of saved Intuit item.
	 */
	public QbCustomer save(QbCustomer customer);		
	public boolean delete(QbCustomer customer);

}
