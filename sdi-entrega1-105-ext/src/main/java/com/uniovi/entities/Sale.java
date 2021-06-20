package com.uniovi.entities;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Sale {

	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String detalle;
	private Date fecha;
	private double precio;
	private boolean comprada;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; //Vendedor
	
	@ManyToOne
	@JoinColumn(name = "user_id_buyer")
	private User userBuyer; //Comprador
	


	public Sale(String titulo, String detalle, double precio, User user) {
		super();
		this.titulo = titulo;
		this.detalle = detalle;
		this.precio = precio;
		this.user = user;
		this.fecha = new Date(System.currentTimeMillis());
		this.comprada = false;
	}
	
	public Sale(Long id, String titulo, String detalle, double precio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.detalle = detalle;
		this.precio = precio;
		this.fecha = new Date(System.currentTimeMillis());
		this.comprada = false;
	}

	public Sale() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isComprada() {
		return comprada;
	}

	public void setComprada(boolean comprada) {
		this.comprada = comprada;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Oferta [id=" + id + ", titulo=" + titulo + ", detalle=" + detalle + ", fecha=" + fecha + ", precio="
				+ precio + ", comprada=" + comprada + "]";
	}
	
	

}
