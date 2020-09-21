package com.ecommerce.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.site.model.Order;
import com.ecommerce.site.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
	public Order getById(Long id) {
		return orderRepository.findById(id).get();
	}
}
