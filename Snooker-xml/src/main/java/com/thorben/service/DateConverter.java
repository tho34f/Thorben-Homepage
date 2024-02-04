package com.thorben.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class DateConverter {
	
	public static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("dd MM yyyy");
	public static final SimpleDateFormat TIME_DAY_FORMAT = new SimpleDateFormat("dd MM yyyy yyyy MM dd HH:mm");
	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
	
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
		long date = indexDate.getTime();
		String formatDate = null;
		
		if(request.getSession().getAttribute("formatDate") == null) {
			formatDate =  DateConverter.long2Date (date, 3);
			request.getSession().setAttribute("formatDate", formatDate);
		}
		
		return true;
	}

}
