package com.app.intuit.api;

import java.util.List;

import com.app.intuit.domain.QBVendor;

public interface VendorOperations {
	
	public QBVendor getVendor(String id);
	public List<QBVendor> getVendors();
	public QBVendor update(QBVendor qbVendor);
	public QBVendor create(QBVendor qbVendor);
	public QBVendor save(QBVendor qbVendor);
	public Boolean delete(QBVendor qbVendor);
}
