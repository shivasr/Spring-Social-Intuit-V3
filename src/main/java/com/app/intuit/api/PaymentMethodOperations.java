package com.app.intuit.api;

import java.util.List;

import com.intuit.ipp.data.PaymentMethod;


public interface PaymentMethodOperations {
	
	public PaymentMethod getPaymentMethod(Long id);
	public List<PaymentMethod> getPaymentMethods();
}
