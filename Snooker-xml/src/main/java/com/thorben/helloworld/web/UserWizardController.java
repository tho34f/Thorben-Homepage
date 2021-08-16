package com.thorben.helloworld.web;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.thorben.helloworld.queries.MySql;
import com.thorben.helloworld.service.ThorbenDierkesService;
import com.thorben.helloworld.service.TypeConverter;
import com.thorben.helloworld.snooker.User;


@Controller
public class UserWizardController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3295292219817459332L;
	private ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	
	public UserWizardController() {
		
	}

	@Autowired
	public UserWizardController(ThorbenDierkesService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@GetMapping(value = "/backend/userwizard")
	public String creatNews(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		
		String forwordPath = null;
		User user = null;
		String userId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = "backend/userwizard";
			if(userId != null) {
				user = MySql.getInstance().getUserQueries().loadUser(TypeConverter.string2int(userId, 0));
			}
			request.getSession().setAttribute("user", user);
		} else {
			forwordPath = helloWorldService.errorUserLogin(request);
		} 
		
		return forwordPath;
	}
	
	@PostMapping(value = "/backend/userwizard")
	public String setNews(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		String forwordPath = null;
		
		String firstName = request.getParameter("firstNameWizard");
		String lastName = request.getParameter("lastNameWizard");
		String login = request.getParameter("loginWizard");
		String password = request.getParameter("passwordWizard");
		
		if(request.getSession().getAttribute("user") != null) {
			MySql.getInstance().getUserQueries().createUser(firstName, lastName, login, password);
			forwordPath = "backend/userwizard";
		} else {
			forwordPath = helloWorldService.errorUserLogin(request);
		} 
		
		return forwordPath;
	}
	

	public ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}


}