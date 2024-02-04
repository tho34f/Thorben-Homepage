package com.thorben.tags;

import java.util.Locale;

import com.thorben.tags.service.BundleTranslator;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BundleTag extends TagSupport{
	
	private static final long serialVersionUID = -5474872956874292026L;
	
	private String value;
	
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

}
