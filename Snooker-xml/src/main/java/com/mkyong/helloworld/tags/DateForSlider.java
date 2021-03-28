package com.mkyong.helloworld.tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.mkyong.helloworld.service.DateConverter;

public class DateForSlider extends TagSupport{
	
	/**
	 * 
	 */
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

	public long getDateForSLider() {
		return dateAsLong;
	}

	public void setDateForSLider(long dateAsLong) {
		this.dateAsLong = dateAsLong;
	}

	public String getDateAsString() {
		return dateAsString;
	}

	public void setDateAsString(String dateAsString) {
		this.dateAsString = dateAsString;
	}

}
