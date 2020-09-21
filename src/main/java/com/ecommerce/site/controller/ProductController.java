package com.ecommerce.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.site.model.Customer;
import com.ecommerce.site.service.CustomerService;
import com.ecommerce.site.service.ItemService;

@RestController
public class ProductController {
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/product-detail/{id}")
	public ModelAndView getIndex(@PathVariable("id")Long id, HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null) {
			long customerId = (Long)session.getAttribute("userid");
			Customer customer = customerService.getCustById(customerId);
			model.addObject("username",customer.getAccount().getUsername());
		}
		model.addObject("item", itemService.getById(Long.valueOf(id)));
		model.setViewName("product-detail");
		return model;
	}
}
