package com.app.intuit.api.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.app.intuit.api.AccountOperations;
import com.app.intuit.api.CompanyMetaDataOperations;
import com.app.intuit.api.CustomerOperations;
import com.app.intuit.api.Intuit;
import com.app.intuit.api.IntuitProperties;
import com.app.intuit.api.InvoiceOperations;
import com.app.intuit.api.ItemOperations;
import com.app.intuit.api.PaymentMethodOperations;
import com.app.intuit.api.PaymentOperations;
import com.app.intuit.api.UserOperations;
import com.app.intuit.api.VendorOperations;

public class IntuitTemplate extends AbstractOAuth1ApiBinding implements Intuit {
	
	public static final Logger LOG = Logger.getLogger(IntuitTemplate.class);
	
	private AccountOperations accountOperations;
	private CompanyMetaDataOperations companyMetaDataOperations;
	private CustomerOperations customerOperations;
	private UserOperations userOperations;
	private PaymentOperations paymentOperations;
	private PaymentMethodOperations paymentMethodOperations;
	private InvoiceOperations invoiceOperations;
	private ItemOperations itemOperations;
	private VendorOperations vendorOperations;
	
	private String companyId;
	private static final String BASE_URL = "https://qbo.sbfinance.intuit.com";	 
	
	IntuitProperties properties;

	public IntuitTemplate(IntuitProperties properties) {
		super(properties.getConsumerKey(), properties.getConsumerSecret(), properties.getAccessToken(), properties.getAccessTokenSecret());
		Assert.assertNotNull("Consumer Key is null.", properties.getConsumerKey());
		Assert.assertNotNull("Consumer Secret is null.", properties.getConsumerSecret());
		Assert.assertNotNull("Company Id is null.", properties.getCompanyId());
		Assert.assertNotNull("Access Token is null.", properties.getAccessToken());
		Assert.assertNotNull("Access Token Secret is null.", properties.getAccessTokenSecret());
		this.properties = properties;
	}
	
	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {		
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(getFormMessageConverter());
		converters.add(new StringHttpMessageConverter());
		converters.add(new Jaxb2RootElementHttpMessageConverter());
		return converters;
	}

	private void initSubApis() {
		if(!isInitialized()){
			String companyId = properties.getCompanyId();
			/*userOperations = new UserTemplate(isAuthorized(), getRestTemplate()); */
			String baseUrl = (properties.getBaseUrl() == null)? BASE_URL : properties.getBaseUrl();
			accountOperations = new AccountTemplate(isAuthorized(), getRestTemplate(), companyId, baseUrl);
			customerOperations = new CustomerTemplate(isAuthorized(), getRestTemplate(), companyId, baseUrl);
			invoiceOperations = new InvoiceTemplate(isAuthorized(), getRestTemplate(), companyId, baseUrl);
			vendorOperations = new VendorTemplate(isAuthorized(),getRestTemplate(), companyId, baseUrl);
			/*paymentOperations = new PaymentTemplate(isAuthorized(), getRestTemplate(), companyId, BASE_URL);
			paymentMethodOperations = new PaymentMethodTemplate(isAuthorized(), getRestTemplate(), companyId, BASE_URL);*/
			
			itemOperations = new ItemTemplate(isAuthorized(), getRestTemplate(), companyId, baseUrl); 
		}
	}

	public CompanyMetaDataOperations companyMetaDataOperations() {
		initSubApis();
		return companyMetaDataOperations;
	}

	public AccountOperations accountOperations() {
		initSubApis();
		return accountOperations;
	}

	public CustomerOperations customerOperations() {
		initSubApis();
		return customerOperations;
	}	

	public UserOperations userOperations() {
		initSubApis();
		return userOperations;
	}
	
	public PaymentOperations paymentOperations() {
		initSubApis();
		return paymentOperations;
	}

	public PaymentMethodOperations paymentMethodOperations() {
		initSubApis();
		return paymentMethodOperations;
	}
	
	public InvoiceOperations invoiceOperations(){
		initSubApis();
		return invoiceOperations;
	}
	
	public ItemOperations itemOperations() {
		initSubApis();
		return itemOperations;
	}
	
	public VendorOperations vendorOperations(){
		initSubApis();
		return vendorOperations;
	}
	protected void configureRestTemplate(RestTemplate restTemplate) {
		restTemplate.setErrorHandler(new ResponseErrorHandler() {
					
					@Override
					public boolean hasError(ClientHttpResponse response) throws IOException {
						if(HttpStatus.OK != response.getStatusCode())
							return true;
						
						return false;
					}
					
					@Override
					public void handleError(ClientHttpResponse response) throws IOException {
						HttpStatus statusCode = response.getStatusCode();
						String statusText = response.getStatusText();
						
						String encoding = null;
						encoding = encoding == null ? "UTF-8" : encoding;
						String body = IOUtils.toString(response.getBody(), encoding);
						LOG.error("response = " + body);
						
						throw new HttpClientErrorException(statusCode);
						
					}
				});
		
		ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {
			
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body,
					ClientHttpRequestExecution execution) throws IOException {
				
				String requestBody = IOUtils.toString(body);
				ClientHttpResponse response = execution.execute(request, body);
				return response;
				
			}
		};
		
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		
		interceptors.add(1, interceptor);
		restTemplate.setInterceptors(interceptors);
	}

	private boolean isInitialized(){
		if(BASE_URL != null && companyId != null){
			return true;
		}
		return false;
	}
}
