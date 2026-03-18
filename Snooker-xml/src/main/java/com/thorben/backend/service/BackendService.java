package com.thorben.backend.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BackendService {

	private static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
	public static final Random GENERATOR = new Random();
	
	public int generateId() {
		
		return (1 + GENERATOR.nextInt(500));
	}
	
	public static String errorUserLogin(HttpServletRequest request) {
		LOOGER.errorLog("Login Fehler", ThorbenDierkes.USER_NOT_LOGIN);
		request.getSession().setAttribute(ThorbenDierkes.IS_LOGIN_OK, false);
		request.setAttribute(ThorbenDierkes.ERROR_MASSAGE, ThorbenDierkes.USER_NOT_LOGIN);
		return ThorbenDierkes.LOGIN;

	}
	
}