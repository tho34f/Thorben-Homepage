package com.thorben.backend.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class WizardData extends BackendControllerData {

    private final String LOGIN = "backend/login";
    private final String LOGIN_REDIRECT = "redirect:/backend/login";
    private final String ERROR_MASSAGE = "errormasage";
    private final String IS_LOGIN_OK = "isLoginOk";

    private String view;
}
