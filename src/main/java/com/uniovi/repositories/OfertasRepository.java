package com.uniovi.repositories;




import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;

public interface OfertasRepository extends CrudRepository<Oferta, Long> {
	
	@Query("SELECT o FROM Oferta o WHERE o.user = ?1 ORDER BY o.id ASC ")
	Page<Oferta> findAllByUser(Pageable pageable, User user);
	
	@Query("SELECT o FROM Oferta o WHERE LOWER(o.titulo) LIKE LOWER(?1)")
	List<Oferta> findAllByTitle(String title);

	@Transactional
	@Modifying
	@Query("UPDATE Oferta SET comprada=?3, user_id_buyer=?2 WHERE id=?1")
	void updateComprada(Long id, Long idUser, boolean b);

	
	@Query("SELECT o FROM Oferta o WHERE o.userBuyer.id=?1")
	List<Oferta> findAllByBuyer(Long id);
	
	List<Oferta> findAllByUser(User user);

	@Transactional
	@Modifying
	@Query("DELETE FROM Oferta WHERE user_id=?1")
	void deleteOfertaByUserId(long id);

	@Query("SELECT o FROM Oferta o WHERE LOWER(o.titulo) LIKE LOWER(?1)")
	Page<Oferta> findAllByTitle(Pageable pageable, String title);

	Page<Oferta> findAll(Pageable pageable);

}
