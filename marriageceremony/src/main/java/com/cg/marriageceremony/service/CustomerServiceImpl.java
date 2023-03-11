package com.cg.marriageceremony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.marriageceremony.model.Customer;
import com.cg.marriageceremony.repository.CustomerRepository;




@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired

	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer c) {
		Customer cust = customerRepository.save(c);
		return cust;
	}

	@Override
	public List<Customer> allCustomers() {
		List<Customer> custList = customerRepository.findAll();
		return custList;
	}
}
