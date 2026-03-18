package com.thorben.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.thorben.service.DateConverter;
import com.thorben.service.ThorbenDierkesLogger;
import com.thorben.web.data.ControllerData;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class MathController extends HttpServlet {
	
	private static final long serialVersionUID = 4441260446740040597L;
	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	
	private final ControllerData controllerData;
	
	@Autowired
    public MathController() {
        this.controllerData = new ControllerData();
        this.controllerData.setLanguage("de");
    }

	@GetMapping(value = "/math")
	public String start(Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
		LOOGER.infoLog("MathController: start()");
		DateConverter.setDateFooter(this.controllerData.getIndexDate(), request);
		return "math/math";
	}

}