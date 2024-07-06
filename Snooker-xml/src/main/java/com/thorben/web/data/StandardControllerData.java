package com.thorben.web.data;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class StandardControllerData extends ControllerData {
	
	private int pageReminderNewsList = 1;
	private int pageReminderTerminList = 1;

}
