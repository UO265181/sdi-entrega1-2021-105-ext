package com.uniovi.services;


import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;



@Service
public class InsertSampleDataService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@SuppressWarnings("serial")
	@PostConstruct
	public void init() {
		User user1 = new User("Lucas", "Hernández", "Lolas132@yahoo.es");
		user1.setPassword("123456");
		user1.setRole(roleService.getRoles()[0]);
		User user2 = new User("María", "Luisa", "M4ras@gmail.com");
		user2.setPassword("123456");
		user2.setRole(roleService.getRoles()[0]);
		User user3 = new User("Marta", "Piedad", "12_asMar@gmail.com");
		user3.setPassword("123456");
		user3.setRole(roleService.getRoles()[0]);
		User user4 = new User("admin", "admin", "admin@email.com");
		user4.setPassword("admin");
		user4.setRole(roleService.getRoles()[1]);
		
		User user5 = new User("Jaime", "Lopez", "Jaime@gmail.com");
		user5.setPassword("123456");
		user5.setRole(roleService.getRoles()[0]);
		User user6 = new User("Juan", "Fernandez", "Juan@gmail.com");
		user6.setPassword("123456");
		user6.setRole(roleService.getRoles()[0]);
		User user7 = new User("Pepe", "Nacho", "Pepe@gmail.com");
		user7.setPassword("123456");
		user7.setRole(roleService.getRoles()[0]);
		User user8 = new User("Ramona", "Jimena", "Ramona@gmail.com");
		user8.setPassword("123456");
		user8.setRole(roleService.getRoles()[0]);
		User user9 = new User("Estrella", "Gutierrez", "Estrella@gmail.com");
		user9.setPassword("123456");
		user9.setRole(roleService.getRoles()[0]);


		
		
		Set<Sale> user1Ofertas = new HashSet<Sale>() {
			{
				add(new Sale("Patinete","4 ruedas y un manillar",36.99,user1));
			}
		};
		user1.setOfertas(user1Ofertas);
		
		Set<Sale> user2Ofertas = new HashSet<Sale>() {
			{
				add(new Sale("Libro de Sintaxis","Nivel 4ºESO",22.11,user2));
			}
		};
		user2.setOfertas(user2Ofertas);
		
		Set<Sale> user3Ofertas = new HashSet<Sale>() {
			{
				add(new Sale("Dishonored","Para xbox360, precintado",30.0,user3));
				add(new Sale("Deus Ex","Original (2000)",9.99,user3));
				add(new Sale("Dark Souls 3","Edición especial",88.23,user3));
				add(new Sale("MGS3 Snake Eater","Para xbox360",20.0,user3));
				add(new Sale("Monopatín","2 ruedas. El manillar está roto",21.99,user3));
				add(new Sale("Skate","Nuevo",80,user3));
				add(new Sale("El Quijote","Edición infantil",12.59,user3));
			}
		};
		user3.setOfertas(user3Ofertas);
		
		
		
		
		
		userService.addUser(user1);
		userService.addUser(user2);
		userService.addUser(user3);
		userService.addUser(user4);
		userService.addUser(user5);
		userService.addUser(user6);
		userService.addUser(user7);
		userService.addUser(user8);
		userService.addUser(user9);
		
		
		
	}
}

