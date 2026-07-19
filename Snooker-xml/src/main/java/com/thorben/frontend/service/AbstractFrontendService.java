package com.thorben.frontend.service;

import com.thorben.service.ThorbenDierkesLogger;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractFrontendService {

    protected static final ThorbenDierkesLogger LOOGER = new ThorbenDierkesLogger();
}
