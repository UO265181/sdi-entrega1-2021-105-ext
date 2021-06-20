package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.repositories.SaleRepository;

@Service
public class SaleService {
	

	
	@Autowired
	private SaleRepository saleRepository;
	
	public void addOferta(Sale oferta) {
		saleRepository.save(oferta);
	}
	
	public void deleteOferta(Long id) {
		saleRepository.deleteById(id);
	}
	
	public List<Sale> getOfertas() {
		List<Sale> ofertas = new ArrayList<Sale>();
		saleRepository.findAll().forEach(ofertas::add);;
		return ofertas;
	}

	public List<Sale> getOfertasByTitle(String title) {
		List<Sale> ofertas = new ArrayList<Sale>();
		title='%'+title+'%';
		saleRepository.findAllByTitle(title).forEach(ofertas::add);
		return ofertas;
	}


	public Sale getOferta(Long id) {
		return saleRepository.findById(id).get();
	}

	public void update(Sale sale) {
		saleRepository.save(sale);
	}


	public Page<Sale> getOfertasByTitle(Pageable pageable, String title) {
		Page<Sale> ofertas = new PageImpl<Sale>(new LinkedList<Sale>());
		title='%'+title+'%';
		ofertas = saleRepository.findAllByTitle(pageable,title);
		return ofertas;
	}

	public Page<Sale> getOfertas(Pageable pageable) {
		Page<Sale> ofertas = new PageImpl<Sale>(new LinkedList<Sale>());
		ofertas=saleRepository.findAll(pageable);
		return ofertas;
	}

	public Object getOfertasByOwner(User user) {
		List<Sale> ofertas = new ArrayList<Sale>();
		saleRepository.findAllByUser(user.getId()).forEach(ofertas::add);;
		return ofertas;
	}
	
	public List<Sale> getOfertasByBuyer(User buyer) {
		List<Sale> ofertas = new ArrayList<Sale>();
		saleRepository.findAllByBuyer(buyer.getId()).forEach(ofertas::add);;
		return ofertas;
	}


}
