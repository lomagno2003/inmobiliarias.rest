package com.clomagno.inmobiliarias.rest.model;

import java.util.Comparator;
import java.util.Date;

public interface IContabilizable {
	public Double getMonto();
	public Date getFecha();
	
	public class DateComparator implements Comparator<IContabilizable>{
		@Override
		public int compare(IContabilizable o1, IContabilizable o2) {
			return o1.getFecha().compareTo(o2.getFecha());
		}
	}
}