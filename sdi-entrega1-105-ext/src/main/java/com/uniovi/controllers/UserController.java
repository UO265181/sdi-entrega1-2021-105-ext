package com.uniovi.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.RoleService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UserService;
import com.uniovi.validators.LogInValidator;
import com.uniovi.validators.SignUpValidator;

@Controller
public class UserController {

	@Autowired
	private UserService usersService;

	@Autowired
	private SignUpValidator signUpValidator;

	@Autowired
	private LogInValidator logInValidator;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private HttpSession httpSession;

	
	/**
	 * Peticion GET del formulario de registro
	 * @param model contiene el usuario a registrar
	 * @return signup la vista del formulario
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	/**
	 * Peticion POST del formulario de registro
	 * @param user el usuario a registrar
	 * @param result contiene los errores de la validacion
	 * @return signup si no se ha podido registrar, home si lo ha conseguido
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {

		//Validación
		signUpValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		
		//Se le asigna 100€ y el rol de usuario
		user.setRole(roleService.getRoles()[0]);
		user.setDinero(100.0);
		usersService.addUser(user);
		
		//Se logea y se guarda en sesion
		securityService.autoLogin(user.getEmail(), user.getPassword2());
		httpSession.setAttribute("user", user);
		return "redirect:home";
	}

	/**
	 * Peticion GET del formulario de inicio de sesion
	 * @param model contiene el usuario que intenta iniciar sesion
	 * @return login la vista con el formulario
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	/**
	 * Peticion GET de "myLogin" por si se accede directamente por URL
	 * @return login que mostrará la vista con el formulario
	 */
	@RequestMapping(value = "/myLogin", method = RequestMethod.GET)
	public String customLogin() {
		return "redirect:/login";
	}

	/**
	 * Peticion POST del login ("myLogin" para poder realizar mis propias validaciones)
	 * @param user el usuario que intenta iniciar sesion
	 * @param result contiene los errores de validacion
	 * @return login si no se consigue logear, home si lo consigue
	 */
	@RequestMapping(value = "/myLogin", method = RequestMethod.POST)
	public String customLogin(User user, BindingResult result) {

		//Validación
		logInValidator.validate(user, result);
		if (result.hasErrors()) {
			return "login";
		}

		//Se logea y se añade a la sesion
		httpSession.setAttribute("user", usersService.getUserByEmail(user.getEmail()));
		securityService.autoLogin(user.getEmail(), user.getPassword());
		return "redirect:/home";

	}

	/**
	 * Peticion de la lista de usuarios del sistema
	 * @param model contiene los usuarios del sistema
	 * @return user/list la vista de los usuarios
	 */
	@RequestMapping("/user/list")
	public String getList(Model model) {
		//Se obtienen todos los usuarios no administradores
		model.addAttribute("usersList", usersService.getNotAdminUsers());
		return "user/list";
	}

	/**
	 * Peticion POST de eliminar usuario/s
	 * @param checkboxValue contienen los emails de los usuarios a eliminar
	 * @return user/list la vista con los usuarios del sistema
	 */
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String deleteUser(@RequestParam(value = "checkboxUser", required = false) String checkboxValue[]) {
		
		if(checkboxValue!=null) {
			//Se eliminan los usuarios marcados
			usersService.deleteUsers(checkboxValue);
		}

		return "redirect:/user/list";
	}

}
