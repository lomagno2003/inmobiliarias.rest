package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	public Persona() {
	}

	@Id
	private long idPersona;
	private String nombre;
	private String dni;
	private String direccion;
	private String telefonoFijo;
	private String telefonoMovil;
	private String email;
	
	@JsonIgnore(value=false)
	public long getIdPersona() {
		return idPersona;
	}
	
	@JsonIgnore(value=false)
	public void setIdPersona(long id) {
		this.idPersona = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String param) {
		this.nombre = param;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String param) {
		this.dni = param;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String param) {
		this.direccion = param;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String param) {
		this.telefonoFijo = param;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String param) {
		this.telefonoMovil = param;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String param) {
		this.email = param;
	}

}