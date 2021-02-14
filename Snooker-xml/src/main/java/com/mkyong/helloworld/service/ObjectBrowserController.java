package com.mkyong.helloworld.service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.mkyong.helloworld.queries.CalendarQueries;
import com.mkyong.helloworld.queries.NewsQueries;
import com.mkyong.helloworld.snooker.News;
import com.mkyong.helloworld.snooker.Termin;

public class ObjectBrowserController {
	
	private ObjectBrowserController() {
    	
    	throw new IllegalStateException("Utility Class");
    	
    }
	
	private static Set<News> newsList = new HashSet<>();
	private static Set<Termin> calendarList = new HashSet<>();
	
	public static void  getInformationForOb(ObjectBrowser ob, final HttpServletRequest request) {
		
		int id = ob.getObjectType();
		
		switch(id) {
			case 39:
				setNewsList(NewsQueries.loadNewsList());
				if(!getNewsList().isEmpty()) {
					request.getSession().setAttribute("informationList", newsList);
				} else {
					request.getSession().setAttribute("errorMessage", "Es sind keine ELemente vorhanden.");
				}
				break;
			case 40: 
				setCalendarList(CalendarQueries.loadCalendarList());
				if(!getCalendarList().isEmpty()) {
					request.getSession().setAttribute("informationList", calendarList);
				} else {
					request.getSession().setAttribute("errorMessage", "Es sind keine ELemente vorhanden.");
				}
				break;
		}
		
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

}
