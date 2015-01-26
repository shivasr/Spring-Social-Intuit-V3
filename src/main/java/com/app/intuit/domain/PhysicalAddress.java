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
@XmlRootElement(name="PhysicalAddr")  
@XmlType(propOrder={"id", "line1", "line2", "line3", "city", "countrySubDivisionCode", "postalCode" })
public class PhysicalAddress {
	
	private String id;
	
	public PhysicalAddress(com.intuit.ipp.data.PhysicalAddress qbPhysAddress) {
		if(qbPhysAddress != null)
		{
			this.id = qbPhysAddress.getId();
			this.line1 = qbPhysAddress.getLine1();
			this.line2 = qbPhysAddress.getLine2();
			this.line3 = qbPhysAddress.getLine3();
			this.city = qbPhysAddress.getCity();
			this.countrySubDivisionCode = qbPhysAddress.getCountrySubDivisionCode();
			this.postalCode = qbPhysAddress.getPostalCode();
		}
	}

	public PhysicalAddress() {
	}

	/**
	 * @return the id
	 */
	@XmlElement(name="Id")
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
	 * @return the line1
	 */
	@XmlElement(name="Line1")
	public String getLine1() {
		return line1;
	}

	/**
	 * @param line1 the line1 to set
	 */
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	/**
	 * @return the line2
	 */
	@XmlElement(name="Line2")
	public String getLine2() {
		return line2;
	}

	/**
	 * @param line2 the line2 to set
	 */
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	/**
	 * @return the line3
	 */
	@XmlElement(name="Line3")
	public String getLine3() {
		return line3;
	}

	/**
	 * @param line3 the line3 to set
	 */
	public void setLine3(String line3) {
		this.line3 = line3;
	}

	/**
	 * @return the city
	 */
	@XmlElement(name="City")
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the countrySubDivisionCode
	 */
	@XmlElement(name="CountrySubDivisionCode")
	public String getCountrySubDivisionCode() {
		return countrySubDivisionCode;
	}

	/**
	 * @param countrySubDivisionCode the countrySubDivisionCode to set
	 */
	public void setCountrySubDivisionCode(String countrySubDivisionCode) {
		this.countrySubDivisionCode = countrySubDivisionCode;
	}

	/**
	 * @return the postalCode
	 */
	@XmlElement(name="PostalCode")
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	private String line1;
	
	private String line2;
	
	private String line3;
	
	private String city;
	
	private String countrySubDivisionCode;
	
	private String postalCode;

}
