package com.app.intuit.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

import com.app.intuit.api.ItemOperations;
import com.app.intuit.domain.QBItem;
import com.app.intuit.domain.QBVendor;
import com.app.intuit.util.QBUtilities;
import com.intuit.ipp.data.IntuitEntity;
import com.intuit.ipp.data.IntuitResponse;
import com.intuit.ipp.data.Item;
import com.intuit.ipp.data.QueryResponse;
import com.intuit.ipp.data.Vendor;

public class ItemTemplate implements ItemOperations{

	
	public static final Logger LOG = Logger.getLogger(ItemTemplate.class);
	private final boolean isAuthorized;
	private final RestTemplate restTemplate;
	private final String companyId;
	private final String baseUrl;

	public ItemTemplate(boolean isAuthorized, RestTemplate restTemplate, String companyId, String baseUrl) {
		super();
		this.isAuthorized = isAuthorized;
		this.restTemplate = restTemplate;
		this.companyId = companyId;
		this.baseUrl = baseUrl;
	}	
	
	@Override
	public QBItem getItem(String itemId) {
		
		requireAuthorization();
		QBItem qbItem = null;
		IntuitResponse response = restTemplate.getForObject("{baseURL}/v3/company/{companyId}/item/{itemId}", IntuitResponse.class, baseUrl, companyId, itemId);
		if(response != null){
			Item item = null;
			item = (Item) response.getIntuitObject().getValue();
			qbItem = QBUtilities.convertToQBItem(item);
			return qbItem;
		}
		
		return null;
	}

	@Override
	public List<QBItem> getItems() {
		// TODO Auto-generated method stub
		
		requireAuthorization();		
		IntuitResponse response = restTemplate.getForObject("{baseURL}/v3/company/{companyId}/query?query=select * from item", IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			QueryResponse queryResponse = response.getQueryResponse();
			List<JAXBElement<? extends IntuitEntity>> intuitObjects = queryResponse.getIntuitObject();
			
			List<QBItem> items = new ArrayList<QBItem>(intuitObjects.size());
			for(JAXBElement<? extends IntuitEntity> element: intuitObjects){
				Item item = (Item) element.getValue();
				QBItem qbItem = QBUtilities.convertToQBItem(item);
				items.add(qbItem);
			}
			return items;
		}
		return null;
	}

	@Override
	public QBItem update(QBItem sparseItem) {
		requireAuthorization();
		
		QBItem qbItem = null;
		//return restTemplate.postForObject("{baseURL}/v3/company/{companyId}/customer?operation=update", customer, IntuitResponse.class, baseUrl, companyId);
		IntuitResponse response = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/item?operation=update", sparseItem, IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			Item item = (Item) response.getIntuitObject().getValue();
			qbItem = QBUtilities.convertToQBItem(item);
			return qbItem;
		}
		return null;
	}

	@Override
	public QBItem create(QBItem sparseItem) {
		// TODO Auto-generated method stub
		
	requireAuthorization();
		
		QBItem qbItem = null;
		//return restTemplate.postForObject("{baseURL}/v3/company/{companyId}/customer?operation=update", customer, IntuitResponse.class, baseUrl, companyId);
		IntuitResponse response = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/item", sparseItem, IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			Item item = (Item) response.getIntuitObject().getValue();
			qbItem = QBUtilities.convertToQBItem(item);
			return qbItem;
		}
		return null;
	}

	@Override
	public QBItem save(QBItem qbItem) {
		// TODO Auto-generated method stub
		requireAuthorization();
		if(qbItem.getId() != null){
			return update(qbItem);
		}
		else {
			return create(qbItem);
		}
	}

	@Override
	public boolean delete(QBItem qbItem) {
		requireAuthorization();
		Item removedItem = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/item?operation=delete", qbItem, Item.class, baseUrl, companyId);
		return (removedItem == null);
	}
	
	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("intuit");
		}

}
}
