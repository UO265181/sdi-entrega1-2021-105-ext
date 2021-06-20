package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository usersRepository;
	
	@Autowired
	private SaleService saleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleService rolesService;
	

	/**
	 * Devuelve todos los usuarios
	 */
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	


	/**
	 * Añade un usuario dado
	 */
	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	/**
	 * Devuelve un usuario segun un email dado
	 */
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	/**
	 * Elimina los usuarios de los emails dados
	 */
	public void deleteUsers(String[] emails) {
		for(String email:emails) {
			User user = usersRepository.findByEmail(email);
			for(Sale sale:user.getOfertas()) {
				saleService.deleteOferta(sale.getId());
			}
			usersRepository.delete(user);
		}
	}

	/**
	 * Devuelve todos los usuarios no administradores
	 */
	public List<User> getNotAdminUsers() {
		List<User> users = getUsers();
		List<User> notAdminUsers = new ArrayList<User>(); 
		for(User user:users) {
			if(!user.getRole().equals(rolesService.getRoles()[1])) {
				notAdminUsers.add(user);
			}
		}
		return notAdminUsers;
	}

	/**
	 * Intenta realizar una compra dado un usuario y una oferta
	 * @param user
	 * @param oferta
	 * @return true si se efectua con exito, false si el usuario no tiene dinero suficiente
	 */
	@Transactional
	public boolean buyOferta(User user, Sale oferta) {
		if(user.getDinero()>=oferta.getPrecio()) {
			double nuevoDinero =user.getDinero()-oferta.getPrecio();
			user.setDinero(Math.round(nuevoDinero*100.0)/100.0);
			usersRepository.save(user);
			oferta.setComprada(true);
			oferta.setUserBuyer(user);
			saleService.update(oferta);
			return true;
		}
		return false;
	}



}
