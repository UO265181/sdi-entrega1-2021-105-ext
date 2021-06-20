package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Oferta;
import com.uniovi.entities.User;
import com.uniovi.repositories.OfertasRepository;

@Service
public class OfertasService {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private OfertasRepository ofertasRepository;
	
	public void addOferta(Oferta oferta) {
		oferta.setId(ofertasRepository.count() + 1);
		ofertasRepository.save(oferta);
	}
	
	public void deleteOferta(Long id) {
		ofertasRepository.deleteById(id);
	}
	
	public List<Oferta> getOfertas() {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertasRepository.findAll().forEach(ofertas::add);;
		return ofertas;
	}

	public List<Oferta> getOfertasByTitle(String title) {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		title='%'+title+'%';
		ofertasRepository.findAllByTitle(title).forEach(ofertas::add);
		return ofertas;
	}

	@SuppressWarnings("unchecked")
	public Oferta getOferta(Long id) {
		Set<Oferta> consultedList = (Set<Oferta>) httpSession.getAttribute("consultedList");
		if (consultedList == null) {
			consultedList = new HashSet<Oferta>();
		}
		Oferta obtainedoferta = ofertasRepository.findById(id).get();
		consultedList.add(obtainedoferta);
		httpSession.setAttribute("consultedList", consultedList);
		return obtainedoferta;
	}

	public void updateComprada(Long id, Long idUser, boolean b) {
		ofertasRepository.updateComprada(id,idUser,b);
	}

	public List<Oferta> getOfertasByBuyer(User buyer) {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertasRepository.findAllByBuyer(buyer.getId()).forEach(ofertas::add);;
		return ofertas;
	}

	public void deleteOfertaByUserId(long id) {
		ofertasRepository.deleteOfertaByUserId(id);
	}

	public Page<Oferta> getOfertasByTitle(Pageable pageable, String title) {
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		title='%'+title+'%';
		ofertas = ofertasRepository.findAllByTitle(pageable,title);
		return ofertas;
	}

	public Page<Oferta> getOfertas(Pageable pageable) {
		Page<Oferta> ofertas = new PageImpl<Oferta>(new LinkedList<Oferta>());
		ofertas=ofertasRepository.findAll(pageable);
		return ofertas;
	}

}
