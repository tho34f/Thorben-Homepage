package com.thorben.backend.controller.wizard;

import com.thorben.backend.controller.AbstractBackendController;
import com.thorben.backend.data.WizardData;
import com.thorben.backend.service.BackendService;
import com.thorben.backend.service.wizard.WizardService;
import com.thorben.objects.Termin;
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
@RequestMapping("/backend/terminewizard")
public class TerminWizardController extends AbstractBackendController<WizardData, WizardService> {

	@Serial
    private static final long serialVersionUID = -3295292219817459332L;

	@Autowired
	public TerminWizardController() {
		this.controllerData = new WizardData();
		this.controllerData.setLanguage("de");
		this.controllerData.setView("backend/terminewizard");
		this.service = new WizardService();
	}
	
	@GetMapping
	public ModelAndView createEvent(final HttpServletRequest request) {

		User author = LoginService.getUserfromSession(request);
		if(author == null) {
			return new ModelAndView(this.service.errorUserLogin(request));
		}

		String terminId = request.getParameter("id");
		if(terminId != null) {
			Termin tm = MySql.getInstance().getCalendarQueries().loadCalendar(TypeConverter.string2int(terminId, 0));
			request.getSession().setAttribute("termin", tm);
		}
		
		return new ModelAndView(this.controllerData.getView());
	}
	
	@PostMapping(value = "/submit")
	public ModelAndView saveEvent(final HttpServletRequest request) {
		LOOGER.infoLog("TerminWizardController: start save Event");

		User author = LoginService.getUserfromSession(request);
		if(author == null) {
			return new ModelAndView(this.service.errorUserLogin(request));
		}
		
		String title = TextService.getRequestParameter(request, "titleWizard", "");
		String teaser = TextService.getRequestParameter(request, "teaserWizard", "");
		String description = TextService.getRequestParameter(request, "beschreibungWizard", "");
		
		boolean success  = MySql.getInstance().getCalendarQueries().newCalendarEntry(title, description, teaser, author.getUserLogin(), author.getId());
		if(!success) {
			request.setAttribute(ThorbenDierkes.ERROR_MASSAGE, ThorbenDierkes.ERROR_MESSAGE_SQL_ERROR );
		}else {
			request.setAttribute(ThorbenDierkes.SUCCESS_MASSAGE, true);
		}

		return new ModelAndView(SUBMIT_VIEW);
	}

}