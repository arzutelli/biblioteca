package com.nttdata.utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static int getEta(Date dataNascita) {

		int eta = 0;
		Calendar cNascita = Calendar.getInstance();
		cNascita.setTime(dataNascita);
		Calendar now = Calendar.getInstance();

		if (cNascita.get(Calendar.YEAR) < now.get(Calendar.YEAR)) {

			eta = now.get(Calendar.YEAR) - cNascita.get(Calendar.YEAR);

			if (cNascita.get(Calendar.MONTH) < now.get(Calendar.MONTH)) 
				return eta;
			else
				
			{
			if ((cNascita.get(Calendar.MONTH) == now.get(Calendar.MONTH)) && cNascita.get(Calendar.DAY_OF_MONTH) <= now.get(Calendar.DAY_OF_MONTH))
				return eta;
			else
				eta-=1;
			}
		}
		return eta;
	}
	
	public static Date addDays(Date data, int gg) {
		Calendar c= Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DAY_OF_MONTH,gg);
		
		return c.getTime();
	}
}
