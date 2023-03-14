package com.cg.marriageceremony.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForPayment;
import com.cg.marriageceremony.exceptions.PaymentDoesNotExistException;
import com.cg.marriageceremony.model.Payment;
import com.cg.marriageceremony.repository.PaymentRepository;

@Component
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Payment addPaymentDetails(Payment payment) {
		try {
			if(payment.getTotalPrice() == null || payment.getMakeUpPrice() == null|| payment.getPhotographyPrice() == null|| payment.getCaterersPrice() == null || payment.getPaymentStatus() == null ||
					payment.getTotalPrice() < 0.0f || payment.getMakeUpPrice() < 0.0f || payment.getPhotographyPrice() < 0.0f || payment.getCaterersPrice() < 0.0f) 
			{
				throw new FieldCannotBeEmptyExceptionForPayment("Please fill every field appropriately");
			}
			
			return paymentRepository.save(payment);
		}catch(FieldCannotBeEmptyExceptionForPayment exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	@Transactional
	public int deletePayment(int paymentId) {
		try {
		
			Payment caterer = paymentRepository.findById(paymentId).get();
			
			if(caterer == null) {
				throw new PaymentDoesNotExistException("Payment does not exist with the id " + paymentId);
			
			}
			
			return paymentRepository.deletePayment(paymentId);
		}catch(PaymentDoesNotExistException exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	public Float getTotalAmount(int paymentId) {
		Optional<Payment> payment = paymentRepository.findById(paymentId);
		if(payment.isPresent()) {
			return payment.get().getTotalPrice();
		}
		return 0.00f;
	}

}



   








