package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Gasto implements Serializable,IContabilizable {

	private static final long serialVersionUID = 1L;

	public Gasto() {
	}

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private long idGasto;
	
	@Column(nullable=false)
	private Double monto;
	
	private String comprobante;
	
	private String comentarios;
	
	@ManyToOne
	private Concepto concepto;
	
	@Column(nullable=false)
	private Date fecha;

	public long getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(long id) {
		this.idGasto = id;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double param) {
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

	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto param) {
		this.concepto = param;
	}

	@JsonProperty("id")
	public Long getId() {
		return getIdGasto();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date param) {
		this.fecha = param;
	}

}