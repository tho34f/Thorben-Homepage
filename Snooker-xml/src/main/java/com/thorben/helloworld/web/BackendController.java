package com.thorben.helloworld.web;

import java.sql.SQLException;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thorben.helloworld.queries.CalendarQueries;
import com.thorben.helloworld.queries.MySql;
import com.thorben.helloworld.queries.NewsQueries;
import com.thorben.helloworld.queries.UserQueries;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.ObjectBrowser;
import com.thorben.helloworld.service.ObjectBrowserController;
import com.thorben.helloworld.service.ThorbenDierkes;
import com.thorben.helloworld.service.ThorbenDierkesLogger;
import com.thorben.helloworld.service.TypeConverter;
import com.thorben.helloworld.snooker.News;
import com.thorben.helloworld.snooker.Termin;
import com.thorben.helloworld.snooker.User;

@Controller
public class BackendController {
	
	private final Logger logger = LoggerFactory.getLogger(BackendController.class);
	private static ThorbenDierkesLogger log = new ThorbenDierkesLogger();
	private final ThorbenDierkesService helloWorldService;
	
	@Autowired
	public BackendController(ThorbenDierkesService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	
	String login = "backend/login";
	String userNotLogin = "Der User ist nicht eingeloggt.";
	String erromessage = "errormasage";
	String isLoginOkString = "isLoginOk";
	
	@RequestMapping(value = "/backend/login", method = RequestMethod.GET)
	public String startLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		request.getSession().setAttribute("user", null);
		return login;
	}
	
	@RequestMapping(value = "/backend/backendindex", method = RequestMethod.POST)
	public String checkLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, NamingException {
		
		User loginUser = new User();
		String forwordPath = null;
		boolean isLoginOk = false;
		
		String test = request.getParameter("username");
		
		if(request.getParameter("username") != null) {
			loginUser.setUserLogin(test);
			loginUser.setPassword(request.getParameter("password"));
		}
		
		isLoginOk = MySql.getInstance().getUserQueries().checkLogin(loginUser);
		request.getSession().setAttribute(isLoginOkString, isLoginOk);
		
		if(isLoginOk) {
			request.getSession().setAttribute("user", loginUser);
			forwordPath = "backend/backendindex";
			logger.info("Login war erfolgreich.");
			
		} else {
			request.setAttribute(erromessage, "Der User ist nicht gültig.");
			forwordPath = login;
			logger.info("Der User ist nicht gültig.");
		}
		
		return forwordPath;
	}
	
	@RequestMapping(value = "/backend/backendindex", method = RequestMethod.GET)
	public String createIndex(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/backendindex";
		} else {
			request.getSession().setAttribute(isLoginOkString, false);
			request.setAttribute(erromessage, userNotLogin);
			forwordPath = login;
		} 
		
		return forwordPath;
	}
	
	@RequestMapping(value = "/backend/backendObjectBrowser", method = RequestMethod.POST)
	public String createObject(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/backendObjectBrowser";
		} else {
			request.getSession().setAttribute(isLoginOkString, false);
			request.setAttribute(erromessage, userNotLogin);
			forwordPath = login;
		} 
		
		return forwordPath;
	}
	
	@RequestMapping(value = "/backend/backendObjectBrowser", method = RequestMethod.GET)
	public String getObject(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, NamingException {
		
		String forwordPath = null;
		int objectId = TypeConverter.string2int(request.getParameter("id"), 0);
		
		if(request.getSession().getAttribute("user") != null) {
			ObjectBrowser ob = ObjectBrowser.setHeaderInformation(request, objectId);
			ObjectBrowserController.getInformationForOb(ob, request);
			forwordPath = "backend/backendObjectBrowser";
		} else {
			request.getSession().setAttribute(isLoginOkString, false);
			request.setAttribute(erromessage, userNotLogin);
			forwordPath = login;
		} 
		
		return forwordPath;
	}
	
	@RequestMapping(value = "/backend/newswizard", method = RequestMethod.GET)
	public String creatNews(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, NamingException {
		
		String forwordPath = null;
		News message = null;
		String newsId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/newswizard";
			if(newsId != null) {
				message = MySql.getInstance().getNewsQueries().loadNews(TypeConverter.string2int(newsId, 0));
			}
			request.getSession().setAttribute("message", message);
		} else {
			request.getSession().setAttribute(isLoginOkString, false);
			request.setAttribute(erromessage, userNotLogin);
			forwordPath = login;
		} 
		
		
		return forwordPath;
	}
	
	@RequestMapping(value = "/backend/newswizard", method = RequestMethod.POST)
	public String setNews(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, NamingException {
		
		String forwordPath = null;
		
		String title = request.getParameter("titleWizard");
		String teaser = request.getParameter("teaserWizard");
		String text = request.getParameter("textWizard");
		
		if(request.getSession().getAttribute("user") != null) {
			MySql.getInstance().getNewsQueries().newNewsEntry(title, text, teaser, null);
			forwordPath = "backend/newswizard";
		} else {
			request.getSession().setAttribute(isLoginOkString, false);
			request.setAttribute(erromessage, userNotLogin);
			forwordPath = login;
		} 
		
		return forwordPath;
	}
	
	@RequestMapping(value = "/backend/terminewizard", method = RequestMethod.GET)
	public String creatTermin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, NamingException {
		
		String forwordPath = null;
		Termin tm = null;
		String terminId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/terminewizard";
			if(terminId != null) {
				tm = MySql.getInstance().getCalendarQueries().loadCalendar(TypeConverter.string2int(terminId, 0));
			}
			request.getSession().setAttribute("termin", tm);
		} else {
			request.getSession().setAttribute(isLoginOkString, false);
			request.setAttribute(erromessage, userNotLogin);
			forwordPath = login;
		} 
		
		return forwordPath;
	}
	
	@RequestMapping(value = "/backend/terminewizard", method = RequestMethod.POST)
	public String setTermin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) throws SQLException, NamingException {
		
		String forwordPath = null;
		
		String title = request.getParameter("titleWizard");
		String teaser = request.getParameter("teaserWizard");
		String description = request.getParameter("beschreibungWizard");
		
		if(request.getSession().getAttribute("user") != null) {
			MySql.getInstance().getCalendarQueries().newCalendarEntry(title, description, teaser);
			forwordPath = "backend/terminewizard";
		} else {
			request.getSession().setAttribute(isLoginOkString, false);
			request.setAttribute(erromessage, userNotLogin);
			forwordPath = login;
		} 
		
		return forwordPath;
	}

	public ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}

}
