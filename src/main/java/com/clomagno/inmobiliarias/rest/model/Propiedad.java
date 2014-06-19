package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.clomagno.inmobiliarias.rest.model.Gasto;
import java.util.Collection;
import javax.persistence.OneToMany;

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
	private Long porcentajeGastosComunes;
	@OneToMany(mappedBy = "propiedad")
	private Collection<Gasto> gasto;
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

	public Long getPorcentajeGastosComunes() {
		return porcentajeGastosComunes;
	}

	public void setPorcentajeGastosComunes(Long param) {
		this.porcentajeGastosComunes = param;
	}

	@JsonProperty("id")
	public Long getId(){
		return getIdPropiedad();
	}

	public Collection<Gasto> getGasto() {
	    return gasto;
	}

	public void setGasto(Collection<Gasto> param) {
	    this.gasto = param;
	}
}