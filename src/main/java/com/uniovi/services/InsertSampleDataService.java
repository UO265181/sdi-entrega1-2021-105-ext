package com.uniovi.services;


import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;



@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;

	@SuppressWarnings("serial")
	@PostConstruct
	public void init() {
		User user1 = new User("Lucas", "Hernández", "Lolas132@yahoo.es");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("María", "Luisa", "M4ras@gmail.com");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("Marta", "Piedad", "12_asMar@gmail.com");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("admin", "admin", "admin@email.com");
		user4.setPassword("admin");
		user4.setRole(rolesService.getRoles()[1]);


		
		
		Set<Oferta> user1Ofertas = new HashSet<Oferta>() {
			{
				add(new Oferta("Patinete","4 ruedas y un manillar",36.99,user1));
				add(new Oferta("Monopatín","2 ruedas. El manillar está roto",21.99,user1));
			}
		};
		user1.setOfertas(user1Ofertas);
		
		Set<Oferta> user2Ofertas = new HashSet<Oferta>() {
			{
				add(new Oferta("El Quijote","Edición infantil",12.59,user2));
				add(new Oferta("Libro de Sintaxis","Nivel 4ºESO",22.11,user2));
			}
		};
		user2.setOfertas(user2Ofertas);
		
		Set<Oferta> user3Ofertas = new HashSet<Oferta>() {
			{
				add(new Oferta("Dishonored","Para xbox360, precintado",30.0,user3));
				add(new Oferta("Deus Ex","Original (2000)",9.99,user3));
				add(new Oferta("Dark Souls 3","Edición especial",88.23,user3));
				add(new Oferta("MGS3 Snake Eater","Para xbox360",20.0,user3));
			}
		};
		user3.setOfertas(user3Ofertas);
		
		
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

