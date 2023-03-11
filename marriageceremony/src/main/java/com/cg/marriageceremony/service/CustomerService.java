package com.cg.marriageceremony.service;

import java.util.List;

import com.cg.marriageceremony.model.Customer;


public interface CustomerService {
	Customer addCustomer(Customer c);
	List<Customer> allCustomers();

}
