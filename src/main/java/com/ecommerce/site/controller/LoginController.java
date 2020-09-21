package com.ecommerce.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.site.model.Customer;
import com.ecommerce.site.model.Login;
import com.ecommerce.site.service.AccountService;
import com.ecommerce.site.service.CustomerService;

@RestController
public class LoginController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService CustomerService;
	
	@GetMapping(value="/login")
	public ModelAndView signUp(HttpServletRequest request) {
		ModelAndView model= new ModelAndView();
		model.setViewName("login");
		return model;
	}
	
	@PostMapping(value = "/login")
	public ModelAndView loginUser(@ModelAttribute("loginModel") Login login,HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		Customer customer=null;
		if(login.getUsername().contains(".com") &&  login.getUsername().contains("@")) {
			customer=CustomerService.getAccountByEmail(login.getUsername());
		}
		if(customer != null && accountService.validateUser(login)) {
			request.getSession().setAttribute("username", customer.getAccount().getUsername());
			request.getSession().setAttribute("userid", customer.getId());
		}
		else {
			model.setViewName("signin");
			return model;
		}
		model.setViewName("index");
		
		return model;
	}
	
	
	@GetMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView model=new ModelAndView();
		HttpSession session = request.getSession();
		session.removeAttribute("userid");
		session.removeAttribute("username");
		model.setViewName("index");
		return model;
	}
}
