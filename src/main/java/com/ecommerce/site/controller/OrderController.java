package com.ecommerce.site.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.site.model.Cart;
import com.ecommerce.site.model.CartItems;
import com.ecommerce.site.model.Customer;
import com.ecommerce.site.model.Order;
import com.ecommerce.site.service.CartService;
import com.ecommerce.site.service.CustomerService;
import com.ecommerce.site.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/checkout")
	public ModelAndView getCheckout(HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		model.setViewName("checkout");
		return model;
	}
	
	@GetMapping("/order/{id}")
	public Order getOrder(@PathVariable("id") Long id) {
		return orderService.getById(id);
	}
	
	@GetMapping("/confirmation")
	public ModelAndView getConfirmation(HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		HttpSession session = request.getSession();
		long customerId = (Long)session.getAttribute("userid");
		Customer customer = customerService.getCustById(customerId);
		Cart cart = cartService.getCartById(customer.getCart().getId());
		int total=cart.getItems().stream().mapToInt(obj -> obj.getQuantity() * obj.getItem().getPrice()).sum();
		
		Cart view=new Cart();
		List<CartItems> list=new ArrayList<>();
		for (CartItems cartItems : cart.getItems()) {
			list.add(cartItems);
		}
		view.setId(cart.getId());
		view.setItems(list);
		 
		Order order=new Order();
		Cart orderCart=new Cart();
		cart.setItems(cart.getItems());
		order.setCart(orderCart);
		order.setDiscount(0);
		order.setPaymentType("CASH");
		order.setTotalAmount(total);
		Order newOrder=orderService.save(order);
		
		model.addObject("orderid",newOrder.getId());
		model.addObject("cart",view);
		model.addObject("totalCart",total);
		model.addObject("customer",customer);
		model.setViewName("confirmation");
		
		cart.setItems(new ArrayList<>());
		cartService.save(cart);
		
		return model;
	}
}
