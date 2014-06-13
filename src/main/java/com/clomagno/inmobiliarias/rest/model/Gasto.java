package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.clomagno.inmobiliarias.rest.model.Consorcio;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Gasto implements Serializable {

	private static final long serialVersionUID = 1L;

	public Gasto() {
	}

	@Id
	private long idGasto;
	private String monto;
	private String comprobante;
	private String comentarios;
	@ManyToOne
	private TipoGasto tipoGasto;
	@ManyToOne
	private Consorcio consorcio;
	public long getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(long id) {
		this.idGasto = id;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String param) {
		this.monto = param;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String param) {
		this.comprobante = param;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String param) {
		this.comentarios = param;
	}

	public TipoGasto getTipoGasto() {
	    return tipoGasto;
	}

	public void setTipoGasto(TipoGasto param) {
	    this.tipoGasto = param;
	}

	public Consorcio getConsorcio() {
	    return consorcio;
	}

	public void setConsorcio(Consorcio param) {
	    this.consorcio = param;
	}
	
	@JsonProperty("id")
	public Long getId(){
		return getIdGasto();
	}

}