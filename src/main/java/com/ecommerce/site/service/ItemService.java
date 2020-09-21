package com.ecommerce.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.site.model.Item;
import com.ecommerce.site.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getAllItems(Pageable pageable){
		return itemRepository.findAll(pageable).getContent();
	}
	
	public List<Item> getAllItems(){
		return (List<Item>) itemRepository.findAll();
	}
	
	public Item getById(Long id) {
		return itemRepository.findById(id).get();
	}
}
