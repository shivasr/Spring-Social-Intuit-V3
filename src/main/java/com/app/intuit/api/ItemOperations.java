package com.app.intuit.api;

import java.util.List;

import com.app.intuit.domain.QBItem;


public interface ItemOperations {
	
	public QBItem getItem(String id);
	public List<QBItem> getItems();
	public QBItem update(QBItem item);
	public QBItem create(QBItem item);
	/**
	 * Depending on if there is a idType create or update is called. Essentially
	 * a wrapper around create and update to hide the logic.
	 * @param item Item item to be persisted to Intuit.
	 * @return Saved Intuit object containing the idType of saved Intuit item.
	 */
	public QBItem save(QBItem item);	
	public boolean delete(QBItem Item);
	//QBItem save(QBItem item);
	

}
