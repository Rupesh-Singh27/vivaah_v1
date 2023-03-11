package com.cg.marriageceremony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.marriageceremony.dto.CartItemsData;
import com.cg.marriageceremony.model.CartItems;
import com.cg.marriageceremony.repository.CustomerRepository;
import com.cg.marriageceremony.service.CartService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/marriageceremony")
@CrossOrigin(origins = "http://localhost:3000",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
public class CartController {

	@Autowired
	private CartService cartservice;

	@Autowired
	CustomerRepository custRepo;

	@ApiOperation("Add item in cart")
	@PostMapping("cart/addItemsInCart")
	public CartItems addItemsInCart(@RequestBody CartItemsData cartData) {
		CartItems cart = cartservice.addItemsInCart(cartData);
		return cart;
	}
	
	@ApiOperation("Get item from cart")
	@GetMapping("cart/findAllItemsPresentInCart")
	public List<CartItems> findAllItemsInCart(@PathVariable("cid") int custId){
		List<CartItems> list = cartservice.findAllItemsInCart(custId);
		return list;
		
	}

}