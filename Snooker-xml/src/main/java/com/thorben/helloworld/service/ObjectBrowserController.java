package com.thorben.helloworld.service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.thorben.helloworld.queries.CalendarQueries;
import com.thorben.helloworld.queries.ErrorLoggQueries;
import com.thorben.helloworld.queries.NewsQueries;
import com.thorben.helloworld.snooker.ErrorMassage;
import com.thorben.helloworld.snooker.News;
import com.thorben.helloworld.snooker.Termin;

public class ObjectBrowserController {
	
	private ObjectBrowserController() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
	
	private static Set<News> newsList = new HashSet<>();
	private static Set<Termin> calendarList = new HashSet<>();
	private static Set<ErrorMassage> errorMassageList = new HashSet<>();
	
	private static final String INFORMATION_LIST = "informationList";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	public static void  getInformationForOb(ObjectBrowser ob, final HttpServletRequest request) {
		
		int id = ob.getObjectType();
		
		switch(id) {
			case ThorbenDierkes.NEWS:
				setNewsList(NewsQueries.loadNewsList());
				if(!getNewsList().isEmpty()) {
					request.getSession().setAttribute(INFORMATION_LIST, newsList);
				} else {
					clearInformationAndSetError(request);
				}
				break;
			case ThorbenDierkes.CALENDAR: 
				setCalendarList(CalendarQueries.loadCalendarList());
				if(!getCalendarList().isEmpty()) {
					request.getSession().setAttribute(INFORMATION_LIST, calendarList);
				} else {
					clearInformationAndSetError(request);
				}
				break;
			case ThorbenDierkes.ERROR_LOG_MASSAGE:
				setErrorMassageList(ErrorLoggQueries.loadErrorLogList());
				if(!getErrorMassageList().isEmpty()) {
					request.getSession().setAttribute(INFORMATION_LIST, errorMassageList);
				} else {
					clearInformationAndSetError(request);
				}
				break;
			default:
				String errorMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_OB).toString();
				ThorbenDierkesLogger.errorLog("Fehlende OB Elemente",errorMessage);
				break;
		}
		
	}
	
	public static void clearInformationAndSetError(final HttpServletRequest request) {
		request.getSession().setAttribute(ERROR_MESSAGE, ThorbenDierkes.ERROR_MESSAGE_NO_ELEMENTS);
		request.getSession().removeAttribute(INFORMATION_LIST);
	}

	public static Set<News> getNewsList() {
		return newsList;
	}

	public static void setNewsList(Set<News> newsList) {
		ObjectBrowserController.newsList = newsList;
	}

	public static Set<Termin> getCalendarList() {
		return calendarList;
	}

	public static void setCalendarList(Set<Termin> calendarList) {
		ObjectBrowserController.calendarList = calendarList;
	}

	public static Set<ErrorMassage> getErrorMassageList() {
		return errorMassageList;
	}

	public static void setErrorMassageList(Set<ErrorMassage> errorMassageList) {
		ObjectBrowserController.errorMassageList = errorMassageList;
	}

}
