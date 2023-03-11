package com.cg.marriageceremony.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Payment {
	@Id
	@GeneratedValue
	private int paymentId;
	private Float totalPrice;
	private Float makeUpPrice;
	private Float photographyPrice;
	private Float caterersPrice;
	private String paymentStatus;
	
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Payment(int paymentId, Float totalPrice, Float makeUpPrice, Float photographyPrice, Float caterersPrice,
			String paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.totalPrice = totalPrice;
		this.makeUpPrice = makeUpPrice;
		this.photographyPrice = photographyPrice;
		this.caterersPrice = caterersPrice;
		this.paymentStatus = paymentStatus;
	}



	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Float getMakeUpPrice() {
		return makeUpPrice;
	}
	public void setMakeUpPrice(Float makeUpPrice) {
		this.makeUpPrice = makeUpPrice;
	}
	public Float getPhotographyPrice() {
		return photographyPrice;
	}
	public void setPhotographyPrice(Float photographyPrice) {
		this.photographyPrice = photographyPrice;
	}
	public Float getCaterersPrice() {
		return caterersPrice;
	}
	public void setCaterersPrice(Float caterersPrice) {
		this.caterersPrice = caterersPrice;
	}
	
}
