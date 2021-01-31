package com.mkyong.helloworld.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mkyong.helloworld.queries.UserQueries;
import com.mkyong.helloworld.service.HelloWorldService;
import com.mkyong.helloworld.snooker.User;

@Controller
public class BackendController {
	
	private final Logger logger = LoggerFactory.getLogger(BackendController.class);
	private final HelloWorldService helloWorldService;
	
	@Autowired
	public BackendController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	
	@RequestMapping(value = "/backend/login", method = RequestMethod.GET)
	public String startLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		request.getSession().setAttribute("user", null);
		return "backend/login";
	}
	
	@RequestMapping(value = "/backend/backendindex", method = RequestMethod.POST)
	public String checkLogin(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		User loginUser = new User(null, null, null, null, 0);
		boolean isLoginOk = false;
		
		String test = request.getParameter("username");
		
		if(request.getParameter("username") != null) {
			loginUser.setUserLogin(test);
			loginUser.setPassword(request.getParameter("password"));
		}
		
		isLoginOk = UserQueries.checkLogin(loginUser);
		request.getSession().setAttribute("isLoginOk", isLoginOk);
		
		if(isLoginOk) {
			request.getSession().setAttribute("user", loginUser);
			
		} else {
			request.setAttribute("errormasage", "Es ist ein Fehler aufgetreten.");
		}
		
		
		
		return "backend/backendindex";
	}

	public HelloWorldService getHelloWorldService() {
		return helloWorldService;
	}

}
