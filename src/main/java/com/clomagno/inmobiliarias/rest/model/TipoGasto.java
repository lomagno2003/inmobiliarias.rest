package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class TipoGasto implements Serializable {

	private static final long serialVersionUID = 1L;

	public TipoGasto() {
	}

	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	private long idTipoGasto;
	private String valorDefecto;
	private String descripcion;
	public long getIdTipoGasto() {
		return idTipoGasto;
	}

	public void setIdTipoGasto(long id) {
		this.idTipoGasto = id;
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
	public Long getId(){
		return getIdTipoGasto();
	}
}