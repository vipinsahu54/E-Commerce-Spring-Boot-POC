package com.ecommerce.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.site.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer getByEmail(String email);

}
