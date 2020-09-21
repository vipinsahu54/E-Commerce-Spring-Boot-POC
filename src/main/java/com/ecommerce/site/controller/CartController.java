package com.ecommerce.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.site.model.Cart;
import com.ecommerce.site.model.CartItems;
import com.ecommerce.site.model.Customer;
import com.ecommerce.site.service.CartService;
import com.ecommerce.site.service.CustomerService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/additem/{itemid}/{qty}")
	public Cart addItem(@PathVariable("itemid")Long itemId,@PathVariable("qty")Integer qty,HttpServletRequest request) {
		return cartService.addItem(1001l, itemId, qty);
	}
	
	@GetMapping("/additem/{itemid}")
	public ModelAndView addItemByItemId(@PathVariable("itemid")Long itemId,@RequestParam("quantity")Integer qty,HttpServletRequest request, ModelMap model) {
		cartService.addItem(1001l, itemId, qty);
		return new ModelAndView("redirect:/index", model);
	}
	
	@GetMapping("/{cartid}")
	public Cart getCart(@PathVariable("cartid")Long cartId) {
		return cartService.getCartById(cartId);
	}
	
	@GetMapping("")
	public ModelAndView getCartByCustomer(HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") == null)
			model.setViewName("login");
		else {
			long customerId = (Long)session.getAttribute("userid");
			Customer customer = customerService.getCustById(customerId);
			Cart cart = cartService.getCartById(customer.getCart().getId());
			int total=cart.getItems().stream().mapToInt(obj -> obj.getQuantity() * obj.getItem().getPrice()).sum();
			model.addObject("cart",cart);
			model.addObject("totalCart",total);
			model.addObject("customer",customer);
			model.addObject("username",customer.getAccount().getUsername());
			model.setViewName("cart");
		}
		return model;
	}
	
	@GetMapping("/decrease/{id}")
	public void decreaseQty(@PathVariable("id") Long id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null) {
			long customerId = (Long)session.getAttribute("userid");
			Customer customer = customerService.getCustById(customerId);
			Cart cart = cartService.getCartById(customer.getCart().getId());
			
			for (CartItems items : cart.getItems()) {
				if(items.getItem().getId().equals(id)) {
					System.out.println(items.getItem().getId()+"-- "+items.getQuantity());
					items.setQuantity(items.getQuantity()-1);
				}
			}
			cartService.save(cart);
		}
	}
	
	@GetMapping("/increase/{id}")
	public void increaseQty(@PathVariable("id")Long id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userid") != null) {
			long customerId = (Long)session.getAttribute("userid");
			Customer customer = customerService.getCustById(customerId);
			Cart cart = cartService.getCartById(customer.getCart().getId());
			
			for (CartItems items : cart.getItems()) {
				if(items.getItem().getId().equals(id)) {
					System.out.println(items.getItem().getId()+"-- "+items.getQuantity());
					items.setQuantity(items.getQuantity()+1);
				}
			}
			cartService.save(cart);
		}
	}
}
