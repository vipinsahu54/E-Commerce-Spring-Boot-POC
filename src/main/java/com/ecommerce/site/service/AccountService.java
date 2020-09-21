package com.ecommerce.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.site.model.Account;
import com.ecommerce.site.model.Customer;
import com.ecommerce.site.model.Login;
import com.ecommerce.site.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerService customerService;
	
	public Account getAccountByUserName(String username) {
		return accountRepository.getByUsername(username);
	}
	
	public boolean validateUser(Login login) {
		Customer customer = customerService.getAccountByEmail(login.getUsername());
		if(customer.getAccount().getPassword().equals(login.getPassword()))
			return true;
		return false;
	}
}
