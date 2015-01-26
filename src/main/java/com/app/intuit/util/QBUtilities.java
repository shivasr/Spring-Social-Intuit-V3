package com.app.intuit.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.app.intuit.api.IntuitProperties;
import com.app.intuit.api.impl.IntuitTemplate;
import com.app.intuit.domain.CurrencyRef;
import com.app.intuit.domain.CustomerRef;
import com.app.intuit.domain.EmailAddress;
import com.app.intuit.domain.ItemRef;
import com.app.intuit.domain.Line;
import com.app.intuit.domain.PhysicalAddress;
import com.app.intuit.domain.QBInvoice;
import com.app.intuit.domain.QbCustomer;
import com.app.intuit.domain.SalesItemLineDetail;
import com.app.intuit.domain.SubTotalLineDetail;
import com.app.intuit.domain.TelephoneNumber;
import com.intuit.ipp.data.LineDetailTypeEnum;
import com.intuit.ipp.data.ReferenceType;

/**
 * This class contains utilities to populate FieldMatic entities with QB
 * Integration models
 * 
 * @author Shiva
 * 
 */
public class QBUtilities {
	
	/**
	 * 
	 */
	public static IntuitTemplate  buildIntuit() {
		IntuitProperties properties = new IntuitProperties() {
			
			@Override
			public String getConsumerSecret() {
				return "Xc1KwMyDCd7HIGPNm6YZKcYENIBx5dyuH301fcMw";
			}
			
			@Override
			public String getConsumerKey() {
				return "qyprd1vHWzip1M4AaWYDUI058XlO8F";
			}
			
			@Override
			public String getCompanyId() {
				return "1315187735";
			}
			
			@Override
			public String getAccessTokenSecret() {
				return "ZUtaXR1ircDRSvOEdRpAqLmakcfQKX7Gy5DBGw20";
			}
			
			@Override
			public String getAccessToken() {
				return "qyprd7cHHUByyxm2w7S2UDlIp4PVKEECPfHgeh9glC5DECGm";
			}

			@Override
			public String getBaseUrl() {
				return "https://sandbox-quickbooks.api.intuit.com";
			}
		};
		
		IntuitTemplate intuit = new IntuitTemplate(properties);
		return intuit;
	}

	public static QbCustomer convertToQbCustomer(com.intuit.ipp.data.Customer customer) {
		QbCustomer qbCustomer = new QbCustomer();
		qbCustomer.setActive(customer.isActive());
		qbCustomer.setCompanyName(customer.getCompanyName());
		qbCustomer.setFamilyName(customer.getFamilyName());
		qbCustomer.setGivenName(customer.getGivenName());
		qbCustomer.setId(customer.getId());
		qbCustomer.setMiddleName(customer.getMiddleName());
		
		qbCustomer.setAlternatePhone(new TelephoneNumber(customer.getAlternatePhone()));
		qbCustomer.setBillAddr(new PhysicalAddress(customer.getBillAddr()));
		
		
		qbCustomer.setPrimaryPhone(new TelephoneNumber(customer.getPrimaryPhone()));
		qbCustomer.setPrimaryEmailAddr(new EmailAddress(customer.getPrimaryEmailAddr()));
		qbCustomer.setFax(new TelephoneNumber(customer.getFax()));
		qbCustomer.setMobile(new TelephoneNumber(customer.getMobile()));
		
		qbCustomer.setSyncToken(Integer.valueOf(customer.getSyncToken()));
		
		
		return qbCustomer;
		
	}
	
	public static QBInvoice convertToQBInvoice(com.intuit.ipp.data.Invoice invoice) {
		QBInvoice qbInvoice = new QBInvoice();
		//qbInvoice.setActive(invoice.isActive());
//		qbInvoice.setCompanyName(invoice.getCompanyName());
//		qbInvoice.setFamilyName(invoice.getFamilyName());
//		qbInvoice.setGivenName(invoice.getGivenName());
//		qbInvoice.setId(invoice.getId());
//		qbInvoice.setMiddleName(invoice.getMiddleName());
		qbInvoice.setId(Long.valueOf(invoice.getId()));
		qbInvoice.setDomain(invoice.getDomain());
		qbInvoice.setBalance(invoice.getBalance().doubleValue());
		qbInvoice.setBillAddr(new PhysicalAddress(invoice.getBillAddr()));
		qbInvoice.setShipAddr(new PhysicalAddress(invoice.getShipAddr()));
		final ReferenceType currencyRef = invoice.getCurrencyRef();
		
		if(currencyRef != null)
			qbInvoice.setCurrencyRef(new CurrencyRef(currencyRef.getName(),currencyRef.getValue()));
	
		final ReferenceType customerRef = invoice.getCustomerRef();
		
		if(customerRef != null)
			qbInvoice.setCustomerRef(new CustomerRef(customerRef.getName(),customerRef.getValue()));
		
		qbInvoice.setDocNumber(Long.valueOf(invoice.getDocNumber()));
		qbInvoice.setDueDate(invoice.getDueDate());
		List<com.intuit.ipp.data.Line> qbLines = invoice.getLine();
		
		for(com.intuit.ipp.data.Line qbLine : qbLines){
			Line line = new Line();
			
			String id = qbLine.getId();
			
			if(id != null)
				line.setId(Long.valueOf(id));
			
			BigInteger lineNum = qbLine.getLineNum();
			
			if(lineNum != null)
				line.setLineNum(lineNum.longValue());
			
			line.setDetailType(qbLine.getDetailType().name());
			line.setDescription(qbLine.getDescription());
			
			BigDecimal amoungBigDecimal = qbLine.getAmount();
			
			if(amoungBigDecimal != null)
				line.setAmount(amoungBigDecimal.doubleValue());

			if( LineDetailTypeEnum.SALES_ITEM_LINE_DETAIL == qbLine.getDetailType()){
				SalesItemLineDetail salesItemLineDetail = convertToSalesitemLineDetail(qbLine);
				line.setSalesItemLineDetail(salesItemLineDetail);
			} else if (LineDetailTypeEnum.SUB_TOTAL_LINE_DETAIL == qbLine.getDetailType()) {
				com.intuit.ipp.data.SubTotalLineDetail qbSubtotalLineDetail = qbLine.getSubTotalLineDetail();
				
				SubTotalLineDetail subTotalLineItem = new SubTotalLineDetail();
				
				final ReferenceType subTotalitemRef = qbSubtotalLineDetail.getItemRef();
				
				if(subTotalitemRef != null){
					ItemRef itemRef = new ItemRef(subTotalitemRef.getName(), subTotalitemRef.getValue());
					subTotalLineItem.setItemRef(itemRef);
				}
				
				Date serviceDate = qbSubtotalLineDetail.getServiceDate();
				
				if(serviceDate != null)
					subTotalLineItem.setServiceDate(serviceDate);
			}
		}
		
	
//		qbCustomer.setAlternatePhone(new TelephoneNumber(customer.getAlternatePhone()));
//		qbCustomer.setBillAddr(new PhysicalAddress(customer.getBillAddr()));
//		
//		
//		qbCustomer.setPrimaryPhone(new TelephoneNumber(customer.getPrimaryPhone()));
//		qbCustomer.setPrimaryEmailAddr(new EmailAddress(customer.getPrimaryEmailAddr()));
//		qbCustomer.setFax(new TelephoneNumber(customer.getFax()));
//		qbCustomer.setMobile(new TelephoneNumber(customer.getMobile()));
//		
//		qbCustomer.setSyncToken(Integer.valueOf(customer.getSyncToken()));
		
		
		return qbInvoice;
		
	}

	private static SalesItemLineDetail convertToSalesitemLineDetail(
			com.intuit.ipp.data.Line qbLine) {
		com.intuit.ipp.data.SalesItemLineDetail qbSalesLineDetail = qbLine.getSalesItemLineDetail();
		SalesItemLineDetail salesItemLineDetail = new SalesItemLineDetail();
		salesItemLineDetail.setItemRef(new ItemRef(qbSalesLineDetail.getItemRef().getName(), qbSalesLineDetail.getItemRef().getValue()));
		
		BigDecimal qtyBigDecimal = qbSalesLineDetail.getQty();
		if(qtyBigDecimal != null)
			salesItemLineDetail.setQty(qtyBigDecimal.doubleValue());
		
		BigDecimal unitPriceBig = qbSalesLineDetail.getUnitPrice();
		
		if(unitPriceBig != null)
			salesItemLineDetail.setUnitPrice(unitPriceBig.doubleValue());
		return salesItemLineDetail;
	}
}
