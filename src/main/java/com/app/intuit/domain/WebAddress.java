package com.app.intuit.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WebAddr")
public class WebAddress {

	public WebAddress(){
		
	}
	public WebAddress(com.intuit.ipp.data.WebSiteAddress webSiteAddress) {
		// TODO Auto-generated constructor stub
		this.uri = webSiteAddress.getURI();
	}
	String uri;
	
	@XmlElement(name = "URI")
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

}
