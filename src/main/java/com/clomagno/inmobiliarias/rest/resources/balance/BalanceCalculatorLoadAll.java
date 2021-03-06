package com.clomagno.inmobiliarias.rest.resources.balance;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.clomagno.inmobiliarias.rest.model.CambioInteres;
import com.clomagno.inmobiliarias.rest.model.CambioPorcentajeGastos;
import com.clomagno.inmobiliarias.rest.model.IContabilizable;
import com.clomagno.inmobiliarias.rest.model.IUbicableEnElTiempo;
import com.clomagno.inmobiliarias.rest.model.UnidadFuncional;

@Component
public class BalanceCalculatorLoadAll implements IBalanceCalculator {
	@Override
	public Double getBalance(UnidadFuncional unidadFuncional, Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		
		List<IContabilizable> gastosExtraordinarios = new LinkedList<IContabilizable>(unidadFuncional.getGastoExtraordinario());
		List<IContabilizable> gastosOrdinarios = new LinkedList<IContabilizable>(unidadFuncional.getConsorcio().getGastoOrdinario());
		List<IContabilizable> pagos = new LinkedList<IContabilizable>(unidadFuncional.getPago());
		
		Collections.sort(gastosExtraordinarios, new IContabilizable.DateComparator());
		Collections.sort(gastosOrdinarios, new IContabilizable.DateComparator());
		Collections.sort(pagos, new IContabilizable.DateComparator());
		
		calendar.add(Calendar.MONTH, 1);
		extract(calendar.getTime(), gastosExtraordinarios);
		extract(calendar.getTime(), gastosOrdinarios);
		extract(calendar.getTime(), pagos);
		
		return getBalanceRec(unidadFuncional,fecha,gastosExtraordinarios,gastosOrdinarios,pagos);
	}
	
	private Double getBalanceRec(UnidadFuncional unidadFuncional, Date fecha, List<IContabilizable> gastosExtraordinarios, List<IContabilizable> gastosOrdinarios, List<IContabilizable> pagos){
		if(gastosExtraordinarios.isEmpty()&&gastosOrdinarios.isEmpty()&&pagos.isEmpty()){
			return 0.0;
		} else {
			//Extract the IContabilizables of the month
			List<IContabilizable> auxGastosExtraordinarios = extract(fecha, gastosExtraordinarios);
			List<IContabilizable> auxGastosOrdinarios = extract(fecha, gastosOrdinarios);
			List<IContabilizable> auxPagos = extract(fecha, pagos);
			
			//Get the corresponding PorcentajeDeGastos
			Double porcentajeGastosComunes = null;
			//If the UF havn't setted any cambioPorcentajeGastos, is assumed 0
			if(unidadFuncional.getCambioPorcentajeGastos().isEmpty()){
				porcentajeGastosComunes = 0.0;
			} else {
				List<IUbicableEnElTiempo> cambiosPorcentajeGastosComunes = new LinkedList<IUbicableEnElTiempo>(unidadFuncional.getCambioPorcentajeGastos());
				uExtract(fecha, cambiosPorcentajeGastosComunes);
				Collections.sort(cambiosPorcentajeGastosComunes,new IUbicableEnElTiempo.DateComparator());
				
				porcentajeGastosComunes = ((CambioPorcentajeGastos)cambiosPorcentajeGastosComunes.iterator().next()).getPorcentajeGasto();
			}
			
			//Calculate the partial balance of the month
			Double result = -1.0*calcularSumatoria(auxGastosExtraordinarios);
			result -= porcentajeGastosComunes*calcularSumatoria(auxGastosOrdinarios);
			result += calcularSumatoria(auxPagos);
			
			//Continue with the recursive call
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha);
			calendar.add(Calendar.MONTH, -1);
			
			Double lastBalance = getBalanceRec(unidadFuncional,calendar.getTime(),gastosExtraordinarios,gastosOrdinarios,pagos); 
			
			//If lastBalance is negative, apply the corresponding taxes to it
			if(lastBalance<0.0){
				//Get the corresponding taxes at that moment
				List<IUbicableEnElTiempo> cambiosIntereses = new LinkedList<IUbicableEnElTiempo>(unidadFuncional.getConsorcio().getCambioInteres());
				uExtract(fecha, cambiosIntereses);
				Collections.sort(cambiosIntereses,new IUbicableEnElTiempo.DateComparator());
				
				Double intereses = ((CambioInteres)cambiosIntereses.iterator().next()).getInteres();

				lastBalance *= (1+intereses);
			}
			
			return result + lastBalance;
		}
	}
	
	/**
	 * This method facilitates all the castings to extract Contabilizables using the IUbicableEnElTiempo logic
	 * @param fecha
	 * @param list
	 * @return
	 */
	private List<IContabilizable> extract(Date fecha, List<IContabilizable> list){
		List<IUbicableEnElTiempo> aux = new LinkedList<IUbicableEnElTiempo>(list);
		
		List<IUbicableEnElTiempo> extractedElements = uExtract(fecha, aux);
		
		List<IContabilizable> result = new LinkedList<IContabilizable>();
		
		for(IUbicableEnElTiempo elem:extractedElements){
			result.add(((IContabilizable)elem));
		}
		
		list.clear();
		for(IUbicableEnElTiempo elem:aux){
			list.add(((IContabilizable)elem));
		}
		
		return result;
	}
	
	/**
	 * Extract all Contabilizable from the list since the mes and año and remove them from the list
	 * @param mes
	 * @param año
	 * @return
	 */
	private List<IUbicableEnElTiempo> uExtract(Date fecha, List<IUbicableEnElTiempo> list){
		//Get the first day of the month
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(Calendar.DAY_OF_MONTH,
				Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		Date firstDayOfMonth = calendar.getTime();

		//Initialize a new list for the result
		LinkedList<IUbicableEnElTiempo> result = new LinkedList<IUbicableEnElTiempo>();
		
		//Find all the elements which ocur after the first day of the month
		for(IUbicableEnElTiempo elem:list){
			if(elem.getFecha().after(firstDayOfMonth)){
				result.add(elem);
			}
		}
		
		//Remove the elements found from the list
		list.removeAll(result);

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
