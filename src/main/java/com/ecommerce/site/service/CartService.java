package com.ecommerce.site.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.site.model.Cart;
import com.ecommerce.site.model.CartItems;
import com.ecommerce.site.model.Item;
import com.ecommerce.site.repository.CartRepository;
import com.ecommerce.site.repository.ItemRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Cart addItem(Long cartId, Long itemId, int qty) {
		Cart cart=null;
		Optional<Cart> cartOptional = cartRepository.findById(cartId);
		if(cartOptional.isPresent())
			cart=cartOptional.get();
		Item item = itemRepository.findById(itemId).get();
		
		if(cart == null) {
			cart=new Cart();
			cart.setItems(new ArrayList<CartItems>());
		}
		
		List<CartItems> items = cart.getItems();
		CartItems cartItem=new CartItems();
		cartItem.setItem(item);
		cartItem.setQuantity(qty);
		items.add(cartItem);

		cart.setItems(items);
		cartRepository.save(cart);
		return cart;
	}
	
	public Cart getCartById(Long cartId) {
		return cartRepository.findById(cartId).get();
	}
	
	public void save(Cart cart) {
		cartRepository.save(cart);
	}
	
	public void delete(Cart cart) {
		cartRepository.delete(cart);
	}
}
