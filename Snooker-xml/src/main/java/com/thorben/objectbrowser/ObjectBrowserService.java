package com.thorben.objectbrowser;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.thorben.backend.service.ObjectBrowser3Service;
import com.thorben.objectbrowser.title.filter.ObjectBrowserFilter;
import com.thorben.objectbrowser.title.filter.ObjectBrowserTitle;
import com.thorben.objects.ErrorMassage;
import com.thorben.objects.News;
import com.thorben.objects.Termin;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
@Service
public class ObjectBrowserService {
	
	private static ThorbenDierkesLogger logger = new ThorbenDierkesLogger();
	
	private static final String ERROR_MESSAGE = "errorMessage";
	
	public static void  getInformationForOb(ObjectBrowser ob, final HttpServletRequest request) {
		
		Map<String,String> filterValue = ObjectBrowser3Service.getOb3FilterValue(request);
		int id = ob.getObjectType();
		switch(id) {
			case ThorbenDierkes.USER:
				Set<User> users = MySql.getInstance().getUserQueries().loadUserList(filterValue);
				if(!users.isEmpty()) {
					ob.getOb3Data().setObjectList(users);
				} else {
					clearInformationAndSetError(request);
				}
				break;
			case ThorbenDierkes.NEWS:
				Set<News> newsList = MySql.getInstance().getNewsQueries().loadNewsList(filterValue);
				if(!newsList.isEmpty()) {
					ob.getOb3Data().setObjectList(newsList);
				} else {
					clearInformationAndSetError(request);
				}
				break;
			case ThorbenDierkes.CALENDAR: 
				Set<Termin> calendarList = MySql.getInstance().getCalendarQueries().loadCalendarList(filterValue);
				if(!calendarList.isEmpty()) {
					ob.getOb3Data().setObjectList(calendarList);
				} else {
					clearInformationAndSetError(request);
				}
				break;
			case ThorbenDierkes.ERROR_LOG_MASSAGE:
				Set<ErrorMassage> errorList = MySql.getInstance().getErrorLoggQueries().loadErrorLogList(filterValue);
				if(!errorList.isEmpty()) {
					ob.getOb3Data().setObjectList(errorList);
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
	
	public static ObjectBrowser setHeaderInformation(int id) {
		return setHeaderInformation(id, new Locale("de"));
	}
	
	public static ObjectBrowser setHeaderInformation(int id, Locale locale) {
		ObjectBrowser ob = null;
		List<ObjectBrowserTitle> titleList = MySql.getInstance().getOb3Queries().getColumTitleForOb3(id, locale);
		List<ObjectBrowserFilter> filterList = MySql.getInstance().getOb3Queries().getFilterForOb3(id, locale);
		ObjectBrowserData obd = new ObjectBrowserData(titleList, filterList);
		switch(id) {
			case ThorbenDierkes.USER:
				ob = new ObjectBrowser("Benutzer", "Neuer Benutzer", "fas fa-user", id, obd);
				break;
			case ThorbenDierkes.NEWS:
				ob = new ObjectBrowser("Nachrichten", "Neue Nachricht", "fas fa-newspaper", id, obd);
				break;
			case ThorbenDierkes.CALENDAR: 
				ob = new ObjectBrowser("Termine", "Neuer Termin", "far fa-calendar-alt", id, obd);
				break;
			case ThorbenDierkes.ERROR_LOG_MASSAGE:
				ob = new ObjectBrowser("Fehler-Log", "fas fa-exclamation-triangle", id, obd);
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
		return true;
	}

}
