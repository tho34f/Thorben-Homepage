package com.thorben.tags;

import com.thorben.service.DateConverter;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateForSlider extends TagSupport{
	
	private static final long serialVersionUID = -5474872956874292025L;
	
	private long dateAsLong;
	private String dateAsString;
	
	@Override
	public int doStartTag() {
		
		try {
			JspWriter out = pageContext.getOut();
			setDateAsString(DateConverter.long2Date(dateAsLong,1));
			out.write(getDateAsString());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
