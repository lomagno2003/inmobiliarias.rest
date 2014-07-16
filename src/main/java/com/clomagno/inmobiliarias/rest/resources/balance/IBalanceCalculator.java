package com.clomagno.inmobiliarias.rest.resources.balance;

import java.util.Date;

import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;

public interface IBalanceCalculator {
	public Double getBalance(UnidadFuncional unidadFuncional, Date fecha);
}
