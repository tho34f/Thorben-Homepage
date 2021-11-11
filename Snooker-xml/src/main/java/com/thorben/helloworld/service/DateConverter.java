package com.thorben.helloworld.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class DateConverter {
	
	private static long displayDate;
	
	private SimpleDateFormat dayFormat = new SimpleDateFormat("dd MM yyyy");
	private SimpleDateFormat timeDayFormat = new SimpleDateFormat("dd MM yyyy yyyy MM dd HH:mm");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	public static String long2Date (long longDate, int dateFormat) { 
		
		Date date = new Date(longDate);
		String parsedDate = null; 
		
		switch(dateFormat) {
			case 1:
				DateFormat timeDayDate = DateFormat.getDateTimeInstance( DateFormat.MEDIUM, DateFormat.MEDIUM);
				parsedDate = timeDayDate.format(date);
				break;
			case 2:
				DateFormat timeDate = DateFormat.getTimeInstance( DateFormat.MEDIUM);
				parsedDate = timeDate.format(date);
				break;
			case 3: 
				DateFormat dayDate = DateFormat.getDateInstance( DateFormat.LONG);
				parsedDate = dayDate.format(date);
				break;
			default: 
				return null;
		} 
	    
	    return parsedDate; 
	}
	
	public static String long2DateLocal (long longDate, int dateFormat, Locale aLocale) { 
		
		Date date = new Date(longDate);
		String parsedDate = null; 
		
		switch(dateFormat) {
			case 1:
				DateFormat timeDayDate = DateFormat.getDateTimeInstance( DateFormat.MEDIUM, DateFormat.MEDIUM, aLocale );
				parsedDate = timeDayDate.format(date);
				break;
			case 2:
				DateFormat timeDate = DateFormat.getTimeInstance( DateFormat.MEDIUM, aLocale);
				parsedDate = timeDate.format(date);
				break;
			case 3: 
				DateFormat dayDate = DateFormat.getDateInstance( DateFormat.LONG, aLocale);
				parsedDate = dayDate.format(date);
				break;
			default: 
				return null;
		} 
	    
	    return parsedDate; 
	}
	
	public static boolean setDateFooter(Date indexDate, final HttpServletRequest request) {
		DateConverter.setDisplayDate(indexDate.getTime());
		long date = DateConverter.getDisplayDate();
		String formatDate = null;
		
		if(request.getSession().getAttribute("formatDate") == null) {
			formatDate =  DateConverter.long2Date (date, 3);
			request.getSession().setAttribute("formatDate", formatDate);
		}
		
		return true;
	}

	public SimpleDateFormat getDayFormat() {
		return dayFormat;
	}

	public void setDayFormat(SimpleDateFormat dayFormat) {
		this.dayFormat = dayFormat;
	}

	public SimpleDateFormat getTimeDayFormat() {
		return timeDayFormat;
	}

	public void setTimeDayFormat(SimpleDateFormat timeDayFormat) {
		this.timeDayFormat = timeDayFormat;
	}

	public SimpleDateFormat getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(SimpleDateFormat timeFormat) {
		this.timeFormat = timeFormat;
	}

	public static long getDisplayDate() {
		return displayDate;
	}

	public static void setDisplayDate(long displayDate) {
		DateConverter.displayDate = displayDate;
	} 

}
