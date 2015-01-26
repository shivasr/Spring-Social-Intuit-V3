/**
 * 
 */
package com.app.intuit.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * @author shiva
 *
 */
/*
 <Customer xmlns="http://schema.intuit.com/finance/v3" domain="QBO" sparse="false">
  <GivenName>James</GivenName>
  <MiddleName>B</MiddleName>
  <FamilyName>Drew</FamilyName>
  <CompanyName>Drew Furniture</CompanyName>
  <Active>true</Active>
  <PrimaryPhone>
    <FreeFormNumber>(555) 555-5555</FreeFormNumber>
  </PrimaryPhone>
  <AlternatePhone>
    <FreeFormNumber>(555) 555-8888</FreeFormNumber>
  </AlternatePhone>
  <Mobile>
    <FreeFormNumber>555-5555-6666</FreeFormNumber>
  </Mobile>
  <Fax>
    <FreeFormNumber>(555) 555-7777</FreeFormNumber>
  </Fax>
  <PrimaryEmailAddr>
    <Address>shivasr@gmail.com</Address>
  </PrimaryEmailAddr>
  <BillAddr>
    <City>Mountain View</City>
    <Country>USA</Country>
    <CountrySubDivisionCode>CA</CountrySubDivisionCode>
    <PostalCode>94042</PostalCode>
    <Lat>37.3931794</Lat>
    <Long>-122.0778761</Long>
  </BillAddr>
  <ShipAddr>
    <City>Mountain View</City>
    <Country>USA</Country>
    <CountrySubDivisionCode>CA</CountrySubDivisionCode>
    <PostalCode>94042</PostalCode>
    <Lat>37.3931794</Lat>
    <Long>-122.0778761</Long>
  </ShipAddr>
  <Notes>Important Customer</Notes>
</Customer>
 */

@XmlRootElement(name="Customer")  
@XmlType(propOrder={"id", "syncToken", "givenName", "middleName", "familyName",
		"companyName", "billAddr", "shipAddr",  
		"primaryPhone", "alternatePhone","mobile", "fax",
		"primaryEmailAddr", "notes", "active" })
public class QbCustomer   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String givenName;
	
	private String middleName;
	
	private String familyName;
	
	private String companyName;
	
	boolean active  = true;
	
	private PhysicalAddress billAddr;
	
	private PhysicalAddress shipAddr;
	
	private TelephoneNumber primaryPhone;
	
	private TelephoneNumber alternatePhone;
	
	private TelephoneNumber mobile;
	
	private TelephoneNumber fax;
	
	private EmailAddress  primaryEmailAddr;
	
	private String notes;
	
	final String xlmns = "http://schema.intuit.com/finance/v3";
	String id;
	int syncToken = -1;
	String domain = "QBO";
	String sparse;

	/**
	 * @return the xlmns
	 */
	@XmlAttribute(name = "xmlns")
	public String getXlmns() {
		return xlmns;
	}

	/**
	 * @return the domain
	 */
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
	public String getSparse() {
		return sparse;
	}

	/**
	 * @param sparse the sparse to set
	 */
	public void setSparse(String sparse) {
		this.sparse = sparse;
	}

	/**
	 * @return the id
	 */
	@XmlElement(name = "Id")
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the syncToken
	 */
	@XmlElement(name = "SyncToken")
	public String getSyncToken() {
		return Integer.toString(syncToken);
	}

	/**
	 * @param syncToken the syncToken to set
	 */
	public void setSyncToken(int syncToken) {
		this.syncToken = syncToken;
	}
	
	
	@XmlElement(name="GivenName")  
	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	@XmlElement(name="MiddleName")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@XmlElement(name="MiddleName")
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	@XmlElement(name="CompanyName")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@XmlElement(name="Active")
	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	/**
	 * @return the billAddr
	 */
	@XmlElement(name="BillAddr")
	public PhysicalAddress getBillAddr() {
		return billAddr;
	}

	/**
	 * @param billAddr the billAddr to set
	 */
	public void setBillAddr(PhysicalAddress billAddr) {
		this.billAddr = billAddr;
	}

	/**
	 * @return the shipAddr
	 */
	@XmlElement(name="ShipAddr")
	public PhysicalAddress getShipAddr() {
		return shipAddr;
	}

	/**
	 * @param shipAddr the shipAddr to set
	 */
	public void setShipAddr(PhysicalAddress shipAddr) {
		this.shipAddr = shipAddr;
	}

	/**
	 * @return the primaryPhone
	 */
	@XmlElement(name="PrimaryPhone")
	public TelephoneNumber getPrimaryPhone() {
		return primaryPhone;
	}

	/**
	 * @param primaryPhone the primaryPhone to set
	 */
	public void setPrimaryPhone(TelephoneNumber primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	/**
	 * @return the alternatePhone
	 */
	@XmlElement(name="AlternatePhone")
	public TelephoneNumber getAlternatePhone() {
		return alternatePhone;
	}

	/**
	 * @param alternatePhone the alternatePhone to set
	 */
	public void setAlternatePhone(TelephoneNumber alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	/**
	 * @return the mobile
	 */
	@XmlElement(name="Mobile")
	public TelephoneNumber getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(TelephoneNumber mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the fax
	 */
	@XmlElement(name="Fax")
	public TelephoneNumber getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(TelephoneNumber fax) {
		this.fax = fax;
	}

	/**
	 * @return the primaryEmailAddr
	 */
	@XmlElement(name="PrimaryEmailAddr")
	public EmailAddress getPrimaryEmailAddr() {
		return primaryEmailAddr;
	}

	/**
	 * @param primaryEmailAddr the primaryEmailAddr to set
	 */
	public void setPrimaryEmailAddr(EmailAddress primaryEmailAddr) {
		this.primaryEmailAddr = primaryEmailAddr;
	}

	/**
	 * @return the notes
	 */
	@XmlElement(name="Notes")
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
