package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Pago implements Serializable {

	private static final long serialVersionUID = 1L;

	public Pago() {
	}

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private long idPago;
	private String monto;
	private String comentario;
	@ManyToOne
	private UnidadFuncional unidadFuncional;
	private String depositoBancario;

	public long getIdPago() {
		return idPago;
	}

	public void setIdPago(long id) {
		this.idPago = id;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String param) {
		this.monto = param;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String param) {
		this.comentario = param;
	}

	@JsonProperty("id")
	public Long getId() {
		return getIdPago();
	}

	public UnidadFuncional getUnidadFuncional() {
		return unidadFuncional;
	}

	public void setUnidadFuncional(UnidadFuncional param) {
		this.unidadFuncional = param;
	}

	public String getDepositoBancario() {
		return depositoBancario;
	}

	public void setDepositoBancario(String param) {
		this.depositoBancario = param;
	}
}