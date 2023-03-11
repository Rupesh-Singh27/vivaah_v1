package com.cg.marriageceremony.dto;

public class CartItemsData {

	private int customerId;
	private int vendorId;
	

	public CartItemsData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItemsData(int customerId, int vendorId) {
		super();
		this.customerId = customerId;
		this.vendorId = vendorId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

}
