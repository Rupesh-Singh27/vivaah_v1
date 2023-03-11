package com.cg.marriageceremony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

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

import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyException;
import com.cg.marriageceremony.exceptions.FieldCannotBeEmptyExceptionForVendor;
import com.cg.marriageceremony.exceptions.NoCatererPresentException;
import com.cg.marriageceremony.exceptions.NoVendorPresentException;
import com.cg.marriageceremony.model.CartItems;
import com.cg.marriageceremony.model.Customer;
import com.cg.marriageceremony.model.Vendor;
import com.cg.marriageceremony.repository.VendorRepository;

@SpringBootTest
class VendorServiceTest {
	
	@Autowired
	VendorService vendorService;
	
	@MockBean
	VendorRepository vendorRepository;
	
//	stub
//	Test 1
	Vendor ashwin;
	List<CartItems> cartItems;
	
//	Test 2
	private Vendor rishab;
	private Vendor bumrah;
	private Vendor siraj;
	List<Vendor> vendorList;
	
//	Test 4
	Vendor shami;

	@BeforeEach
	void setUp() throws Exception {
		
//		Test 1, 3
		CartItems item1 = new CartItems(1, new Customer(), new Vendor(), 100);
		CartItems item2 = new CartItems(2, new Customer(), new Vendor(), 100);
		CartItems item3 = new CartItems(3, new Customer(), new Vendor(), 100);
		
		cartItems = new ArrayList<>();
		cartItems.add(item1);
		cartItems.add(item2);
		cartItems.add(item3);
		
		ashwin = new Vendor(1, "Catering", 100000.00f, 8452323423L, cartItems);
		
		
//		Test 2
		rishab = new Vendor(2, "MakeUp", 200000.00f, 8476523423L, cartItems);
		bumrah = new Vendor(3, "Photographer", 70000.00f, 9276523343L, cartItems);
		siraj = new Vendor(4, "Venue", 1000000.00f, 8434563423L, cartItems);
		
		vendorList = new ArrayList<>();
		vendorList.add(ashwin);
		vendorList.add(rishab);
		vendorList.add(bumrah);
		vendorList.add(siraj);
		
		
//		Exception
		shami = new Vendor();

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Saves the vendor provided into the database")
	public void test_addVendor_GivenTheVendor_ShouldReturnSavedVendor() {
		
		Mockito.when(vendorRepository.save(ashwin)).thenReturn(ashwin);
		
		Vendor vendorReturnedFromVendorServiceAddMethod = vendorService.addVendor(ashwin);
		
		assertNotNull(vendorReturnedFromVendorServiceAddMethod);
		assertEquals(ashwin, vendorReturnedFromVendorServiceAddMethod);
	}
	
	
	@Test
	@DisplayName("Return List of Vendors")
	public void test_getAllVendor_ShouldReturnListOfVendor() {
		
		Mockito.when(vendorRepository.findAll()).thenReturn(vendorList);
		
		List<Vendor> vendorListReturnedFromVendorService = vendorService.allVendor();
		
		assertNotNull(vendorList);
		assertNotNull(vendorListReturnedFromVendorService);
		assertEquals(4, vendorList.size());
		assertEquals(ashwin, vendorList.get(0));
		assertEquals(rishab, vendorList.get(1));
		assertEquals(bumrah, vendorList.get(2));
		assertEquals(siraj, vendorList.get(3));
		
	}
	
	
	@Test
	public void test_addVendor_shouldThrowFieldCannotBeEmptyExceptionForVendor() {
		
		Mockito.when(vendorRepository.save(ashwin)).thenThrow(FieldCannotBeEmptyExceptionForVendor.class);
		
		assertThrows(FieldCannotBeEmptyExceptionForVendor.class, () -> {
			
			vendorService.addVendor(shami);
			
		});
	
	}
	
	@Test
	public void test_allVendor_shouldThrowNoVendorPresentException() {
		
		Mockito.when(vendorRepository.findAll()).thenThrow(NoVendorPresentException.class);
		
		assertThrows(NoVendorPresentException.class, () -> vendorService.allVendor());
		
	}
	
	/*
	@Test
	@DisplayName("Search vendor by category")
    public void test_searchByCategory_GivenVendorCategory_ShouldReturnVendor() {
		
		Mockito.when(vendorRepository.findByCategory("Catering")).thenReturn(ashwin);
		
		Vendor vendorReturnedFromVendorServiceGetByCategoryMethod = vendorService.getByCategory("Catering");
		
		assertNotNull(vendorReturnedFromVendorServiceGetByCategoryMethod);
		assertEquals("Catering", vendorReturnedFromVendorServiceGetByCategoryMethod.getCategory());
	}*/
	
	

}
