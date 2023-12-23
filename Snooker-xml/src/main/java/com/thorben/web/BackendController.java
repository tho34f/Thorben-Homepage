package com.thorben.web;

import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thorben.objectbrowser.ObjectBrowser;
import com.thorben.objectbrowser.ObjectBrowserService;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;
import com.thorben.service.TypeConverter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BackendController extends HttpServlet {
	
	private static final long serialVersionUID = 1217699872564172806L;
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	private static final String LOGIN = "backend/login";
	private static final String LOGIN_REDIRECT = "redirect:/backend/login";
	private static final String ERROR_MASSAGE = "errormasage";
	private static final String IS_LOGIN_OK = "isLoginOk";
	
	private static String language;
  
	@GetMapping(value = "/backend")
	public ModelAndView startLoginBackend(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		return new ModelAndView(LOGIN_REDIRECT);
	}
	
	@GetMapping(value = "/backend/login")
	public ModelAndView startLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		setLanguage(Locale.getDefault().getLanguage());
		request.getSession().removeAttribute("user");
		return new ModelAndView(LOGIN);
	}
	
	@PostMapping(value = "/backend/backendindex")
	public ModelAndView checkLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		User loginUser = new User();
		setLanguage(Locale.getDefault().getLanguage());
		boolean isLoginOk = false;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username != null) {
			loginUser.setUserLogin(username);
		} else {
			request.setAttribute(ERROR_MASSAGE, "Der Username ist nicht gültig.");
			LOOGER.infoLog("Der Username ist nicht gültig");
			return new ModelAndView(LOGIN_REDIRECT);
		}
		
		if(password != null) {
			loginUser.setPassword(password);
		} else {
			request.setAttribute(ERROR_MASSAGE, "Das Password ist nicht gültig.");
			LOOGER.infoLog("Der Password ist nicht gültig");
			return new ModelAndView(LOGIN_REDIRECT);
		}
		
		isLoginOk = MySql.getInstance().getUserQueries().checkLogin(loginUser);
		request.getSession().setAttribute(IS_LOGIN_OK, isLoginOk);
		
		if(isLoginOk) {
			request.getSession().setAttribute("user", loginUser);
			LOOGER.infoLog("Login war erfolgreich.");
			return new ModelAndView("backend/backendindex");
		} else {
			request.setAttribute(ERROR_MASSAGE, "Der User ist nicht gültig.");
			LOOGER.infoLog("Der User ist nicht gültig.");
			return new ModelAndView(LOGIN_REDIRECT);
		}
	}
	
	@GetMapping(value = "/backend/backendindex")
	public String createIndex(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		setLanguage(Locale.getDefault().getLanguage());
		return "backend/backendindex";
	}
	
	@PostMapping(value = "/backend/backendObjectBrowser")
	public String createObject(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		setLanguage(Locale.getDefault().getLanguage());
		return "backend/backendObjectBrowser";
	}
	
	@GetMapping(value = "/backend/backendObjectBrowser")
	public String getObject(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		setLanguage(Locale.getDefault().getLanguage());
		int objectId = TypeConverter.string2int(request.getParameter("id"), 0);
		
		ObjectBrowser ob = ObjectBrowserService.setHeaderInformation(request, objectId);
		ObjectBrowserService.getInformationForOb(ob, request);
		request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);

		return "backend/backendObjectBrowser";
	}

	public static String getLanguage() {
		return language;
	}

	public static void setLanguage(String language) {
		BackendController.language = language;
	}

}
