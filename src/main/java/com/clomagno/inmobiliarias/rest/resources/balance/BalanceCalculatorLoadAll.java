package com.clomagno.inmobiliarias.rest.resources.balance;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import scala.annotation.meta.getter;

import com.clomagno.inmobiliarias.rest.model.GastoExtraordinario;
import com.clomagno.inmobiliarias.rest.model.GastoOrdinario;
import com.clomagno.inmobiliarias.rest.model.IContabilizable;
import com.clomagno.inmobiliarias.rest.model.Pago;
import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;
import com.clomagno.inmobiliarias.rest.repositories.GastoExtraordinarioRepository;

public class BalanceCalculatorLoadAll implements IBalanceCalculator {
	private static final Double INTERESES = 0.0;

	@Override
	public Double getBalance(UnidadFuncional unidadFuncional, Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		
		System.out.println("Getting balance of month " + calendar.get(Calendar.MONTH));
		List<IContabilizable> gastosExtraordinarios = new LinkedList<IContabilizable>(unidadFuncional.getGastoExtraordinario());
		List<IContabilizable> gastosOrdinarios = new LinkedList<IContabilizable>(unidadFuncional.getConsorcio().getGastoOrdinario());
		List<IContabilizable> pagos = new LinkedList<IContabilizable>(unidadFuncional.getPago());
		
		Collections.sort(gastosExtraordinarios, new IContabilizable.DateComparator());
		Collections.sort(gastosOrdinarios, new IContabilizable.DateComparator());
		Collections.sort(pagos, new IContabilizable.DateComparator());
		
		System.out.println("Init");
		//TODO Extract all Gastos and Pagos since mes and año
		
		calendar.add(Calendar.MONTH, 1);
		extract(calendar.getTime(), gastosExtraordinarios);
		extract(calendar.getTime(), gastosOrdinarios);
		extract(calendar.getTime(), pagos);
		System.out.println("End");
		
		return getBalanceRec(unidadFuncional,fecha,gastosExtraordinarios,gastosOrdinarios,pagos);
	}
	
	private Double getBalanceRec(UnidadFuncional unidadFuncional, Date fecha, List<IContabilizable> gastosExtraordinarios, List<IContabilizable> gastosOrdinarios, List<IContabilizable> pagos){
		//TODO Stub method
		if(gastosExtraordinarios.isEmpty()&&gastosOrdinarios.isEmpty()&&pagos.isEmpty()){
			return 0.0;
		} else {
			//TODO Add logic to apply taxes to liabilities

			//Extract the IContabilizables of the month
			List<IContabilizable> auxGastosExtraordinarios = extract(fecha, gastosExtraordinarios);
			List<IContabilizable> auxGastosOrdinarios = extract(fecha, gastosOrdinarios);
			List<IContabilizable> auxPagos = extract(fecha, pagos);
			
			//Calculate the partial balance of the month
			Double result = -1.0*calcularSumatoria(auxGastosExtraordinarios);
			result -= unidadFuncional.getPorcentajeGastosComunes()*calcularSumatoria(auxGastosOrdinarios);
			result += calcularSumatoria(auxPagos);
			
			//Continue with the recursive call
			//TODO Contemplate the case crossing years
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha);
			calendar.add(Calendar.MONTH, -1);
			
			return result + getBalanceRec(unidadFuncional,calendar.getTime(),gastosExtraordinarios,gastosOrdinarios,pagos);
		}
	}
	
	/**
	 * Extract all Contabilizable from the list since the mes and año and remove them from the list
	 * @param mes
	 * @param año
	 * @return
	 */
	private LinkedList<IContabilizable> extract(Date fecha, List<IContabilizable> list){
		//Get the first day of the month
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(Calendar.DAY_OF_MONTH,
				Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		Date firstDayOfMonth = calendar.getTime();
				
		//Initialize a new list for the result
		LinkedList<IContabilizable> result = new LinkedList<IContabilizable>();
		
		//Find all the elements which ocur after the first day of the month
		for(IContabilizable elem:list){
			if(elem.getFecha().after(firstDayOfMonth)){
				result.add(elem);
			}
		}
		
		//Remove the elements found from the list
		list.removeAll(result);
		System.out.println(result.size()+" were extracted.");

		return result;
	}

	private Double calcularSumatoria(Collection<IContabilizable> collection) {
		Double result = 0.0;

		for (IContabilizable element : collection) {
			result += element.getMonto();
		}

		return result;
	}
}
