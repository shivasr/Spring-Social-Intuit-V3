package com.app.intuit.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

import com.app.intuit.api.VendorOperations;
import com.app.intuit.domain.QBVendor;
import com.app.intuit.util.QBUtilities;
import com.intuit.ipp.data.IntuitEntity;
import com.intuit.ipp.data.IntuitResponse;
import com.intuit.ipp.data.QueryResponse;
import com.intuit.ipp.data.Vendor;

public class VendorTemplate implements VendorOperations {
	
	public static final Logger LOG = Logger.getLogger(VendorTemplate.class);
	private final boolean isAuthorized;
	private final RestTemplate restTemplate;
	private final String companyId;
	private final String baseUrl;

	public VendorTemplate(boolean isAuthorized, RestTemplate restTemplate, String companyId, String baseUrl) {
		super();
		this.isAuthorized = isAuthorized;
		this.restTemplate = restTemplate;
		this.companyId = companyId;
		this.baseUrl = baseUrl;
	}	
	

	@Override
	public QBVendor getVendor(String vendorId) {
		// TODO Auto-generated method stub
		
		requireAuthorization();
		QBVendor qbVendor = null;
		IntuitResponse response = restTemplate.getForObject("{baseURL}/v3/company/{companyId}/vendor/{vendorID}", IntuitResponse.class, baseUrl, companyId, vendorId);
		if(response != null){
			Vendor vendor = null;
			vendor = (Vendor) response.getIntuitObject().getValue();
			qbVendor = QBUtilities.convertToQBVendor(vendor);
			return qbVendor;
		}
		return null;
	}




	@Override
	public List<QBVendor> getVendors() {
		// TODO Auto-generated method stub
		
		requireAuthorization();		
		IntuitResponse response = restTemplate.getForObject("{baseURL}/v3/company/{companyId}/query?query=select * from vendor", IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			QueryResponse queryResponse = response.getQueryResponse();
			List<JAXBElement<? extends IntuitEntity>> intuitObjects = queryResponse.getIntuitObject();
			
			List<QBVendor> vendors = new ArrayList<QBVendor>(intuitObjects.size());
			for(JAXBElement<? extends IntuitEntity> element: intuitObjects){
				Vendor vendor = (Vendor) element.getValue();
				QBVendor qbVendor = QBUtilities.convertToQBVendor(vendor);
				vendors.add(qbVendor);
			}
			return vendors;
		}
		return null;
	}

	@Override
	public QBVendor update(QBVendor sparseVendor) {
		// TODO Auto-generated method stub
		requireAuthorization();
		
		QBVendor qbVendor = null;
		//return restTemplate.postForObject("{baseURL}/v3/company/{companyId}/customer?operation=update", customer, IntuitResponse.class, baseUrl, companyId);
		IntuitResponse response = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/vendor?operation=update", sparseVendor, IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			Vendor vendor = (Vendor) response.getIntuitObject().getValue();
			qbVendor = QBUtilities.convertToQBVendor(vendor);
			return qbVendor;
		}
		return null;
	}

	@Override
	public QBVendor create(QBVendor sparseVendor) {
		// TODO Auto-generated method stub
		
	requireAuthorization();
		
		QBVendor qbVendor = null;
		//return restTemplate.postForObject("{baseURL}/v3/company/{companyId}/customer?operation=update", customer, IntuitResponse.class, baseUrl, companyId);
		IntuitResponse response = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/vendor", sparseVendor, IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			Vendor vendor = (Vendor) response.getIntuitObject().getValue();
			qbVendor = QBUtilities.convertToQBVendor(vendor);
			return qbVendor;
		}
		return null;
	}

	@Override
	public QBVendor save(QBVendor qbVendor) {
		// TODO Auto-generated method stub
		requireAuthorization();
		if(qbVendor.getId() != null){
			return update(qbVendor);
		}
		else {
			return create(qbVendor);
		}
	}

	@Override
	public Boolean delete(QBVendor qbVendor) {
		requireAuthorization();
		Vendor removedVendor = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/vendor?operation=delete", qbVendor, Vendor.class, baseUrl, companyId);
		return (removedVendor == null);
	}
	
	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("intuit");
		}
		// TODO Auto-generated method stub
		
	}

}
