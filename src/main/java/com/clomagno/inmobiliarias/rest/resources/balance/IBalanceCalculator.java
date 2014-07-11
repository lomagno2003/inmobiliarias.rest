package com.clomagno.inmobiliarias.rest.resources.balance;

import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;

public interface IBalanceCalculator {
	public Double getBalance(UnidadFuncional unidadFuncional, Integer mes, Integer año);
}
