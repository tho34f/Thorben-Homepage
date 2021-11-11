package com.thorben.helloworld.web;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.thorben.helloworld.queries.MySql;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.ObjectBrowser;
import com.thorben.helloworld.service.ObjectBrowserController;
import com.thorben.helloworld.service.TypeConverter;
import com.thorben.helloworld.snooker.User;

@Controller
public class BackendController extends HttpServlet {
	
	private static final long serialVersionUID = 1217699872564172806L;
	private final Logger logger = LoggerFactory.getLogger(BackendController.class);
	private static ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	
	private static final String LOGIN = "backend/login";
	private static final String ERROR_MASSAGE = "errormasage";
	private static final String IS_LOGIN_OK = "isLoginOk";
	
	private static String language;
	
	@GetMapping(value = "/backend")
	public String startLoginBackend(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		setLanguage(Locale.getDefault().getLanguage());
		request.getSession().setAttribute("user", null);
		return LOGIN;
	}
	
	@GetMapping(value = "/backend/login")
	public String startLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		return startLoginBackend(model, request, response);
	}
	
	@PostMapping(value = "/backend/backendindex")
	public String checkLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		User loginUser = new User();
		String forwordPath = null;
		setLanguage(Locale.getDefault().getLanguage());
		boolean isLoginOk = false;
		
		String test = request.getParameter("username");
		
		if(request.getParameter("username") != null) {
			loginUser.setUserLogin(test);
			loginUser.setPassword(request.getParameter("password"));
		}
		
		isLoginOk = MySql.getInstance().getUserQueries().checkLogin(loginUser);
		request.getSession().setAttribute(IS_LOGIN_OK, isLoginOk);
		
		if(isLoginOk) {
			request.getSession().setAttribute("user", loginUser);
			forwordPath = "backend/backendindex";
			logger.info("Login war erfolgreich.");
			
		} else {
			request.setAttribute(ERROR_MASSAGE, "Der User ist nicht g�ltig.");
			forwordPath = LOGIN;
			logger.info("Der User ist nicht g�ltig.");
		}
		
		return forwordPath;
	}
	
	@GetMapping(value = "/backend/backendindex")
	public String createIndex(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		setLanguage(Locale.getDefault().getLanguage());
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/backendindex";
		} else {
			forwordPath = ThorbenDierkesService.errorUserLogin(request, false);
		} 
		
		return forwordPath;
	}
	
	@PostMapping(value = "/backend/backendObjectBrowser")
	public String createObject(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		setLanguage(Locale.getDefault().getLanguage());
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/backendObjectBrowser";
		} else {
			forwordPath = ThorbenDierkesService.errorUserLogin(request, false);
		} 
		
		return forwordPath;
	}
	
	@GetMapping(value = "/backend/backendObjectBrowser")
	public String getObject(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		setLanguage(Locale.getDefault().getLanguage());
		int objectId = TypeConverter.string2int(request.getParameter("id"), 0);
		
		if(request.getSession().getAttribute("user") != null) {
			ObjectBrowser ob = ObjectBrowserController.setHeaderInformation(request, objectId);
			ObjectBrowserController.getInformationForOb(ob, request);
			forwordPath = "backend/backendObjectBrowser";
		} else {
			forwordPath = ThorbenDierkesService.errorUserLogin(request, false);
		} 
		
		return forwordPath;
	}

	public static ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}

	public static String getLanguage() {
		return language;
	}

	public static void setLanguage(String language) {
		BackendController.language = language;
	}

}
