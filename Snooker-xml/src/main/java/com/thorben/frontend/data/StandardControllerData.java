package com.thorben.frontend.data;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class StandardControllerData extends FrontendControllerData {
	
	private int pageReminderNewsList = 1;
	private int pageReminderTerminList = 1;

}
