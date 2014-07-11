package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Pago implements Serializable, IContabilizable {

	private static final long serialVersionUID = 1L;

	public Pago() {
	}

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private long idPago;
	private Double monto;
	private String comentario;
	@ManyToOne
	private UnidadFuncional unidadFuncional;
	private Boolean depositoBancario;
	private Date fecha;

	public long getIdPago() {
		return idPago;
	}

	public void setIdPago(long id) {
		this.idPago = id;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double param) {
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

	public Boolean getDepositoBancario() {
		return depositoBancario;
	}

	public void setDepositoBancario(Boolean param) {
		this.depositoBancario = param;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date param) {
		this.fecha = param;
	}
}