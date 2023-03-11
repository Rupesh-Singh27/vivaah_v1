package com.cg.marriageceremony.controller;


import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.marriageceremony.model.Payment;
import com.cg.marriageceremony.service.PaymentServiceImpl;

@RestController
@RequestMapping("/marriageceremony")
@CrossOrigin(origins = "http://localhost:3000",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
public class PaymentController {
	@Autowired
    PaymentServiceImpl paymentService;
	
	@PostMapping("payment/addPayment")
	public ResponseEntity<Payment> addPaymentDetails(@RequestBody Payment payment){
		Payment p = paymentService.addPaymentDetails(payment);
		
		if(p!=null)
			return ResponseEntity.ok(p);
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("payment/deletePayment/{paymentId}")
	public int deletePayment(@PathVariable int paymentId) {
		int temp = paymentService.deletePayment(paymentId);
		return temp;
	}
    
	@GetMapping("payment/getTotalAmount/{paymentId}")
    public Float getTotalAmount(int paymentId) {
		return paymentService.getTotalAmount(paymentId);
	}
    
} 


