package com.app.intuit.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name="CurrencyRef")
public class CurrencyRef {
	private String name ;
	
	//private String USD;
	private String value;
	
	public CurrencyRef()
	{
		
	}
	
	public CurrencyRef(String name,String value)
	{
		this.name = name;
		this.value = value;
	}

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	@XmlValue
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
