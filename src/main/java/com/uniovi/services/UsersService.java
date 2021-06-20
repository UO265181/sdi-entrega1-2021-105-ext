package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private OfertasService ofertasService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RolesService rolesService;
	

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}
	



	public void addUser(User user) {
		user.setRole(rolesService.getRoles()[0]);
		user.setDinero(100.0);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void deleteUsers(List<User> users) {
		for(User user:users) {
			if(user.isSelected()) {
				ofertasService.deleteOfertaByUserId(user.getId());
				usersRepository.delete(user);
			}
		}
	}

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

	@Transactional
	public boolean buyOferta(User user, Oferta oferta) {
		if(user.getDinero()>=oferta.getCantidad()) {
			usersRepository.updateDinero(user.getId(), user.getDinero()-oferta.getCantidad());
			ofertasService.updateComprada(oferta.getId(),user.getId(), true);
			return true;
		}
		return false;
	}
}
