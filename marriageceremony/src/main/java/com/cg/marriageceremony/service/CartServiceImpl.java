package com.cg.marriageceremony.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.marriageceremony.dto.CartItemsData;
import com.cg.marriageceremony.exceptions.CatererDoesNotExistException;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyException;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForCart;
import com.cg.marriageceremony.exceptions.NoCatererPresentException;
import com.cg.marriageceremony.model.CartItems;
import com.cg.marriageceremony.model.Caterer;
import com.cg.marriageceremony.model.Customer;
import com.cg.marriageceremony.model.Vendor;
import com.cg.marriageceremony.repository.CartItemsRepository;
import com.cg.marriageceremony.repository.CustomerRepository;
import com.cg.marriageceremony.repository.VendorRepository;





@Component
public class CartServiceImpl implements CartService {
    @Autowired
	private CartItemsRepository cartRepo;
    @Autowired
    private CustomerRepository custRepo;
    @Autowired
    private VendorRepository venRepo;
    @PersistenceContext
    private EntityManager em;
	
	@Override
	public CartItems addItemsInCart(CartItemsData cartData) {
		
		try {
			if(cartData.getCustomerId() <= 0 || cartData.getVendorId() <= 0) {
				throw new FieldCannotBeEmptyExceptionForCart("Please fill every field appropriately");
			}
		}catch(FieldCannotBeEmptyExceptionForCart exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
		
		int cid= cartData.getCustomerId();
		Customer c=custRepo.findById(cid).get();
		int vid= cartData.getVendorId();
		Vendor v= venRepo.findById(vid).get();
		CartItems cart= new CartItems();
		cart.setCustomer(c);
		cart.setVendor(v);
		cart= cartRepo.save(cart);
		return cart;
	}

	@Override
	public List<CartItems> findAllItemsInCart(int customerId) {
		try {
			List<CartItems> cartList= cartRepo.findAllItemsInCart(customerId);
			
			if(cartList.isEmpty()) {
				throw new NoCatererPresentException("There in no items present in database");
			}
			
			return cartList;
		}catch(NoCatererPresentException exception) {
			throw exception;
		}catch(RuntimeException exception) {
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	public int deleteItem(int vendorId, int customerId) {
		
		return cartRepo.deleteItemFromCart(vendorId, customerId);
	}

}