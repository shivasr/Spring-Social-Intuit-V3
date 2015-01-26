package com.app.intuit.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.web.client.RestTemplate;

import com.app.intuit.api.AccountOperations;
import com.intuit.ipp.data.Account;
import com.intuit.ipp.data.AccountSubTypeEnum;


public class AccountTemplate implements AccountOperations {
	
	private final boolean isAuthorized;
	private final RestTemplate restTemplate;
	private final String companyId;
	private final String baseUrl;
	private Integer pageSize = 50;

	public AccountTemplate(boolean isAuthorized, RestTemplate restTemplate, String companyId, String baseUrl) {
		super();
		this.isAuthorized = isAuthorized;
		this.restTemplate = restTemplate;
		this.companyId = companyId;
		this.baseUrl = baseUrl;
	}

	public Account getAccount(Long accountId) {
		requireAuthorization();
		return restTemplate.getForObject("{baseURL}/resource/account/v2/{companyId}/{accountID}", Account.class, baseUrl, companyId, accountId);
	}
	
	public List<Account> getBankAccounts() {
		List<Account> bankAccounts = new ArrayList<Account>();
		List<Account> accounts = getAccounts();
		for(Account account : accounts){
			String subtype = account.getAccountSubType();
			if(AccountSubTypeEnum.CASH_ON_HAND.value().equals(subtype)
				|| AccountSubTypeEnum.CHECKING.value().equals(subtype)
				|| AccountSubTypeEnum.MONEY_MARKET.value().equals(subtype)
				|| AccountSubTypeEnum.RENTS_HELD_IN_TRUST.value().equals(subtype)
				|| AccountSubTypeEnum.SAVINGS.value().equals(subtype)
				|| AccountSubTypeEnum.TRUST_ACCOUNTS.value().equals(subtype)){
				bankAccounts.add(account);
			}
		}
		return bankAccounts;
	}

	public List<Account> getAccounts() {
		requireAuthorization();
		List<Account> accounts = new ArrayList<Account>();
		/*boolean hasMore = true;
		Integer pageNum = 1;
		while(hasMore){
			MultiValueMap<String, String> criteria = new LinkedMultiValueMap<String, String>();
			criteria.add("ResultsPerPage", pageSize.toString());
			criteria.add("PageNum", pageNum.toString());
			SearchResults response = restTemplate.postForObject("{baseURL}/resource/accounts/v2/{companyId}", criteria, SearchResults.class, baseUrl, companyId);
			if(response == null) break;
			List<Account> returnedAccounts = ((Accounts)response.getCdmCollections()).getAccounts();
			if(pageSize != returnedAccounts.size()) hasMore = false;
			accounts.addAll(returnedAccounts);
			pageNum = response.getCurrentPage() + 1;
		} */
		return accounts;
	}

	public Account update(Account account) {
		requireAuthorization();
		return restTemplate.postForObject("{baseURL}/resource/account/v2/{companyId}/{accountID}", account, Account.class, baseUrl, companyId, account.getId());
	}

	public Account create(Account account) {
		requireAuthorization();
		return restTemplate.postForObject("{baseURL}/resource/account/v2/{companyId}", account, Account.class, baseUrl, companyId);
	}

	public Account save(Account account) {
		requireAuthorization();
		if(account.getId() != null && account.getId() != null){
			return update(account);
		}
		else {
			return create(account);
		}
	}
	
	public boolean delete(Account account) {
		requireAuthorization();
		Account response = restTemplate.postForObject("{baseURL}/resource/account/v2/{companyId}/{accountID}?methodx=delete", buildDelete(account), Account.class, baseUrl, companyId, account.getId());
		return (response.getId() == null);
	}
	
	private Account buildDelete(Account account){
		Account delete = new Account();
		delete.setSyncToken(account.getSyncToken());
		delete.setId(account.getId());
		
		return delete;
	}
	
	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("intuit");
		}
	}

}
