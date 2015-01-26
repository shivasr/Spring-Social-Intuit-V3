package com.app.inuit.api;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.app.intuit.api.IntuitProperties;
import com.app.intuit.api.impl.IntuitTemplate;
import com.app.intuit.domain.QbCustomer;
import com.app.intuit.util.QBUtilities;

/**
 * @author shiva
 *
 */
public class CustomerTemplateTest {
	
	IntuitTemplate intuit;

	public CustomerTemplateTest() {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		intuit = QBUtilities.buildIntuit();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.app.intuit.api.impl.CustomerTemplate#getCustomers()}.
	 */
	@Test
	public final void testGetCustomers() {
		
		System.out.println("Starting: ");
		
		QbCustomer qbCustomer = intuit.customerOperations().getCustomer(1L);
		
		System.out.println("qbCustomer: " + qbCustomer.getGivenName());
		
		qbCustomer.setGivenName("Shiva");
		
		qbCustomer = intuit.customerOperations().update(qbCustomer);
		
		System.out.println("Customer changed: " + qbCustomer.getGivenName());
		
		
	}

	/**
	 * Test method for {@link com.app.intuit.api.impl.CustomerTemplate#requireAuthorization()}.
	 */
	@Test
	public final void testRequireAuthorization() {
		Assert.assertTrue(true);
	}
	
	public static void main(String[] args){
		CustomerTemplateTest test = new CustomerTemplateTest();
		
		try {
			test.setUp();
			test.testGetCustomers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
