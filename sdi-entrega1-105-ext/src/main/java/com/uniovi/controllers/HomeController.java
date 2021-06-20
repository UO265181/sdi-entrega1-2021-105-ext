package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	/**
	 * Petición del index
	 * @return index
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * Peticion GET del home (inicio)
	 * @return vista home
	 */
	@RequestMapping(value ={"/home"},method =RequestMethod.GET)
	public String home(){
		return "home";
	}
}
