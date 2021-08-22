package com.thorben.helloworld.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	private static final String CONTROLLER_MAPPING = "/WEB-INF/views/jsp/backend/userwizard.jsp";
	
	public UserWizardController() {
		
	}

	@Autowired
	public UserWizardController(ThorbenDierkesService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	
	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		
		String forwordPath = null;
		User user = null;
		String userId = request.getParameter("id");
		
		if(request.getSession().getAttribute("user") != null) {
			forwordPath = CONTROLLER_MAPPING;
			if(userId != null) {
				user = MySql.getInstance().getUserQueries().loadUser(TypeConverter.string2int(userId, 0));
			}
			request.getSession().setAttribute("user", user);
		} else {
			forwordPath = helloWorldService.errorUserLogin(request, true);
		} 
		
		try {
			request.getServletContext().getRequestDispatcher(forwordPath).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		
		String forwordPath = null;
		
		String firstName = request.getParameter("firstNameWizard");
		String lastName = request.getParameter("lastNameWizard");
		String login = request.getParameter("loginWizard");
		String password = request.getParameter("passwordWizard");
		
		if(request.getSession().getAttribute("user") != null) {
			MySql.getInstance().getUserQueries().createUser(firstName, lastName, login, password);
			forwordPath = CONTROLLER_MAPPING;
		} else {
			forwordPath = helloWorldService.errorUserLogin(request, false);
		} 
		
		try {
			request.getServletContext().getRequestDispatcher(forwordPath).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}


}