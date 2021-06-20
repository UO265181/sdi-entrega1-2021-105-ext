package com.uniovi.repositories;




import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

public interface SaleRepository extends CrudRepository<Sale, Long> {
	
	@Query("SELECT o FROM Sale o WHERE o.user = ?1 ORDER BY o.id ASC ")
	Page<Sale> findAllByUser(Pageable pageable, User user);
	
	@Query("SELECT o FROM Sale o WHERE LOWER(o.titulo) LIKE LOWER(?1)")
	List<Sale> findAllByTitle(String title);

	@Transactional
	@Modifying
	@Query("UPDATE Sale SET comprada=?3, user_id_buyer=?2 WHERE id=?1")
	void updateComprada(Long id, Long idUser, boolean b);

	
	@Query("SELECT o FROM Sale o WHERE o.userBuyer.id=?1")
	List<Sale> findAllByBuyer(Long id);
	
	@Query("SELECT o FROM Sale o WHERE o.user.id=?1")
	List<Sale> findAllByUser(Long id);

	@Transactional
	@Modifying
	@Query("DELETE FROM Sale WHERE user_id=?1")
	void deleteOfertaByUserId(long id);

	@Query("SELECT o FROM Sale o WHERE LOWER(o.titulo) LIKE LOWER(?1)")
	Page<Sale> findAllByTitle(Pageable pageable, String title);

	Page<Sale> findAll(Pageable pageable);

}
