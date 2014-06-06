package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.clomagno.inmobiliarias.rest.model.Gasto;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Propiedad implements Serializable {

	private static final long serialVersionUID = 1L;

	public Propiedad() {
	}

	@Id
	private long idPropiedad;
	@ManyToOne
	private Consorcio consorcio;
	private String nombre;
	private String direccion;
	@ManyToOne
	private Propietario propietario;
	private String porcentajeGastosComunes;
	@ManyToOne
	private Gasto gasto;
	
	public long getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(long id) {
		this.idPropiedad = id;
	}

	public Consorcio getConsorcio() {
		return consorcio;
	}

	public void setConsorcio(Consorcio param) {
		this.consorcio = param;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String param) {
		this.nombre = param;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String param) {
		this.direccion = param;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario param) {
		this.propietario = param;
	}

	public String getPorcentajeGastosComunes() {
		return porcentajeGastosComunes;
	}

	public void setPorcentajeGastosComunes(String param) {
		this.porcentajeGastosComunes = param;
	}

	public Gasto getGasto() {
	    return gasto;
	}

	public void setGasto(Gasto param) {
	    this.gasto = param;
	}

}