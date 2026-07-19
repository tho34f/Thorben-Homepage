package com.thorben.backend.service;

import com.thorben.service.ThorbenDierkes;
import com.thorben.service.ThorbenDierkesLogger;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public abstract class AbstractBackendService {

    protected static final ThorbenDierkesLogger LOGGER = new ThorbenDierkesLogger();
    protected static final Random GENERATOR = new Random();

    public int generateId() {
        return (1 + GENERATOR.nextInt(500));
    }

    public String errorUserLogin(HttpServletRequest request) {
        LOGGER.errorLog("Login Fehler", ThorbenDierkes.USER_NOT_LOGIN);
        request.getSession().setAttribute(ThorbenDierkes.IS_LOGIN_OK, false);
        request.setAttribute(ThorbenDierkes.ERROR_MASSAGE, ThorbenDierkes.USER_NOT_LOGIN);
        return ThorbenDierkes.LOGIN;
    }

    public boolean clearInformationAndSetError(final HttpServletRequest request) {
        request.getSession().setAttribute("errorMessage", ThorbenDierkes.ERROR_MESSAGE_NO_ELEMENTS);
        return true;
    }
}
