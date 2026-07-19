package com.thorben.backend.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Component
public abstract class BackendControllerData {

    private String language;
    private Date indexDate = new Date();
}
