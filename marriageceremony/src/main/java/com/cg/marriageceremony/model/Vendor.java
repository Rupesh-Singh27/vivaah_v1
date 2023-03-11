package com.cg.marriageceremony.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity

public class Vendor {
		@Id
		@GeneratedValue
		int vendorId;
		String category;
		Float price;
		Long contactNo;
		@JsonManagedReference("vendor")
		@OneToMany(mappedBy="vendor")
		private List<CartItems> cartItems;

		public Vendor() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		public Vendor(int vendorId, String category, Float price, Long contactNo,
				List<com.cg.marriageceremony.model.CartItems> cartItems) {
			super();
			this.vendorId = vendorId;
			this.category = category;
			this.price = price;
			this.contactNo = contactNo;
			this.cartItems = cartItems;
		}



		public int getVendorId() {
			return vendorId;
		}
		public void setVendorId(int vendorId) {
			this.vendorId = vendorId;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public Float getPrice() {
			return price;
		}
		public void setPrice(Float price) {
			this.price = price;
		}
		public Long getContactNo() {
			return contactNo;
		}
		public void setContactNo(Long contactNo) {
			this.contactNo = contactNo;
		}
		public List<CartItems> getCartItems() {
			return cartItems;
		}
		public void setCartItems(List<CartItems> cartItems) {
			this.cartItems = cartItems;
		}
		
		
}
