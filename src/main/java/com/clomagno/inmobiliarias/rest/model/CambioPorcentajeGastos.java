package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Table;

@Entity
@Table(name = "CambioPorcentajeGastos")
public class CambioPorcentajeGastos implements Serializable,
		IUbicableEnElTiempo {

	private static final long serialVersionUID = 1L;

	public CambioPorcentajeGastos() {
	}

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private long idCambioPorcentajeGastos;
	private Double porcentajeGasto;
	private Date fecha;

	public long getIdCambioPorcentajeGastos() {
		return idCambioPorcentajeGastos;
	}

	public void setIdCambioPorcentajeGastos(long idInteres) {
		this.idCambioPorcentajeGastos = idInteres;
	}

	public Double getPorcentajeGasto() {
		return porcentajeGasto;
	}

	public void setPorcentajeGasto(Double interes) {
		this.porcentajeGasto = interes;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}