package com.uniovi.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String role;
	@Column(unique = true)
	private String email;
	private String nombre;
	private String apellidos;

	private String password;
	@Transient
	private String password2;

	@Transient
	private boolean selected;

	private double dinero;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Sale> ofertas;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Sale> ofertasPurchased;

	public User(String nombre, String apellidos, String email) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.dinero = 100.0;
		this.selected = false;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public Set<Sale> getOfertas() {
		return ofertas;
	}

	public void setOfertas(Set<Sale> ofertas) {
		this.ofertas = ofertas;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}



}
