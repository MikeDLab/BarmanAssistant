package com.labutin.barman.command;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImageUtil {
	private static Logger logger = LogManager.getLogger();
	public ImageUtil() {
		// TODO Auto-generated constructor stub
	}
	public InputStream getInputStream(HttpServletRequest request)
	{
			try {
				return request.getPart(JspParameter.IMAGE.getValue()).getInputStream();
			} catch (IOException | ServletException e) {
				logger.warn("Image input stream exception", e);
				return null;
			}
	}
}
