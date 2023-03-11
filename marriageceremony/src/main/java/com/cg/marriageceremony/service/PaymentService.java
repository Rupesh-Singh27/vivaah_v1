package com.cg.marriageceremony.service;

import com.cg.marriageceremony.model.Payment;

public interface PaymentService {
	public Payment addPaymentDetails(Payment payment);
	public int deletePayment(int paymentId);
	public Float getTotalAmount(int paymentId);
}

