package com.thorben.backend.controller;

import com.thorben.backend.data.BackendControllerData;
import com.thorben.backend.service.AbstractBackendService;
import com.thorben.service.ThorbenDierkesLogger;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractBackendController<D extends BackendControllerData, S extends AbstractBackendService> extends HttpServlet {

    protected static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
    protected static final String SUBMIT_VIEW = "backend/submitWizard";

    protected D controllerData;
    protected S service;
}
