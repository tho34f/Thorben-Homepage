package com.thorben.helloworld.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.thorben.helloworld.service.DateConverter;
import com.thorben.helloworld.service.ThorbenDierkesService;


@Controller
public class MathController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3295292219817459332L;

	private ThorbenDierkesService helloWorldService = new ThorbenDierkesService();
	
	private static Date indexDate = new Date();
	
	public MathController() {
		
	}

	@Autowired
	public MathController(ThorbenDierkesService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@GetMapping(value = "/math")
	public String start(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		
		DateConverter.setDateFooter(indexDate, request);
		
		return "math";
	}
	

	public ThorbenDierkesService getHelloWorldService() {
		return helloWorldService;
	}


}