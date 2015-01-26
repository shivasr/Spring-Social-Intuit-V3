package com.app.intuit.api.test;

import org.junit.Assert;

import com.app.intuit.api.IntuitProperties;
import com.app.intuit.api.impl.IntuitTemplate;
import com.app.intuit.domain.QBInvoice;

public class InvoiceTemplateTest {
	
	IntuitTemplate intuit;

	public InvoiceTemplateTest() {
		
	}
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
				return "ZUtaXR1ircDRSvOEdRpAqLmakcfQKX7Gy5DBGw20";
			}
			
			@Override
			public String getAccessToken() {
				return "qyprd7cHHUByyxm2w7S2UDlIp4PVKEECPfHgeh9glC5DECGm";
			}

			@Override
			public String getBaseUrl() {
				return "https://sandbox-quickbooks.api.intuit.com";
			}
		};
		
		intuit = new IntuitTemplate(properties);
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
