package com.labutin.barman.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.LoadFileCommand;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.command.cocktail.AddCocktailCommand;
import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.CocktailService;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.UserService;

/**
 * Servlet implementation class First
 */

@MultipartConfig(maxFileSize = 16177215)
public class ImageServlet extends HttpServlet {

	private CocktailService receiverCocktail;
	private UserService receiverUser;
	private IngredientService receiverIngredient;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cocktailId = Integer.parseInt(request.getParameter("imageId"));
		try {
			receiverCocktail = new CocktailService();
			Cocktail cocktail = receiverCocktail.receiveCocktailById(cocktailId);
			try {
				byte[] img = cocktail.getImage().readAllBytes();
				response.setContentType("image/jpeg");
				OutputStream os = response.getOutputStream();
				os.write(img);
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

}
