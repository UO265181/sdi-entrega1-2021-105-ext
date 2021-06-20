package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.services.SaleService;
import com.uniovi.services.UserService;
import com.uniovi.validators.SaleValidator;

@Controller
public class SaleController {
	
	@Autowired
	private SaleService saleService;

	@Autowired
	private UserService userService;

	@Autowired
	private SaleValidator saleValidator;
	
	
	@RequestMapping(value = "/sale/add", method = RequestMethod.GET)
	public String getSales(Model model) {
		model.addAttribute("sale", new Sale());
		return "sale/add";
	}
	
	
	@RequestMapping(value = "/sale/add", method = RequestMethod.POST)
	public String setSales(@Validated Sale sale, BindingResult errors) {
		
		
		saleValidator.validate(sale, errors);
		if (errors.hasErrors()) {
			return "sale/add";
		}
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userService.getUserByEmail(email);
		sale.setUser(user);
		
		
		saleService.addOferta(sale);
		return "redirect:/sale/list";
	}
	
	
	@RequestMapping("/sale/list")
	public String getList(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userService.getUserByEmail(email);
		model.addAttribute("sales", saleService.getOfertasByOwner(user));
		
		return "sale/list";
	}

}
