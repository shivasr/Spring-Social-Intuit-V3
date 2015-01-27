package com.app.intuit.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Item")
@XmlType(propOrder={"Domain","Sparse","Id","SyncToken","Name","Description","Active","Taxable","UnitPrice","TrackQtyOnHand","InvStartDate","QtyOnHand"})
public class QBItem {
	public enum ItemTypeEnum{INVENTORY,SERVICE};
	ItemTypeEnum itemType;
	private String domain = "QBO";
	private boolean sparse = false;
	
	private String id;
	private String syncToken;
	private String name;
	private String decription;
	private boolean active;
	private boolean taxable;
	private BigDecimal unitPrice;
	private boolean trackQtyOnHand;
	private Date invStartDate;
	private BigDecimal qtyOnHand;
	
	
	public ItemTypeEnum getItemType() {
		return itemType;
	}
	public void setItemType(ItemTypeEnum itemType) {
		this.itemType = itemType;
	}
	
	@XmlAttribute(name = "Doamin")
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@XmlAttribute(name = "Sparse")
	public boolean isSparse() {
		return sparse;
	}
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
	
	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "Description")
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	
	@XmlElement(name = "Active")
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@XmlElement(name = "Taxable")
	public boolean isTaxable() {
		return taxable;
	}
	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}
	
	@XmlElement(name = "UnitPrice")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@XmlElement(name = "TrackQtyHand")
	public boolean isTrackQtyOnHand() {
		return trackQtyOnHand;
	}
	public void setTrackQtyOnHand(boolean trackQtyOnHand) {
		this.trackQtyOnHand = trackQtyOnHand;
	}
	
	@XmlElement(name = "InvStartDate")
	public Date getInvStartDate() {
		return invStartDate;
	}
	public void setInvStartDate(Date invStartDate) {
		this.invStartDate = invStartDate;
	}
	
	@XmlElement(name = "QtyOnHand")
	public BigDecimal getQtyOnHand() {
		return qtyOnHand;
	}
	public void setQtyOnHand(BigDecimal qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}
	
	
}
