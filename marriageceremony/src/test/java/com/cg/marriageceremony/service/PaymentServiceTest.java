package com.cg.marriageceremony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyException;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForPayment;
import com.cg.marriageceremony.exceptions.PaymentDoesNotExistException;
import com.cg.marriageceremony.model.Payment;
import com.cg.marriageceremony.repository.PaymentRepository;

@SpringBootTest
class PaymentServiceTest {
	
	@MockBean
	PaymentRepository paymentRepository;
	
	@Autowired
	PaymentService paymentService;
	
//	Stubb
	Payment payment;
	
//	Exception
	Payment anotherPayment;

	@BeforeEach
	void setUp() throws Exception {
		payment = new Payment(1, 1500000f, 200000f, 300000f, 500000f, "Paid");
		
//		Exception
		anotherPayment = new Payment();
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	@DisplayName("Saves the payment object provided into the database")
	public void test_addPaymentDetails_GivenThePayment_ShouldReturnSavedPayment() {

		Mockito.when(paymentRepository.save(payment)).thenReturn(payment);

		Payment paymentReturnedFromPaymentServiceAddMethod = paymentService.addPaymentDetails(payment);

		assertNotNull(paymentReturnedFromPaymentServiceAddMethod);
		assertEquals(payment, paymentReturnedFromPaymentServiceAddMethod);
	}
	
	@Test
	public void test_addPaymentDetails_shouldThrowFieldCannotBeEmptyExceptionForPaymentDetails() {
		
		Mockito.when(paymentRepository.save(payment)).thenThrow(new FieldCannotBeEmptyExceptionForPayment());
		
		assertThrows(FieldCannotBeEmptyExceptionForPayment.class, () -> {
			
			paymentService.addPaymentDetails(anotherPayment);
			
		});
	}

}
