package com.app.intuit.api.test;

import org.junit.Assert;

import com.app.intuit.api.impl.IntuitTemplate;
import com.app.intuit.domain.QBItem;
import com.app.intuit.util.QBUtilities;

public class ItemTemplateTest {
	IntuitTemplate intuit;
	public void setUp() throws Exception {
		intuit = QBUtilities.buildIntuit();
	}
	public final void testGetItem(){
		System.out.println("Starting: ");
		QBItem qbItem= intuit.itemOperations().getItem("11");
		System.out.println("Item name : "+qbItem.getName());
	qbItem.setName("PetrolPump");
	qbItem=intuit.itemOperations().update(qbItem);
		//System.out.println("vendor name : "+qbItem.getName());
		//System.out.println("Invoice Due date : "+qbInvoice.getDueDate());
	
	}
	public final void testRequireAuthorization() {
		Assert.assertTrue(true);
	}
	public static void main(String[] args){
		ItemTemplateTest itemTemplateTest = new ItemTemplateTest();
		try {
			itemTemplateTest.setUp();
			itemTemplateTest.testGetItem();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
