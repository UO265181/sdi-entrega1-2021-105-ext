package com.uniovi.controllers;


import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@Autowired
	private HttpSession httpSession;
	
	/**
	 * Peticion GET del formulario de añadir oferta
	 * @param model parametro en el que se construye la oferta
	 * @return sale/add vista del formulario de añadir oferta
	 */
	@RequestMapping(value = "/sale/add", method = RequestMethod.GET)
	public String getSales(Model model) {
		model.addAttribute("sale", new Sale());
		return "sale/add";
	}
	
	/**
	 * Peticion POST del formulario de añadir oferta
	 * @param sale oferta a añadir
	 * @param errors errores de validacion
	 * @return sale/add si la oferta tiene errores de validacion, sale/list si se consigue añadir
	 */
	@RequestMapping(value = "/sale/add", method = RequestMethod.POST)
	public String setSales(@Validated Sale sale, BindingResult errors) {
		
		//Validación
		saleValidator.validate(sale, errors);
		if (errors.hasErrors()) {
			return "sale/add";
		}
		
		//Se asocia la oferta con el usuario que la crea
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userService.getUserByEmail(email);
		sale.setUser(user);
		
		//Se añade la oferta
		saleService.addOferta(sale);
		
		return "redirect:/sale/list";
	}
	
	/**
	 * Peticion de la lista de ofertas creadas
	 * @param model contiene las ofertas creadas
	 * @return sale/list la vista con las ofertas
	 */
	@RequestMapping("/sale/list")
	public String getList(Model model) {
		
		//Se obtiene el usuario en sesion
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userService.getUserByEmail(email);
		
		//Se obtienen las ofertas del usuario
		model.addAttribute("sales", saleService.getOfertasByOwner(user));
		
		return "sale/list";
	}
	
	/**
	 * Peticion de eliminacion de una oferta
	 * @param id de la oferta a eliminar
	 * @return sale/list la vista de las ofertas creadas
	 */
	@RequestMapping("/sale/delete/{id}")
	public String deleteSales(@PathVariable Long id) {
		
		//Se obtiene el usuario en sesion
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		String saleEmail=saleService.getOferta(id).getUser().getEmail();
		
		//Se comprueba que el usuario es dueño de la oferta que quiere eliminar
		if(saleEmail.equals(email)) {
			//Se elimina la oferta
			saleService.deleteOferta(id);
		}
		return "redirect:/sale/list";
	}
	
	/**
	 * Peticion GET de la busqueda de ofertas
	 * @param model contiene las ofertas buscadas
	 * @param pageable sistema de pagincacion
	 * @param titleInputSearch la busqueda
	 * @param noMoney booleano que informa de que la compra no se ha podido efectuar
	 * @return sale/search la vista con las ofertas buscadas
	 */
	@RequestMapping(value = "/sale/search", method = RequestMethod.GET)
	public String listOferta(Model model, Pageable pageable, @RequestParam(required = false) String titleInputSearch,
			@RequestParam(required = false) boolean noMoney) {
		
		Page<Sale> ofertas= new PageImpl<Sale>(new LinkedList<Sale>());

		//Si la busqueda es "vacia", se devuelven todas las ofertas del sistema 
		if (titleInputSearch != null && !titleInputSearch.isEmpty()) {
			ofertas = saleService.getOfertasByTitle(pageable, titleInputSearch);
			model.addAttribute("titleSearch", titleInputSearch);
		} else {
			ofertas = saleService.getOfertas(pageable);
			model.addAttribute("titleSearch", "");
		}
		model.addAttribute("sales", ofertas.getContent());
		model.addAttribute("page", ofertas);
		model.addAttribute("noMoney", noMoney);
		return "sale/search";
	}
	
	/**
	 * Peticion de compra de una oferta
	 * @param id de la oferta a comprar
	 * @return sale/search la vista con todas las ofertas
	 */
	@RequestMapping(value = "/sale/buy/{id}")
	public String buyOferta(Model model, @PathVariable Long id) {

		//Se obtiene el usuario en sesion
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUserByEmail(email);

		//Se intenta realizar la compra
		boolean noMoney = !userService.buyOferta(activeUser, saleService.getOferta(id));
		
		//Si usuario consigue efectuar la compra se actualiza su sesion (para actualizar en la vista su saldo)
		if(!noMoney) {
			httpSession.setAttribute("user", userService.getUserByEmail(auth.getName()));
		}
				
		return "redirect:/sale/search?noMoney=" + noMoney;
	}
	
	/**
	 * Peticion de la lista de ofertas adquiridas
	 * @return sale/purchased la vista con las ofertas adquiridas del usuario en sesion
	 */
	@RequestMapping("/sale/purchased")
	public String getPurchased(Model model) {
		//Se obtiene el usuario en sesion
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = userService.getUserByEmail(email);

		//Se obtienen sus ofertas adquiridas
		model.addAttribute("sales",saleService.getOfertasByBuyer(activeUser));
		
		return "sale/purchased";
	}

}
