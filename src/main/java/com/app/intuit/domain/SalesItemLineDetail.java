package com.app.intuit.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="SalesItemLineDetail")
@XmlType(propOrder={"itemRef", "unitPrice", "qty", "taxCodeRef"})
public class SalesItemLineDetail {
	private ItemRef itemRef = null;
	
	private String taxCodeRef = null;
	
	private double qty;
	
	private double unitPrice;
	
	@XmlElement(name="ItemRef")
	public ItemRef getItemRef() {
		return itemRef;
	}
	public void setItemRef(ItemRef itemRef) {
		this.itemRef = itemRef;
	}
	
	@XmlElement(name="TaxCodeRef")
	public String getTaxCodeRef() {
		return taxCodeRef;
	}
	public void setTaxCodeRef(String taxCodeRef) {
		this.taxCodeRef = taxCodeRef;
	}
	/**
	 * @return the qty
	 */
	@XmlElement(name="Qty")
	public double getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(double qty) {
		this.qty = qty;
	}
	/**
	 * @return the unitPrice
	 */
	@XmlElement(name="UnitPrice")
	public double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
