package com.thorben.backend.controller.wizard;

import com.thorben.backend.controller.AbstractBackendController;
import com.thorben.backend.data.WizardData;
import com.thorben.backend.service.BackendService;
import com.thorben.backend.service.wizard.WizardService;
import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.LoginService;
import com.thorben.service.TextService;
import com.thorben.service.ThorbenDierkes;
import com.thorben.service.TypeConverter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serial;


@Controller
@RequestMapping("/backend/userwizard")
public class UserWizardController extends AbstractBackendController<WizardData, WizardService> {

	@Serial
    private static final long serialVersionUID = -3295292219817459332L;

	@Autowired
	public UserWizardController() {
		this.controllerData = new WizardData();
		this.controllerData.setLanguage("de");
		this.controllerData.setView("backend/userwizard");
		this.service = new WizardService();
	}

	@GetMapping
	public ModelAndView createUser(final HttpServletRequest request) {
		LOOGER.infoLog("UserWizardController: start create User");

		User author = LoginService.getUserfromSession(request);
		if(author == null) {
			return new ModelAndView(this.service.errorUserLogin(request));
		}

		String userId = request.getParameter("id");
		if(userId != null) {
			User user = MySql.getInstance().getUserQueries().loadUser(TypeConverter.string2int(userId, 0));
			request.getSession().setAttribute("user", user);
		}
		
		return new ModelAndView(this.controllerData.getView());
	}
	
	@PostMapping(value = "/submit")
	public ModelAndView saveUser(final HttpServletRequest request) {
		LOOGER.infoLog("UserWizardController: start save User");

		User author = LoginService.getUserfromSession(request);
		if(author == null) {
			return new ModelAndView(this.service.errorUserLogin(request));
		}
		
		String firstName = TextService.getRequestParameter(request, "firstNameWizard", "");
		String lastName = TextService.getRequestParameter(request, "lastNameWizard", "");
		String login = TextService.getRequestParameter(request, "loginWizard", "");
		String password = TextService.getRequestParameter(request, "passwordWizard", "");
		
		boolean success = MySql.getInstance().getUserQueries().createUser(firstName, lastName, login, password);
		if(!success) {
			request.setAttribute(ThorbenDierkes.ERROR_MASSAGE, ThorbenDierkes.ERROR_MESSAGE_SQL_ERROR );
		} else {
			request.setAttribute(ThorbenDierkes.SUCCESS_MASSAGE, true);
		}

		return new ModelAndView(SUBMIT_VIEW);
	}

}