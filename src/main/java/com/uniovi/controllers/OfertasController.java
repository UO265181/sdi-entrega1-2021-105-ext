package com.uniovi.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.services.OfertasService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddOfertaFormValidator;

@Controller
public class OfertasController {

	@Autowired
	private OfertasService ofertasService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AddOfertaFormValidator addOfertaFormValidator;

	@RequestMapping("/oferta/list")
	public String getList(Model model) {
		model.addAttribute("ofertaList", ofertasService.getOfertas());
		return "oferta/list";
	}

	@RequestMapping(value = "/oferta/add", method = RequestMethod.GET)
	public String setOferta(Model model) {
		model.addAttribute("oferta", new Oferta());
		return "oferta/add";
	}

	@RequestMapping(value = "/oferta/add", method = RequestMethod.POST)
	public String setOferta(@ModelAttribute Oferta oferta, BindingResult result) {
		addOfertaFormValidator.validate(oferta, result);
		if (result.hasErrors()) {
			return "oferta/add";
		}
		ofertasService.addOferta(oferta);
		return "redirect:/oferta/list";
	}

	@RequestMapping(value = "/oferta/add")
	public String getOferta(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "oferta/add";
	}

	@RequestMapping(value = "/oferta/delete/{id}")
	public String deleteOferta(@PathVariable Long id) {
		ofertasService.deleteOferta(id);
		return "redirect:/oferta/list";
	}

	@RequestMapping(value = "/oferta/search", method = RequestMethod.GET)
	public String listOferta(Model model, Pageable pageable, @RequestParam(required = false) String titleInputSearch,
			@RequestParam(required = false) boolean noMoney) {

		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());

		if (titleInputSearch != null && titleInputSearch != "") {
			ofertas = ofertasService.getOfertasByTitle(pageable, titleInputSearch);
			model.addAttribute("titleSearch", titleInputSearch);
		} else {
			ofertas = ofertasService.getOfertas(pageable);
			model.addAttribute("titleSearch", "");
		}
		model.addAttribute("ofertas", ofertas.getContent());
		model.addAttribute("page", ofertas);
		model.addAttribute("noMoney", noMoney);
		return "oferta/search";
	}

	@RequestMapping(value = "/oferta/buy/{id}")
	public String buyOferta(Model model, @PathVariable Long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);

		boolean noMoney = !usersService.buyOferta(activeUser, ofertasService.getOferta(id));
		return "redirect:/oferta/search?noMoney=" + noMoney;
	}

	@RequestMapping(value = "/oferta/listPurchased")
	public String buyOferta(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);

		model.addAttribute("ofertas", ofertasService.getOfertasByBuyer(activeUser));

		return "oferta/listPurchased";
	}

}
