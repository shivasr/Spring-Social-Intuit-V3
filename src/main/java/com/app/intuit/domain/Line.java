package com.app.intuit.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="Line")
@XmlType(propOrder = {"id","lineNum","description", "amount","detailType","salesItemLineDetail", "subTotalLineDetail"})
public class Line {
	private Long id;
	private Long lineNum;
	
	private double amount;
	private SalesItemLineDetail salesItemLineDetail = null;
	private SubTotalLineDetail subTotalLineDetail = null;
	
	private String description;
	
	String detailType ;
	
	@XmlElement(name="Id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement(name="LineNum")
	public Long getLineNum() {
		return lineNum;
	}
	public void setLineNum(Long lineNum) {
		this.lineNum = lineNum;
	}
	
	@XmlElement(name="Amount")
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@XmlElement(name="DetailType")
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	
	@XmlElement(name="SalesItemLineDetail")
	public SalesItemLineDetail getSalesItemLineDetail() {
		return salesItemLineDetail;
	}
	public void setSalesItemLineDetail(SalesItemLineDetail salesItemLineDetail) {
		this.salesItemLineDetail = salesItemLineDetail;
	}
	
	/**
	 * @return the subTotalLineDetail
	 */
	@XmlElement(name="SubTotalLineDetail")
	public SubTotalLineDetail getSubTotalLineDetail() {
		return subTotalLineDetail;
	}
	/**
	 * @param subTotalLineDetail the subTotalLineDetail to set
	 */
	public void setSubTotalLineDetail(SubTotalLineDetail subTotalLineDetail) {
		this.subTotalLineDetail = subTotalLineDetail;
	}
	/**
	 * @return the description
	 */
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
