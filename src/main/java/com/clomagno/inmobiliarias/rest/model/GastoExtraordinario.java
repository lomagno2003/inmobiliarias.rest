package com.clomagno.inmobiliarias.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;
import javax.persistence.ManyToOne;

@Entity
public class GastoExtraordinario extends Gasto implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManyToOne
	private UnidadFuncional unidadFuncional;
	public UnidadFuncional getUnidadFuncional() {
	    return unidadFuncional;
	}
	public void setUnidadFuncional(UnidadFuncional param) {
	    this.unidadFuncional = param;
	}
}