package com.ecommerce.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.site.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	public Account getByUsername(String username);
	
}
