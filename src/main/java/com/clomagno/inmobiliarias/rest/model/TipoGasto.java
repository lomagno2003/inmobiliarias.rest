package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class TipoGasto implements Serializable {

	private static final long serialVersionUID = 1L;

	public TipoGasto() {
	}

	@Id
	private long id;
	private String valorDefecto;
	private String descripcion;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValorDefecto() {
		return valorDefecto;
	}

	public void setValorDefecto(String param) {
		this.valorDefecto = param;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String param) {
		this.descripcion = param;
	}

	@JsonProperty("id")
	public Long getId2(){
		return getId();
	}
}