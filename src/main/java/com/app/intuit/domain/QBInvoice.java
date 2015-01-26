package com.app.intuit.domain;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * 
 * <Invoice domain="QBO" sparse="false">
    <Id>44</Id>
    <SyncToken>0</SyncToken>
    <DocNumber>1014</DocNumber>
    <TxnDate>2012-04-20</TxnDate>
    <CurrencyRef name="United States Dollar">USD</CurrencyRef>
    <Line>
      <Id>1</Id>
      <LineNum>1</LineNum>
      <Amount>15.00</Amount>
      <DetailType>SalesItemLineDetail</DetailType>
      <SalesItemLineDetail>
        <ItemRef name="Sales">1</ItemRef>
        <TaxCodeRef>NON</TaxCodeRef>
      </SalesItemLineDetail>
    </Line>
    <Line>
      <Amount>15.00</Amount>
      <DetailType>SubTotalLineDetail</DetailType>
      <SubTotalLineDetail/>
    </Line>
    <CustomerRef name="wEDvyvvklH gNnWGaB1fd">67</CustomerRef>
    <BillAddr>
      <Id>57</Id>
      <Line1>2500</Line1>
      <Line2>Garcia Avenue</Line2>
      <City>Mountain View</City>
      <Country>USA</Country>
      <PostalCode>94043</PostalCode>
    </BillAddr>
    <ShipAddr>
      <Id>58</Id>
      <Line1>6590</Line1>
      <Line2>Jefferson Street</Line2>
      <City>MenloPark</City>
      <Country>USA</Country>
      <PostalCode>94026</PostalCode>
    </ShipAddr>
    <DueDate>2012-05-20</DueDate>
    <TotalAmt>15.00</TotalAmt>
    <Balance>15.00</Balance>
  </Invoice>
 *
 */
@XmlRootElement(name="Invoice")
@XmlType(propOrder={"id", "xmlns", "syncToken", "docNumber", "txnDate","currencyRef", "lines","customerRef","billAddr","shipAddr","dueDate","totalAmt","balance"})
public class QBInvoice {
	final private String xmlns = "http://schema.intuit.com/finance/v3";
	
	private String domain = "QBO";
	private Boolean sparse = false;
	private Long id ;
	private int syncToken;
	private Long docNumber;
	private Date txnDate;
	private Date dueDate;
	private double totalAmt;
	private double balance;
	
	List<Line> lines;
	
	private PhysicalAddress billAddr = null;
	private PhysicalAddress shipAddr = null;
	private CurrencyRef currencyRef = null;
	private CustomerRef customerRef = null;
	
	@XmlAttribute(name = "domain")
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@XmlAttribute(name="sparse")
	public Boolean getSparse() {
		return sparse;
	}
	public void setSparse(Boolean sparse) {
		this.sparse = sparse;
	}
	
	@XmlElement(name="Id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement(name="SyncToken")
	public int getSyncToken() {
		return syncToken;
	}
	public void setSyncToken(int syncToken) {
		this.syncToken = syncToken;
	}
	
	@XmlElement(name="DocNumber")
	public Long getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(Long docNumber) {
		this.docNumber = docNumber;
	}
	
	@XmlElement(name="TxnDate")
	public Date getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}
	
	@XmlElement(name="DueDate")
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@XmlElement(name="TotalAmt")
	public double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}
	
	@XmlElement(name="Balance")
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	@XmlElement(name="CurrencyRef")
	public CurrencyRef getCurrencyRef() {
		return currencyRef;
	}
	public void setCurrencyRef(CurrencyRef currencyRef) {
		this.currencyRef = currencyRef;
	}
	
	@XmlElement(name="CustomerRef")
	public CustomerRef getCustomerRef() {
		return customerRef;
	}
	public void setCustomerRef(CustomerRef customerRef) {
		this.customerRef = customerRef;
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
	
	@XmlElement(name="Line")
	public List<Line> getLines() {
		return lines;
	}
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
	/**
	 * @return the xmlns
	 */
	@XmlAttribute
	public String getXmlns() {
		return xmlns;
	}
	
	
}
