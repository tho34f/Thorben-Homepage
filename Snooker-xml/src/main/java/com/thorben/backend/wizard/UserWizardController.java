package com.thorben.backend.wizard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thorben.backend.service.BackendService;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.TextService;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;
import com.thorben.service.TypeConverter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class UserWizardController extends HttpServlet {

	private static final long serialVersionUID = -3295292219817459332L;
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	private static final String VIEW = "backend/userwizard";
	private static final String SUBMIT_VIEW = "backend/submitWizard";
	
	
	@GetMapping(value = "/backend/userwizard")
	public ModelAndView createUser(final HttpServletRequest request, final HttpServletResponse response) {
		LOOGER.infoLog("UserWizardController: start create User");
		String userId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") == null) {
			return new ModelAndView(BackendService.errorUserLogin(request));
		} 
		
		if(userId != null) {
			User user = MySql.getInstance().getUserQueries().loadUser(TypeConverter.string2int(userId, 0));
			request.getSession().setAttribute("user", user);
		}
		
		return new ModelAndView(VIEW);
	}
	
	@PostMapping(value = "/backend/userwizard/submit")
	public ModelAndView saveUser(final HttpServletRequest request, final HttpServletResponse response) {
		LOOGER.infoLog("UserWizardController: start save User");
		
		String firstName = TextService.getRequestParameter(request, "firstNameWizard", "");
		String lastName = TextService.getRequestParameter(request, "lastNameWizard", "");
		String login = TextService.getRequestParameter(request, "loginWizard", "");
		String password = TextService.getRequestParameter(request, "passwordWizard", "");
		
		boolean success = false;
		if(request.getSession().getAttribute("user") != null) {
			success = MySql.getInstance().getUserQueries().createUser(firstName, lastName, login, password);
		} else {
			return new ModelAndView(BackendService.errorUserLogin(request));
		} 
		
		if(!success) {
			request.setAttribute(ThorbenDierkes.ERROR_MASSAGE, ThorbenDierkes.ERROR_MESSAGE_SQL_ERROR );
		} else {
			request.setAttribute(ThorbenDierkes.SUCCESS_MASSAGE, true);
		}

		return new ModelAndView(SUBMIT_VIEW);
	}


}