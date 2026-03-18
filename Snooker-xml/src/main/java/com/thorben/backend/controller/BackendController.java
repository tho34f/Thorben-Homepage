package com.thorben.backend.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thorben.objectbrowser.ObjectBrowser;
import com.thorben.objectbrowser.ObjectBrowserService;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;
import com.thorben.service.TypeConverter;
import com.thorben.web.data.ControllerData;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/backend")
public class BackendController extends HttpServlet {
	
	private static final long serialVersionUID = 1217699872564172806L;
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	private static final String LOGIN = "backend/login";
	private static final String LOGIN_REDIRECT = "redirect:/backend/login";
	private static final String ERROR_MASSAGE = "errormasage";
	private static final String IS_LOGIN_OK = "isLoginOk";
	
	private final ControllerData controllerData;
	
	@Autowired
    public BackendController() {
        this.controllerData = new ControllerData();
        this.controllerData.setLanguage("de");
    }
  
	@GetMapping(value = "/")
	public ModelAndView startLoginBackend(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		LOOGER.infoLog("BackendController: redirect to backend/login");
		return new ModelAndView(LOGIN_REDIRECT);
	}
	
	@GetMapping(value = "/login")
	public ModelAndView startLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		return new ModelAndView(LOGIN);
	}

	@PostMapping(value = "backendindex")
	public ModelAndView checkLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		LOOGER.infoLog("BackendController: start Login-Progress");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean isLoginOk = false;
		User user = null;
		if(StringUtils.isNotBlank(password) && StringUtils.isNotBlank(username)) {
			user = MySql.getInstance().getUserQueries().checkLogin(username, password);
			isLoginOk = user != null;
			request.getSession().setAttribute(IS_LOGIN_OK, isLoginOk);
		} else {
			request.setAttribute(ERROR_MASSAGE, "Der eingegebene Benutzername bzw. das eingegebene Passwort ist nicht gültig.");
			LOOGER.infoLog("Der Username/das Password ist nicht gültig");
			return new ModelAndView(LOGIN_REDIRECT);
		}
		
		if(isLoginOk) {
			request.getSession().setAttribute("user", user);
			LOOGER.infoLog("Login war erfolgreich.");
			return new ModelAndView("backend/backendindex");
		} else {
			request.setAttribute(ERROR_MASSAGE, "Der User ist nicht gültig.");
			LOOGER.infoLog("Der User ist nicht gültig.");
			return new ModelAndView(LOGIN_REDIRECT);
		}
	}
	
	@GetMapping(value = "/backendindex")
	public String createIndex(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		LOOGER.infoLog("BackendController: createIndex");
		return "backend/backendindex";
	}
	
	@PostMapping(value = "/backendObjectBrowser")
	public String createObject(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		return "backend/backendObjectBrowser";
	}
	
	@GetMapping(value = "/backendObjectBrowser")
	public String getObject(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		int objectId = TypeConverter.string2int(request.getParameter("id"), 0);
		LOOGER.infoLog("BackendController: getObjec for Ob3 " + objectId);
		ObjectBrowser ob = ObjectBrowserService.setHeaderInformation(objectId, request.getLocale());
		ObjectBrowserService.getInformationForOb(ob, request);
		request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);

		return "backend/backendObjectBrowser";
	}

}
