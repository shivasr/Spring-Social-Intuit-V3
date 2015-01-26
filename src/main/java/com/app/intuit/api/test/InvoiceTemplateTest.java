package com.app.intuit.api.test;

import org.junit.Assert;

import com.app.intuit.api.impl.IntuitTemplate;
import com.app.intuit.domain.QBInvoice;
import com.app.intuit.util.QBUtilities;

public class InvoiceTemplateTest {
	
	IntuitTemplate intuit;

	public InvoiceTemplateTest() {
		
	}
	public void setUp() throws Exception {
		intuit = QBUtilities.buildIntuit();
	}
	
	public final void testGetInvoice(){
		System.out.println("Starting: ");
		QBInvoice qbInvoice= intuit.invoiceOperations().getQBInvoice(130L);
		System.out.println("Invoice total amount : "+qbInvoice.getTotalAmt());
		System.out.println("Invoice Due date : "+qbInvoice.getDueDate());
	}
	public final void testRequireAuthorization() {
		Assert.assertTrue(true);
	}
	public static void main(String[] args){
		InvoiceTemplateTest test = new InvoiceTemplateTest();
		
		try {
			test.setUp();
			test.testGetInvoice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
