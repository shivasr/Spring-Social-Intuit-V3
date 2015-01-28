package com.app.intuit.api.test;

import java.util.List;

import org.junit.Assert;

import com.app.intuit.api.impl.IntuitTemplate;
import com.app.intuit.domain.QBItem;
import com.app.intuit.util.QBUtilities;

public class ItemTemplateTest {
	IntuitTemplate intuit;
	public void setUp() throws Exception {
		intuit = QBUtilities.buildIntuit();
	}
	public final void testGetItems(){
		System.out.println("Starting: ");
	
	List<QBItem> items =intuit.itemOperations().getItems();
	
	for(QBItem item : items){
		System.out.println("Item: " + item.getName());
	}
	
	System.out.println("***************Inventory Items****************** ");
	List<QBItem> inventoryItems = intuit.itemOperations().getIventoryItems();
	
	for(QBItem item : inventoryItems){
		System.out.println("Inventory Item: " + item.getName());
	}
	System.out.println("***************Service Items****************** ");
	List<QBItem> serviceItems = intuit.itemOperations().getServiceItems();
	for(QBItem item : serviceItems){
		System.out.println("Service Item: " + item.getName());
	}


	}
	public final void testRequireAuthorization() {
		Assert.assertTrue(true);
	}
	public static void main(String[] args){
		ItemTemplateTest itemTemplateTest = new ItemTemplateTest();
		try {
			itemTemplateTest.setUp();
			itemTemplateTest.testGetItems();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
