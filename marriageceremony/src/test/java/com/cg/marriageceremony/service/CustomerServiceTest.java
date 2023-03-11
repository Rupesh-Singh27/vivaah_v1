package com.cg.marriageceremony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import com.cg.marriageceremony.model.CartItems;
import com.cg.marriageceremony.model.Customer;
import com.cg.marriageceremony.model.Vendor;
import com.cg.marriageceremony.repository.CustomerRepository;


@SpringBootTest
class CustomerServiceTest {
	
	
	/*
	 * @Mock creates a mock, and @InjectMocks creates an instance of the class and
	 * injects the mocks that are created with the @Mock annotations into this
	 * instance.
	 */
	
	@Autowired
	CustomerService customerService;
	
	@MockBean
	CustomerRepository customerRepository;
	
	//Stubs
	private Customer rohit;
	private Customer virat;
	private Customer shubman;
	private Customer ishan;
	List<Customer> customerList;
	
	List<CartItems> cartItems;
	

	@BeforeEach
	void setUp() throws Exception {
		
		CartItems item1 = new CartItems(1, new Customer(), new Vendor(), 100);
		CartItems item2 = new CartItems(2, new Customer(), new Vendor(), 100);
		CartItems item3 = new CartItems(3, new Customer(), new Vendor(), 100);
		
		cartItems = new ArrayList<>();
		cartItems.add(item1);
		cartItems.add(item2);
		cartItems.add(item3);
		
		//Test 1
		rohit = new Customer(1, "rohit", 3333333333L, "rohit@gmail.com", cartItems);
		
		//Test2
		virat = new Customer(2, "virat", 8439323023L, "virat@gmail.com", cartItems);
		shubman = new Customer(3, "shubman", 9232348343L, "shubman@gmail.com", cartItems);
		ishan = new Customer(4, "ishan", 9232747343L, "ishan@gmail.com", cartItems);
		
		customerList = new ArrayList<>();
		customerList.add(rohit);
		customerList.add(virat);
		customerList.add(shubman);
		customerList.add(ishan);
	}

	@AfterEach
	void tearDown() throws Exception {
		customerRepository.deleteAll();
		rohit = null;
	}

	@Test
	@DisplayName("Saves the customer provided into the database")
	public void test_addCustomer_GivenTheCustomer_ShouldReturnSavedCustomer() {
		
		Mockito.when(customerRepository.save(rohit)).thenReturn(rohit);
		
		Customer customerReturnedFromCustomerService = customerService.addCustomer(rohit);
		
		assertNotNull(customerReturnedFromCustomerService);
		assertEquals(rohit, customerReturnedFromCustomerService);
	}
	
	@Test
	@DisplayName("Return List of Customers")
	public void test_getAllCustomer_ShouldReturnListOfCustomer() {
		
		Mockito.when(customerRepository.findAll()).thenReturn(customerList);
		
		List<Customer> customerListReturnedFromCustomerService = customerService.allCustomers();
		
		assertNotNull(customerList);
		assertNotNull(customerListReturnedFromCustomerService);
		assertEquals(4, customerList.size());
		assertEquals(rohit, customerList.get(0));
		assertEquals(virat, customerList.get(1));
		assertEquals(shubman, customerList.get(2));
		assertEquals(ishan, customerList.get(3));
		
	}

}
