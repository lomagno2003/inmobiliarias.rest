package com.clomagno.inmobiliarias.rest.resources.balance;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clomagno.inmobiliarias.rest.model.IContabilizable;
import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;
import com.clomagno.inmobiliarias.rest.repositories.GastoExtraordinarioRepository;
import com.clomagno.inmobiliarias.rest.repositories.GastoOrdinarioRepository;
import com.clomagno.inmobiliarias.rest.repositories.PagoRepository;

@Component
public class BalanceCalculatorByMonth implements IBalanceCalculator {
	private static final Double INTERESES = 0.15;
	@Autowired
	private PagoRepository pagoRepository;

	@Autowired
	private GastoOrdinarioRepository gastoOrdinarioRepository;

	@Autowired
	private GastoExtraordinarioRepository gastoExtraordinarioRepository;

	public void setPagoRepository(PagoRepository pagoRepository) {
		this.pagoRepository = pagoRepository;
	}

	public void setGastoOrdinarioRepository(
			GastoOrdinarioRepository gastoOrdinarioRepository) {
		this.gastoOrdinarioRepository = gastoOrdinarioRepository;
	}

	public void setGastoExtraordinarioRepository(
			GastoExtraordinarioRepository gastoExtraordinarioRepository) {
		this.gastoExtraordinarioRepository = gastoExtraordinarioRepository;
	}

	@Override
	public Double getBalance(UnidadFuncional unidadFuncional, Integer mes,
			Integer año) {
		if (mes > 1) {
			// Calculate the balance of the past month
			Double balancePasado = getBalance(unidadFuncional, mes - 1, año);

			Double balancePasadoConIntereses = balancePasado;
			if (balancePasado < 0) {
				// The UnidadFuncional is in doubt, so interests are applied to
				// the past Balance
				balancePasadoConIntereses *= 1.0 + INTERESES;
			}

			Date inicioMes = getFirstDateOfMonth(mes,año);
			Date finMes = getLastDateOfMonth(mes,año);

			// Calculate the sum of Pagos
			Double pagos = calcularSumatoria(new LinkedList<IContabilizable>(
					pagoRepository.findByUnidadFuncionalAndFechaBetween(
							unidadFuncional, inicioMes, finMes)));

			// Calculate the sum of GastosOrdinarios
			Double gastosOrdinarios = calcularSumatoria(new LinkedList<IContabilizable>(
					gastoOrdinarioRepository.findByConsorcioAndFechaBetween(
							unidadFuncional.getConsorcio(), inicioMes, finMes)));

			// Calculate the sum of GastosExtraordinarios
			Double gastosExtraordinarios = calcularSumatoria(new LinkedList<IContabilizable>(
					gastoExtraordinarioRepository
							.findByUnidadFuncionalAndFechaBetween(
									unidadFuncional, inicioMes, finMes)));

			Double Balance = balancePasadoConIntereses
					+ pagos
					- gastosExtraordinarios
					- (gastosOrdinarios * unidadFuncional
							.getPorcentajeGastosComunes());
			return Balance;
		} else {
			return 0.0;
		}
	}

	private Date getFirstDateOfMonth(Integer mes, Integer año) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, año);
		cal.set(Calendar.DAY_OF_MONTH,
				Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	private Date getLastDateOfMonth(Integer mes, Integer año) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, año);
		cal.set(Calendar.DAY_OF_MONTH,
				Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	private Double calcularSumatoria(Collection<IContabilizable> collection) {
		Double result = 0.0;

		for (IContabilizable element : collection) {
			result += element.getMonto();
		}

		return result;
	}

}
