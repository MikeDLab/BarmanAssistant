package com.labutin.barman.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class InfoTag extends TagSupport {
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		String author = "<p>©<b>Mike Labutin</b> , Epam Training 2018</p>";

		try {
			JspWriter out = pageContext.getOut();
			out.write(author);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
