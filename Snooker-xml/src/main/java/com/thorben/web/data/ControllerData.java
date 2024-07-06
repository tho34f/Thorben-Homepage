package com.thorben.web.data;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class ControllerData {
	
	private String language;
	private Date indexDate = new Date();

}
