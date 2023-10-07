package com.thorben.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

import com.thorben.objects.User;
import com.thorben.queries.MySql;
import com.thorben.service.ThorbenDierkesService;
import com.thorben.service.TypeConverter;


@Controller
public class UserWizardController extends HttpServlet {

	private static final long serialVersionUID = -3295292219817459332L;
	private static ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	private static final String CONTROLLER_MAPPING = "/WEB-INF/views/jsp/backend/userwizard.jsp";
	private static String language;
	
	
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
			forwordPath = ThorbenDierkesService.errorUserLogin(request, true);
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
			forwordPath = ThorbenDierkesService.errorUserLogin(request, false);
		} 
		
		try {
			request.getServletContext().getRequestDispatcher(forwordPath).forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}

	public static String getLanguage() {
		return language;
	}

	public static void setLanguage(String language) {
		UserWizardController.language = language;
	}


}