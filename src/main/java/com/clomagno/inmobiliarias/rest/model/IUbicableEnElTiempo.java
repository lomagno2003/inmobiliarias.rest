package com.clomagno.inmobiliarias.rest.model;

import java.util.Comparator;
import java.util.Date;

public interface IUbicableEnElTiempo {
	public Date getFecha();
	
	public class DateComparator implements Comparator<IUbicableEnElTiempo>{
		@Override
		public int compare(IUbicableEnElTiempo o1, IUbicableEnElTiempo o2) {
			return o1.getFecha().compareTo(o2.getFecha());
		}
	}
}
