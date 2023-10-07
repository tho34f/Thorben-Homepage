package com.thorben.objectbrowser;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.servlet.http.HttpServletRequest;

import com.thorben.objects.ErrorMassage;
import com.thorben.objects.News;
import com.thorben.objects.Termin;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
@Service
@Getter
@Setter
public class ObjectBrowserService {
	
	private static ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
	private static Set<News> newsList = new HashSet<>();
	private static Set<User> userList = new HashSet<>();
	private static Set<Termin> calendarList = new HashSet<>();
	private static Set<ErrorMassage> errorMassageList = new HashSet<>();
	
	private static final String INFORMATION_LIST = "informationList";
	private static final String ERROR_MESSAGE = "errorMessage";
	
	public static void  getInformationForOb(ObjectBrowser ob, final HttpServletRequest request) {
		
		int id = ob.getObjectType();
		switch(id) {
			case ThorbenDierkes.USER:
				setUserList(MySql.getInstance().getUserQueries().loadUserList());
				if(!getUserList().isEmpty()) {
					request.getSession().setAttribute(INFORMATION_LIST, getUserList());
				} else {
					clearInformationAndSetError(request);
				}
				break;
			case ThorbenDierkes.NEWS:
				setNewsList(MySql.getInstance().getNewsQueries().loadNewsList());
				if(!getNewsList().isEmpty()) {
					request.getSession().setAttribute(INFORMATION_LIST, getNewsList());
				} else {
					clearInformationAndSetError(request);
				}
				break;
			case ThorbenDierkes.CALENDAR: 
				setCalendarList(MySql.getInstance().getCalendarQueries().loadCalendarList());
				if(!getCalendarList().isEmpty()) {
					request.getSession().setAttribute(INFORMATION_LIST, getCalendarList());
				} else {
					clearInformationAndSetError(request);
				}
				break;
			case ThorbenDierkes.ERROR_LOG_MASSAGE:
				setErrorMassageList(MySql.getInstance().getErrorLoggQueries().loadErrorLogList());
				if(!getErrorMassageList().isEmpty()) {
					request.getSession().setAttribute(INFORMATION_LIST, getErrorMassageList());
				} else {
					clearInformationAndSetError(request);
				}
				break;
			default:
				String errorMessage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_OB).toString();
				logger.errorLog("Fehlende OB Elemente",errorMessage);
				break;
		}
		
	}
	
	public static ObjectBrowser setHeaderInformation(final HttpServletRequest request, int id) {
		ObjectBrowser ob = null;
		ObjectBrowserData obd = new ObjectBrowserData();
		switch(id) {
			case ThorbenDierkes.USER:
				ob = new ObjectBrowser("Benutzer", "Neuer Benutzer", "fas fa-user", id, obd.getUserObjectTitle());
				request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);
				break;
			case ThorbenDierkes.NEWS:
				ob = new ObjectBrowser("Nachrichten", "Neue Nachricht", "fas fa-newspaper", id, obd.getNewsObjectTitle());
				request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);
				break;
			case ThorbenDierkes.CALENDAR: 
				ob = new ObjectBrowser("Termine", "Neuer Termin", "far fa-calendar-alt", id, obd.getTerminObjectTitle());
				request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);
				break;
			case ThorbenDierkes.ERROR_LOG_MASSAGE:
				ob = new ObjectBrowser("Fehler-Log", "fas fa-exclamation-triangle", id, obd.getErrorLogObjectTitle());
				request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);
				break;
			default:
				String errorMassage = new StringBuilder().append(ThorbenDierkes.ERROR_MESSAGE_OB).toString();
				logger.errorLog("Fehlende OB Elemente",errorMassage);
				break;
		}
		
		return ob;
	}
	
	public static boolean clearInformationAndSetError(final HttpServletRequest request) {
		request.getSession().setAttribute(ERROR_MESSAGE, ThorbenDierkes.ERROR_MESSAGE_NO_ELEMENTS);
		request.getSession().removeAttribute(INFORMATION_LIST);
		
		return true;
	}

	public static Set<News> getNewsList() {
		return newsList;
	}

	public static void setNewsList(Set<News> newsList) {
		ObjectBrowserService.newsList = newsList;
	}

	public static Set<Termin> getCalendarList() {
		return calendarList;
	}

	public static void setCalendarList(Set<Termin> calendarList) {
		ObjectBrowserService.calendarList = calendarList;
	}

	public static Set<ErrorMassage> getErrorMassageList() {
		return errorMassageList;
	}

	public static void setErrorMassageList(Set<ErrorMassage> errorMassageList) {
		ObjectBrowserService.errorMassageList = errorMassageList;
	}

	public static Set<User> getUserList() {
		return userList;
	}

	public static void setUserList(Set<User> userList) {
		ObjectBrowserService.userList = userList;
	}

}