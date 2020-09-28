package com.ecommerce.site.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.site.model.Customer;
import com.ecommerce.site.model.Item;
import com.ecommerce.site.service.CustomerService;
import com.ecommerce.site.service.ItemService;

@RestController
public class ApplicationController {
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/test")
	public List<Item> index() {
		Pageable page=PageRequest.of(0, 6, Sort.by("id"));
		return itemService.getAllItems(page);
	}
	
	@GetMapping({"/","/index"})
	public ModelAndView getIndex(HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		Pageable page=PageRequest.of(0, 2, Sort.by("id"));
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null) {
			long customerId = (Long)session.getAttribute("userid");
			Customer customer = customerService.getCustById(customerId);
			
			model.addObject("username",customer.getAccount().getUsername());
		}
		model.addObject("items", itemService.getAllItems(page));
		model.addObject("pages", 1);
		model.setViewName("index");
		return model;
	}
	
	@GetMapping("/indexs")
	public ModelAndView getIndexByPage(@RequestParam("page")Integer pages, HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		Pageable page=PageRequest.of(pages, 2, Sort.by("id"));
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null) {
			long customerId = (Long)session.getAttribute("userid");
			Customer customer = customerService.getCustById(customerId);
			
			model.addObject("username",customer.getAccount().getUsername());
		}
		model.addObject("items", itemService.getAllItems(page));
		model.addObject("pages", newPageNumber(pages));
		model.setViewName("index");
		return model;
	}
	
	private int newPageNumber(Integer page) {
		Integer newPage=page+1;
		
		Pageable pageable=PageRequest.of(newPage, 2, Sort.by("id"));
		int size = itemService.getAllItems(pageable).size();
		if(size==0)
			newPage=0;
		return newPage;
	}
	
}
