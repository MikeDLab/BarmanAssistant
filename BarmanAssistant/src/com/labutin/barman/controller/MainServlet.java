package com.labutin.barman.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.builder.CommandBuilder;
import com.labutin.barman.builder.Director;
import com.labutin.barman.builder.TypeCommandBuilder;
import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.command.TypeCommand;
import com.labutin.barman.entity.User;

/**
 * Servlet implementation class First
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lang = request.getParameter("locale");
		logger.info("Locale used: " + lang);
		if (lang != null) {
			Locale l = new Locale(lang);
			ResourceBundle rb = ResourceBundle.getBundle("resources.locale", l);
			request.getSession(true).setAttribute("language", lang);
			request.getSession(true).setAttribute("locale", rb);
		}
		response.sendRedirect(PageEnum.HOME_PAGE.getValue());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		TypeCommandBuilder typeBuilder = new TypeCommandBuilder(request.getParameter("command"));
		TypeCommand commandType = Director.createTypeCommand(typeBuilder);
		CommandBuilder commandBuilder = new CommandBuilder(commandType);
		Command command = Director.createCommand(commandBuilder);
		PageEnum page = command.execute(request, response);
		request.getRequestDispatcher(page.getValue()).forward(request, response);
		logger.info("Command type: " + commandType.getValue());
	}

}
