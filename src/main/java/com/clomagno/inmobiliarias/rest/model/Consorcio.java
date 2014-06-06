package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.clomagno.inmobiliarias.rest.model.Gasto;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Consorcio implements Serializable {

	private static final long serialVersionUID = 1L;

	public Consorcio() {
	}

	@Id
	private long idConsorcio;
	@OneToMany(mappedBy = "consorcio")
	private Collection<Propiedad> propiedad;
	private String nombre;
	@OneToMany(mappedBy = "consorcio")
	private Collection<Gasto> gasto;
	
	public long getIdConsorcio() {
		return idConsorcio;
	}

	public void setIdConsorcio(long id) {
		this.idConsorcio = id;
	}

	public Collection<Propiedad> getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Collection<Propiedad> param) {
		this.propiedad = param;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String param) {
		this.nombre = param;
	}

	public Collection<Gasto> getGasto() {
	    return gasto;
	}

	public void setGasto(Collection<Gasto> param) {
	    this.gasto = param;
	}

}