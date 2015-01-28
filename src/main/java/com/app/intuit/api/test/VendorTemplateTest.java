package com.app.intuit.api.test;

import org.junit.Assert;

import com.app.intuit.api.impl.IntuitTemplate;
import com.app.intuit.domain.QBVendor;
import com.app.intuit.util.QBUtilities;

public class VendorTemplateTest {
	IntuitTemplate intuit;
	public void setUp() throws Exception {
		intuit = QBUtilities.buildIntuit();
	}
	
	public final void testGetVendor(){
		System.out.println("Starting: ");
		QBVendor qbVendor= intuit.vendorOperations().getVendor("30");
		System.out.println("vendor name : "+qbVendor.getFamilyName());
		qbVendor.setFamilyName("Umakant");
		qbVendor=intuit.vendorOperations().update(qbVendor);
		System.out.println("vendor name : "+qbVendor.getFamilyName());
		//System.out.println("Invoice Due date : "+qbInvoice.getDueDate());
	
	}
	
	public final void testRequireAuthorization() {
		Assert.assertTrue(true);
	}
	
	
	public static void main(String[] args){
		VendorTemplateTest vendorTemplateTest = new VendorTemplateTest();
		try {
			vendorTemplateTest.setUp();
			vendorTemplateTest.testGetVendor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
