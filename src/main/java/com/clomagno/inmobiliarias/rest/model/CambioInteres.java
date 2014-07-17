package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Table;

@Entity
@Table(name = "CambioInteres")
public class CambioInteres implements Serializable, IUbicableEnElTiempo {

	private static final long serialVersionUID = 1L;

	public CambioInteres() {
	}

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private long idCambioInteres;
	private Double interes;
	private Date fecha;

	public long getIdCambioInteres() {
		return idCambioInteres;
	}

	public void setIdCambioInteres(long idInteres) {
		this.idCambioInteres = idInteres;
	}

	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}