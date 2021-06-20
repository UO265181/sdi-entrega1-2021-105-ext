package com.uniovi.repositories;




import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.uniovi.entities.Sale;
import com.uniovi.entities.User;

public interface SaleRepository extends CrudRepository<Sale, Long> {
	
	@Query("SELECT o FROM Sale o WHERE o.user = ?1 ORDER BY o.id ASC ")
	Page<Sale> findAllByUser(Pageable pageable, User user);
	
	@Query("SELECT o FROM Sale o WHERE LOWER(o.titulo) LIKE LOWER(?1)")
	List<Sale> findAllByTitle(String title);




	@Query("SELECT o FROM Sale o WHERE o.userBuyer.id=?1")
	List<Sale> findAllByBuyer(Long id);
	
	@Query("SELECT o FROM Sale o WHERE o.user.id=?1")
	List<Sale> findAllByUser(Long id);

	@Query("SELECT o FROM Sale o WHERE LOWER(o.titulo) LIKE LOWER(?1)")
	Page<Sale> findAllByTitle(Pageable pageable, String title);

	@Query("SELECT o FROM Sale o ORDER BY o.titulo")
	Page<Sale> findAll(Pageable pageable);


}
