package com.cg.marriageceremony.service;

import java.util.List;

import com.cg.marriageceremony.dto.CartItemsData;
import com.cg.marriageceremony.model.CartItems;


public interface CartService {
	
	CartItems addItemsInCart(CartItemsData cartData);
	
	List<CartItems> findAllItemsInCart(int customerId);
	//List<Vendor> getVendorFromCart(int customerId);
	
	int deleteItem(int customerId, int vId);
}
