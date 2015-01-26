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
@XmlRootElement(name="EmailAddress")  
@XmlType(propOrder={"address"})
public class EmailAddress {
	
	private String address;
	
	public EmailAddress() {
		
	}

	public EmailAddress(com.intuit.ipp.data.EmailAddress emailAddr) {
		if(emailAddr != null)
		this.address = emailAddr.getAddress();
	}

	public EmailAddress(String email) {
		this.address = email;
	}

	/**
	 * @return the address
	 */
	@XmlElement(name="Address")
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
