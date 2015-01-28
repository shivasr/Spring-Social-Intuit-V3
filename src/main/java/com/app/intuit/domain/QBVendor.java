package com.app.intuit.domain;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement(name = "Vendor")
@XmlType(propOrder={"xmlns","domain","sparse","id","syncToken","givenName","familyName","companyName","displayName","printOnCheckName","active","primaryPhone","webAddress","primaryEmailAddr","billAddr","balance","acctNum"})
public class QBVendor {
	
	final private String xmlns = "http://schema.intuit.com/finance/v3";
	private String id;
	private String syncToken;
	private String givenName;
	private String familyName;
	private String companyName;
	private String displayName;
	private String printOnCheckName;
	private boolean active;
	private TelephoneNumber primaryPhone=null;
	private WebAddress webAddress = null;
	private EmailAddress primaryEmailAddr =  null;
	private PhysicalAddress billAddr = null;
	private BigDecimal balance;
	private String acctNum;
	private String domain = "QBO";
	private boolean sparse = false;
	
	
	@XmlAttribute(name = "xmlns")
	public String getXmlns() {
		return xmlns;
	}

	@XmlAttribute(name = "domain")
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the sparse
	 */
	@XmlAttribute(name = "sparse")
	public boolean getSparse() {
		return sparse;
	}

	/**
	 * @param sparse the sparse to set
	 */
	public void setSparse(boolean sparse) {
		this.sparse = sparse;
	}

	
	@XmlElement(name = "Id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement(name = "SyncToken")
	public String getSyncToken() {
		return syncToken;
	}
	public void setSyncToken(String syncToken) {
		this.syncToken = syncToken;
	}
	
	@XmlElement(name = "GivenName")
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	@XmlElement(name = "FamilyName")
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	@XmlElement(name = "CompanyName")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@XmlElement(name = "DisplaName")
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@XmlElement(name = "PrintOnCheckName")
	public String getPrintOnCheckName() {
		return printOnCheckName;
	}
	public void setPrintOnCheckName(String printOnCheckName) {
		this.printOnCheckName = printOnCheckName;
	}
	
	@XmlElement(name = "Active")
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@XmlElement(name = "PrimaryPhone")
	public TelephoneNumber getPrimaryPhone() {
		return primaryPhone;
	}
	public void setPrimaryPhone(TelephoneNumber primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	
	@XmlElement(name = "WebAddress")
	public WebAddress getWebAddress() {
		return webAddress;
	}
	public void setWebAddress(WebAddress webAddress) {
		this.webAddress = webAddress;
	}
	
	@XmlElement(name = "PrimaryEmailAddr")
	public EmailAddress getPrimaryEmailAddr() {
		return primaryEmailAddr;
	}
	public void setPrimaryEmailAddr(EmailAddress primaryEmailAddr) {
		this.primaryEmailAddr = primaryEmailAddr;
	}
	
	@XmlElement(name = "BillAddr")
	public PhysicalAddress getBillAddr() {
		return billAddr;
	}
	public void setBillAddr(PhysicalAddress billAddr) {
		this.billAddr = billAddr;
	}
	
	@XmlElement(name="Balance" )
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	@XmlElement(name = "AcctNum")
	public String getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}
	
	
}
	