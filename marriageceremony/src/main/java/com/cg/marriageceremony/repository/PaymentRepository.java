package com.cg.marriageceremony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.marriageceremony.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	@Query(value= "delete from Payment p where p.paymentId=:pid")
	@Modifying
	int deletePayment(@Param("pid") int pid);
	
//	Float findByPaymentId(Float paymentId);
}

