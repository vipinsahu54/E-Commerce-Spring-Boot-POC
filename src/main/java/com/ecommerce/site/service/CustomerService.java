package com.ecommerce.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.site.model.Customer;
import com.ecommerce.site.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer getCustById(Long id) {
		return customerRepository.findById(id).get();
	}

	public Customer getAccountByEmail(String email) {
		return customerRepository.getByEmail(email);
	}
}
