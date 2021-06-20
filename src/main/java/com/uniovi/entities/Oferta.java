package com.uniovi.entities;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Oferta {

	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private String detalle;
	private Date fecha;
	private double cantidad;
	private boolean comprada;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; //Vendedor
	
	@ManyToOne
	@JoinColumn(name = "user_id_buyer")
	private User userBuyer; //Comprador
	


	public Oferta(String titulo, String detalle, double cantidad, User user) {
		super();
		this.titulo = titulo;
		this.detalle = detalle;
		this.cantidad = cantidad;
		this.user = user;
		this.fecha = new Date(System.currentTimeMillis());
		this.comprada = false;
	}
	
	public Oferta(Long id, String titulo, String detalle, double cantidad) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.detalle = detalle;
		this.cantidad = cantidad;
		this.fecha = new Date(System.currentTimeMillis());
		this.comprada = false;
	}

	public Oferta() {
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

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
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
		return "Oferta [id=" + id + ", titulo=" + titulo + ", detalle=" + detalle + ", fecha=" + fecha + ", cantidad="
				+ cantidad + ", comprada=" + comprada + "]";
	}
	
	

}
