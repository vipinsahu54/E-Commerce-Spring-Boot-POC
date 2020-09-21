package com.ecommerce.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.site.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
