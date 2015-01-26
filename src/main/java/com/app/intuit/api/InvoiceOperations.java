package com.app.intuit.api;

import java.util.List;

import com.app.intuit.domain.QBInvoice;


public interface InvoiceOperations {
	
	public QBInvoice getQBInvoice(Long id);
	public List<QBInvoice> getQBInvoices();
	public List<QBInvoice> getQBInvoices(QBInvoice qbInvoice);
	public QBInvoice update(QBInvoice qbInvoice);
	public QBInvoice create(QBInvoice qbInvoice);
	/**
	 * Depending on if there is a idType create or update is called. Essentially
	 * a wrapper around create and update to hide the logic.
	 * @param invoice Invoice item to be persisted to Intuit.
	 * @return Saved Intuit Item containing the idType of saved Intuit item.
	 */
	public QBInvoice save(QBInvoice qbInvoice);
	public boolean delete(QBInvoice qbInvoice);

}
