package com.app.intuit.api.impl;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

import com.app.intuit.api.APIConstants;
import com.app.intuit.api.CompanyMetaDataOperations;
import com.intuit.ipp.data.CompanyInfo;

public class CompanyMetaDataTemplate implements CompanyMetaDataOperations {
	
	private static final String url = APIConstants.baseUrl + "/v3/company/{companyID}/companyinfo/{companyID}";
	
	private final boolean isAuthorized;
	private final RestTemplate restTemplate;
	
	private final String companyId;
	
	public CompanyMetaDataTemplate(boolean isAuthorized, RestTemplate restTemplate, String companyId) {
		super();
		this.isAuthorized = isAuthorized;
		this.restTemplate = restTemplate;
		this.companyId = companyId;
	}

	public CompanyInfo getCompanyMetaData() {
		requireAuthorization();
		CompanyInfo restResponse = restTemplate.getForObject(url, CompanyInfo.class, companyId, companyId);
		if(restResponse != null){
			return restResponse;
		}
		return null;
	}
	
	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("intuit");
		}
	}

}
