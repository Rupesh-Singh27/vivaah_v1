package com.cg.marriageceremony.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private int custId;
	private String custName;
	private long mobileNo;
	private String email;
	 
	@JsonManagedReference("customer")
	@OneToMany(mappedBy = "customer")
	private List<CartItems> CartItems;
	
	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Customer(int custId, String custName, long mobileNo, String email,
			List<com.cg.marriageceremony.model.CartItems> cartItems) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.mobileNo = mobileNo;
		this.email = email;
		CartItems = cartItems;
	}



	public List<CartItems> getCartItems() {
		return CartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		CartItems = cartItems;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}

