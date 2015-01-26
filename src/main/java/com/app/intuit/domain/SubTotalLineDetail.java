/**
 * 
 */
package com.app.intuit.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author shiva
 *
 */
@XmlRootElement(name="SubTotalLineDetail")
@XmlType(propOrder={"serviceDate", "itemRef"})
public class SubTotalLineDetail {
	
	private Date serviceDate;
	
	private ItemRef itemRef;

	/**
	 * @return the serviceDate
	 */
	@XmlElement(name = "ServiceDate")
	public Date getServiceDate() {
		return serviceDate;
	}

	/**
	 * @param serviceDate the serviceDate to set
	 */
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	/**
	 * @return the itemRef
	 */
	@XmlElement(name = "ItemRef")
	public ItemRef getItemRef() {
		return itemRef;
	}

	/**
	 * @param itemRef the itemRef to set
	 */
	public void setItemRef(ItemRef itemRef) {
		this.itemRef = itemRef;
	}

}
