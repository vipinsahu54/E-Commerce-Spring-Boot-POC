package com.ecommerce.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.site.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
