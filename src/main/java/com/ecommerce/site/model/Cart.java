package com.ecommerce.site.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CART")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL , fetch=FetchType.EAGER)
	private List<CartItems> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartItems> getItems() {
		return items;
	}

	public void setItems(List<CartItems> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", items=" + items + "]";
	}
	
}
