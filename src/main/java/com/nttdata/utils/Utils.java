package com.nttdata.utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	
	
	public static int getEta(Date dataNascita){
 
		int eta = 0;
		Calendar cNascita = Calendar.getInstance();	
		cNascita.setTime(dataNascita);
		Calendar now = Calendar.getInstance();

		if(cNascita.get(Calendar.YEAR) < now.get(Calendar.YEAR)) {

			eta=now.get(Calendar.YEAR)-cNascita.get(Calendar.YEAR);

			if(cNascita.get(Calendar.MONTH) <= now.get(Calendar.MONTH)){

				if(cNascita.get(Calendar.DAY_OF_MONTH) <= now.get(Calendar.DAY_OF_MONTH)){
					eta+=1; 
				}//fine if day

				else {
					eta+=1;
				} //fine else day

			}//fine if month

		}//fine if year

		return eta;
	}
}
