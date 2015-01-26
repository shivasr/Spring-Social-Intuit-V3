package com.app.intuit.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

import com.app.intuit.api.InvoiceOperations;
import com.app.intuit.domain.QBInvoice;
import com.app.intuit.util.QBToFMMapperUtilities;
import com.intuit.ipp.data.IntuitEntity;
import com.intuit.ipp.data.IntuitResponse;
import com.intuit.ipp.data.Invoice;

public class InvoiceTemplate implements InvoiceOperations {
	
	private final boolean isAuthorized;
	private final RestTemplate restTemplate;
	private final String companyId;
	private final String baseUrl;

	public InvoiceTemplate(boolean isAuthorized, RestTemplate restTemplate, String companyId, String baseUrl) {
		super();
		this.isAuthorized = isAuthorized;
		this.restTemplate = restTemplate;
		this.companyId = companyId;
		this.baseUrl = baseUrl;
	}

	public QBInvoice getQBInvoice(Long invoiceId) {
		requireAuthorization();
		
		QBInvoice qbInvoice = null;
		
		IntuitResponse response = restTemplate.getForObject("{baseURL}/v3/company/{companyId}/invoice/{customerID}", IntuitResponse.class, baseUrl, companyId, invoiceId);
		if(response != null){
			Invoice invoice = null;
			invoice = (Invoice) response.getIntuitObject().getValue();
			qbInvoice = QBToFMMapperUtilities.convertToQBInvoice(invoice);
			return qbInvoice;
		}
		return null;
	}

	public List<QBInvoice> getQBInvoices() {
		
		
		requireAuthorization();		
		IntuitResponse response = restTemplate.getForObject("{baseURL}/v3/company/{companyId}/query?query=select * from Invoice ", IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			List<JAXBElement<? extends IntuitEntity>> intuitObjects = response.getQueryResponse().getIntuitObject();
			
			List<QBInvoice> invoices = new ArrayList<QBInvoice>(intuitObjects.size());
			for(JAXBElement<? extends IntuitEntity> element: intuitObjects){
				Invoice invoice = (Invoice) element.getValue();
				QBInvoice qbInvoice = QBToFMMapperUtilities.convertToQBInvoice(invoice);
				invoices.add(qbInvoice);
			}
			return invoices;
		}
		return null;
	}

	public QBInvoice update(QBInvoice qbInvoice1) {
		requireAuthorization();
		
		QBInvoice qbInvoice = null;
		IntuitResponse response = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/invoice?operation=update", qbInvoice1, IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			Invoice invoice = (Invoice) response.getIntuitObject().getValue();
			qbInvoice = QBToFMMapperUtilities.convertToQBInvoice(invoice);
			return qbInvoice;
		}
		return null;
	}

	public QBInvoice create(QBInvoice qbInvoice1) {
		requireAuthorization();
		QBInvoice qbInvoice = null;
		IntuitResponse response = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/invoice", qbInvoice1, IntuitResponse.class, baseUrl, companyId);
		if(response != null){
			Invoice invoice = (Invoice) response.getIntuitObject().getValue();
			qbInvoice = QBToFMMapperUtilities.convertToQBInvoice(invoice);
			return qbInvoice;
		}
		return null;
	}

	public QBInvoice save(QBInvoice qbInvoice) {
		requireAuthorization();
		if(qbInvoice.getId() != null){
			return update(qbInvoice);
		}
		else {
			return create(qbInvoice);
		}
	}
	
	public boolean delete(QBInvoice invoice) {
		requireAuthorization();
		Invoice removedInvoice = restTemplate.postForObject("{baseURL}/v3/company/{companyId}/invoice?operation=delete", invoice, Invoice.class, baseUrl, companyId);
		return (removedInvoice == null);
	}
	
	
	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("intuit");
		}
	}

	@Override
	public List<QBInvoice> getQBInvoices(QBInvoice invoice) {
		throw new UnsupportedOperationException("Get Invoices by Customers not yet supported");
	}

}