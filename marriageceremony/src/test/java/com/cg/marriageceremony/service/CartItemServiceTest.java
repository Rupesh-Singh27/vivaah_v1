package com.cg.marriageceremony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.marriageceremony.dto.CartItemsData;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForCart;
import com.cg.marriageceremony.exceptions.NoCatererPresentException;
import com.cg.marriageceremony.model.CartItems;
import com.cg.marriageceremony.model.Customer;
import com.cg.marriageceremony.model.Vendor;
import com.cg.marriageceremony.repository.CartItemsRepository;

@SpringBootTest
class CartItemServiceTest {

	@MockBean
	private CartItemsRepository cartRepository;

	@Autowired
	private CartService cartService;

//	Stub
//	Test 1
	CartItems item1;
	
	CartItemsData cartItemData;

//	Test 2
	List<CartItems> cartItems;

	CartItems item2;
	CartItems item3;
	
	Customer rohit;
	
//	Exception
	CartItemsData product;

	@BeforeEach
	void setUp() throws Exception {
//		Test 1
		item1 = new CartItems(1, new Customer(), new Vendor(), 100);
		cartItemData = new CartItemsData(1,2);

//		Test 2 & 3
		item2 = new CartItems(2, new Customer(), new Vendor(), 100);
		item3 = new CartItems(3, new Customer(), new Vendor(), 100);

		cartItems = new ArrayList<>();
		cartItems.add(item1);
		cartItems.add(item2);
		cartItems.add(item3);
		
		rohit = new Customer(1, "rohit", 3333333333L, "rohit@gmail.com", cartItems);
		
//		Exception
		product = new CartItemsData();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	@Test
	@DisplayName("Saves the cart item object provided into the database")
	public void test_addCartItemInCart_GivenTheCartItem_ShouldReturnSavedCartItem() {

		Mockito.when(cartRepository.save(item1)).thenReturn(item1);

		CartItems cartItemReturnedFromCartServiceAddMethod = cartService.addItemsInCart(cartItemData);

		assertNotNull(cartItemReturnedFromCartServiceAddMethod);
		assertEquals(item1, cartItemReturnedFromCartServiceAddMethod);
	}*/

	
	@Test
	@DisplayName("Return List of Cart items assosiated by customer Id")
	public void test_getAllCartItems_GivenCustomerID_ShouldReturnListOfMakeups() {

		Mockito.when(cartRepository.findAllItemsInCart((int)1L)).thenReturn(cartItems);

		List<CartItems> cartItemListReturnedFromCartItemService = cartService.findAllItemsInCart((int)1L);

		assertNotNull(cartItems);
		assertNotNull(cartItemListReturnedFromCartItemService);
		assertEquals(3, cartItemListReturnedFromCartItemService.size());
		assertNotEquals(4, cartItemListReturnedFromCartItemService.size());
		assertEquals(item1, cartItemListReturnedFromCartItemService.get(0));
		assertEquals(item2, cartItemListReturnedFromCartItemService.get(1));
		assertEquals(item3, cartItemListReturnedFromCartItemService.get(2));
	}
	
	@Test
	public void test_addItemsInCart_shouldThrowFieldCannotBeEmptyExceptionForCart() {
		Mockito.when(cartRepository.save(item1)).thenThrow(FieldCannotBeEmptyExceptionForCart.class);
		Exception exception = assertThrows(FieldCannotBeEmptyExceptionForCart.class, () -> cartService.addItemsInCart(product));
		assertEquals("Please fill every field appropriately", exception.getMessage());
	}
	
	
	@Test
	public void test_allCateres_shouldThrowNoCartItemPresentException() {
		
		Mockito.when(cartRepository.findAllItemsInCart(5)).thenThrow(NoCatererPresentException.class);
		
		assertThrows(NoCatererPresentException.class, () -> cartService.findAllItemsInCart(5));
		
	}
	
	/*
	 * @Test
	 * 
	 * @DisplayName("Delete and return the vendor whose Id is specified") public
	 * void test_deleteItemFromCart_GivenCustomerId_ShouldDeleteCartItemById() {
	 * 
	 * Mockito.when(cartRepository.deleteItemFromCart((int)1L,
	 * (int)1L)).thenReturn(null);
	 * 
	 * Integer cartItemReturnedFromCartServiceAddMethod =
	 * cartService.deleteItemFromCart(int)1L, (int)1L));
	 * 
	 * assertNull(shami.getVendorId()); assertNull(shami.getPrice());
	 * assertNull(shami.getContactNo()); assertNull(shami.getCartItems());
	 * assertNull(shami.getCategory()); }
	 */
	

}
