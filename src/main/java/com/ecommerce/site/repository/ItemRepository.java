package com.ecommerce.site.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecommerce.site.model.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long>{

}
