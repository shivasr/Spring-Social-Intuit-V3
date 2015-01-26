/**
 * 
 */
package com.app.intuit.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author shiva
 *
 */
@XmlRootElement(name="TelephoneNumber")  
@XmlType(propOrder={"freeFormNumber"})

public class TelephoneNumber {
	
	private String freeFormNumber;

	public TelephoneNumber() {
		
	}
	
	public TelephoneNumber(String freeFormNumber) {
		this.freeFormNumber = freeFormNumber;
	}
	public TelephoneNumber(com.intuit.ipp.data.TelephoneNumber alternatePhone) {
		if(alternatePhone != null)
			this.freeFormNumber = alternatePhone.getFreeFormNumber();
	}

	/**
	 * @return the freeFormNumber
	 */
	public String getFreeFormNumber() {
		return freeFormNumber;
	}

	/**
	 * @param freeFormNumber the freeFormNumber to set
	 */
	@XmlElement(name="FreeFormNumber")
	public void setFreeFormNumber(String freeFormNumber) {
		this.freeFormNumber = freeFormNumber;
	}
	
	

}
