package com.labutin.barman.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.CommandInvoker;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.command.TypeCommand;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.pool.PoolConnection;
import com.labutin.barman.repository.IngredientRepository;
import com.labutin.barman.repository.UserRepository;
import com.labutin.barman.specification.FindBarmanSet;
import com.labutin.barman.specification.FindIngredientByName;

/**
 * Servlet implementation class First
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		try {
//			IngredientRepository repository = IngredientRepository.getInstance();
//			Ingredient item = new Ingredient();
//			item.setIngredientName("Vodka");
////			item.setIngredientDescription("Alcohol, vol 40%");
////			repository.remove(item);
//			Set<Ingredient> ingredients = new FindIngredientByName("Vodka").querry();
//			System.out.println(ingredients.size());
//			System.out.println("GOOOD");
//		} catch (NoJDBCDriverException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoJDBCPropertiesFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		String lang = request.getParameter("locale");
		if (lang != null) {
			System.out.println(lang);
			Locale l = new Locale(lang);
			ResourceBundle rb = ResourceBundle.getBundle("resources.locale", l);
			System.out.println(rb.getString("home.page"));
			request.setAttribute("language", lang);
			request.setAttribute("locale", rb);
		}
		logger.info("REDIRECT");
		Command command = null;
		if (request.getParameter("command") != null) {
			logger.info("Command type: " + request.getParameter("command").toUpperCase());
			TypeCommand typeCommand = TypeCommand.valueOf(request.getParameter("command").toUpperCase());
			CommandInvoker invokerCommand = new CommandInvoker();
			command = invokerCommand.getCommand(typeCommand);
		}
		if (command != null) {
			PageEnum page = command.execute(request, response);
			request.getRequestDispatcher(page.getValue()).forward(request, response);
		} else {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
