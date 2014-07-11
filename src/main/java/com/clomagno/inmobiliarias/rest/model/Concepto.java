package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Concepto implements Serializable {

	private static final long serialVersionUID = 1L;

	public Concepto() {
	}

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private long idConcepto;
	private String valorDefecto;
	private String descripcion;
	private String nombre;

	public long getIdConcepto() {
		return idConcepto;
	}

	public void setIdConcepto(long id) {
		this.idConcepto = id;
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
	public Long getId() {
		return getIdConcepto();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String param) {
		this.nombre = param;
	}
}