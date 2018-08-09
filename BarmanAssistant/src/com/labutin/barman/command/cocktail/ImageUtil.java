package com.labutin.barman.command.cocktail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import javafx.scene.shape.Path;

class ImageUtil {

	public ImageUtil() {
		// TODO Auto-generated constructor stub
	}
	InputStream getInputStream(HttpServletRequest request)
	{
			try {
				return request.getPart("photo").getInputStream();
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				return null;
			}
	}
}
