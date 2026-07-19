package com.thorben.backend.controller;

import com.thorben.backend.data.WizardData;
import com.thorben.backend.service.BackendService;
import com.thorben.objectbrowser.ObjectBrowser;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.TypeConverter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serial;

@Controller
@RequestMapping("/backend")
public class BackendController extends AbstractBackendController<WizardData, BackendService> {
	
	@Serial
    private static final long serialVersionUID = 1217699872564172806L;

	@Autowired
    public BackendController() {
        this.controllerData = new WizardData();
        this.controllerData.setLanguage("de");
		this.service = new BackendService();
    }
  
	@GetMapping(value = "/")
	public ModelAndView startLoginBackend(final HttpServletRequest request) {
		LOOGER.infoLog("BackendController: redirect to backend/login");
		return new ModelAndView(controllerData.getLOGIN());
	}
	
	@GetMapping(value = "/login")
	public ModelAndView startLogin(final HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return new ModelAndView(controllerData.getLOGIN());
	}

	@PostMapping(value = "/backendindex")
	public ModelAndView checkLogin(final HttpServletRequest request) {
		
		LOOGER.infoLog("BackendController: start Login-Progress");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean isLoginOk = false;
		User user = null;
		if(StringUtils.isNotBlank(password) && StringUtils.isNotBlank(username)) {
			user = MySql.getInstance().getUserQueries().checkLogin(username, password);
			isLoginOk = user != null;
			request.getSession().setAttribute(controllerData.getIS_LOGIN_OK(), isLoginOk);
		} else {
			request.setAttribute(controllerData.getERROR_MASSAGE(), "Der eingegebene Benutzername bzw. das eingegebene Passwort ist nicht gültig.");
			LOOGER.infoLog("Der Username/das Password ist nicht gültig");
			return new ModelAndView(controllerData.getLOGIN_REDIRECT());
		}
		
		if(isLoginOk) {
			request.getSession().setAttribute("user", user);
			LOOGER.infoLog("Login war erfolgreich.");
			return new ModelAndView("backend/backendindex");
		} else {
			request.setAttribute(controllerData.getERROR_MASSAGE(), "Der User ist nicht gültig.");
			LOOGER.infoLog("Der User ist nicht gültig.");
			return new ModelAndView(controllerData.getLOGIN_REDIRECT());
		}
	}
	
	@GetMapping(value = "/backendindex")
	public String createIndex(final HttpServletRequest request) {
		LOOGER.infoLog("BackendController: createIndex");
		return "backend/backendindex";
	}
	
	@GetMapping(value = "/backendObjectBrowser")
	public String getObject(final HttpServletRequest request) {
		
		int objectId = TypeConverter.string2int(request.getParameter("id"), 0);
		LOOGER.infoLog("BackendController: getObjec for Ob3 " + objectId);

		ObjectBrowser ob = this.service.createAndfillObjectBrowser(request, objectId);
		request.getSession().setAttribute(ThorbenDierkes.OBJEKT_BROWSER, ob);

		return "backend/backendObjectBrowser";
	}

}
