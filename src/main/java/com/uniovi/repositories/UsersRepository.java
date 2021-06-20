package com.uniovi.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);

	@Transactional
	@Modifying
	@Query("UPDATE User SET dinero=?2 WHERE id=?1")
	void updateDinero(Long id,double dinero);

}
