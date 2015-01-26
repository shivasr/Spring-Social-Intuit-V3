package com.app.intuit.api.test;


import org.junit.Assert;

import com.app.intuit.api.IntuitProperties;
import com.app.intuit.api.impl.IntuitTemplate;
import com.app.intuit.domain.QbCustomer;

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
	public void setUp() throws Exception {
		IntuitProperties properties = new IntuitProperties() {
			
			@Override
			public String getConsumerSecret() {
				return "Xc1KwMyDCd7HIGPNm6YZKcYENIBx5dyuH301fcMw";
			}
			
			@Override
			public String getConsumerKey() {
				return "qyprd1vHWzip1M4AaWYDUI058XlO8F";
			}
			
			@Override
			public String getCompanyId() {
				return "1315187735";
			}
			
			@Override
			public String getAccessTokenSecret() {
				return "o7AKwbZupJk1SqYvbMdkaTHjwohIvDxmrhOzazaE";
			}
			
			@Override
			public String getAccessToken() {
				return "qyprdx7C2oRnVNHT3BHpRd2127ytk5sUFnKxJVs6oT7mzHXW";
			}

			@Override
			public String getBaseUrl() {
				return "https://sandbox-quickbooks.api.intuit.com";
			}
		};
		
		intuit = new IntuitTemplate(properties);
	}


	/**
	 * Test method for {@link com.app.intuit.api.impl.CustomerTemplate#getCustomers()}.
	 */
	public final void testGetCustomers() {
		
		System.out.println("Starting: ");
		
		QbCustomer qbCustomer = intuit.customerOperations().getCustomer(1L);
		
		System.out.println("Customer: " + qbCustomer.getGivenName());
		
		qbCustomer.setGivenName("Shiva1");
		
		qbCustomer = intuit.customerOperations().update(qbCustomer);
		
		System.out.println("Customer changed...: " + qbCustomer.getGivenName());
		
		
	}

	/**
	 * Test method for {@link com.app.intuit.api.impl.CustomerTemplate#requireAuthorization()}.
	 */
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
