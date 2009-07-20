package com.myfetch.util;

import java.util.Calendar;

public class DateUtils {
	/**
	 * year-month-date
	 */
	public static String getDate(){
		String date="";
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH)+1;
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		date+=year;
		if(month<10){
			date+="0"+month;
		}else{
			date+=month;
		}
		if(day<10){
			date+="0"+day;
		}else{
			date+=day;
		}
		return date;
	}
}
