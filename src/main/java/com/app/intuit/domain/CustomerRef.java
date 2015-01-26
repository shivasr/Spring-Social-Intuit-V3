package com.app.intuit.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;




@XmlRootElement(name="CustomerRef")
public class CustomerRef {
	private String name;
	private String value;
	
	public CustomerRef()
	{
		
	}
	
	public CustomerRef(String name,String value)
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
