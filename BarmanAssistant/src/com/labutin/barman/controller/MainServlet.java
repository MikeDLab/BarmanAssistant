package com.labutin.barman.controller;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig(maxFileSize = 16177215)
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getCommand(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		System.out.println("COMMAND: " + request.getParameter("command"));
		TypeCommandBuilder typeBuilder = new TypeCommandBuilder(request.getParameter("command"));
		TypeCommand commandType = Director.createTypeCommand(typeBuilder);
		logger.info("Command type: " + commandType.getValue());
		CommandBuilder commandBuilder = new CommandBuilder(commandType);
		Command command = Director.createCommand(commandBuilder);
		PageEnum page = command.execute(request, response);
		if (page != null) {
			request.getRequestDispatcher(page.getValue()).forward(request, response);
		}
	}

	protected void getCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TypeCommandBuilder typeBuilder = new TypeCommandBuilder(request.getParameter("command"));
		TypeCommand commandType = Director.createTypeCommand(typeBuilder);
		if (commandType == TypeCommand.SHOW_COCKTAIL_IMAGE) {
			logger.info("Command type: " + commandType.getValue());
			CommandBuilder commandBuilder = new CommandBuilder(commandType);
			Command command = Director.createCommand(commandBuilder);
			PageEnum page = command.execute(request, response);
		}
	}

}
