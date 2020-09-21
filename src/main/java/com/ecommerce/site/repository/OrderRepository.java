package com.ecommerce.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.site.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
