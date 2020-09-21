package com.ecommerce.site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.site.model.Item;
import com.ecommerce.site.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping(value="/items/{page}")
	public List<Item> getAllItems(@PathVariable("page")Integer pageNo){
		Pageable page=PageRequest.of(pageNo, 6, Sort.by("id"));
		return itemService.getAllItems(page);
	}
	
	@GetMapping(value="/items")
	public List<Item> getAllItems(){
		return itemService.getAllItems();
	}
}
