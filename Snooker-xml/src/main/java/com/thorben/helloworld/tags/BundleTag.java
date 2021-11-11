package com.thorben.helloworld.tags;

import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.thorben.helloworld.service.BundleTranslator;

public class BundleTag extends TagSupport{
	
	/**
	 * 
	 */
	private String value;
	
	private static final long serialVersionUID = -5474872956874292026L;
	
	  @Override
	  public int doStartTag() throws JspException {
			try {
				JspWriter out = pageContext.getOut();
				String language = Locale.getDefault().getLanguage();
				BundleTranslator bt = new BundleTranslator(language);
				String valueInActualLanguage = bt.translatBundle(getValue());
				out.write(valueInActualLanguage);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
	    return EVAL_BODY_INCLUDE;
	  }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
