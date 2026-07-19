package com.thorben.frontend.contoller;

import com.thorben.frontend.data.FrontendControllerData;
import com.thorben.frontend.service.AbstractFrontendService;
import com.thorben.service.ThorbenDierkesLogger;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractFrontendController<D extends FrontendControllerData, S extends AbstractFrontendService> extends HttpServlet {

    public static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();

    protected D controllerData;
    protected S service;

}
