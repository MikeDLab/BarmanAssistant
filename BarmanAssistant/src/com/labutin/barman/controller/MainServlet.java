package com.labutin.barman.controller;

import java.io.IOException;
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
import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.command.TypeCommand;

/**
 * Servlet implementation class First
 */
@MultipartConfig(maxFileSize = 16177215)
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter(JspParameter.COMMAND.getValue()) == null) {
			request.getRequestDispatcher(PageEnum.HOME_PAGE.getValue()).forward(request, response);
		} else {
			TypeCommandBuilder typeBuilder = new TypeCommandBuilder(request.getParameter(JspParameter.COMMAND.getValue()));
			TypeCommand commandType = Director.createTypeCommand(typeBuilder);
			logger.info("Command type: " + commandType.getValue());
			CommandBuilder commandBuilder = new CommandBuilder(commandType);
			Command command = Director.createCommand(commandBuilder);
			PageEnum page = command.execute(request, response);
			if (page != null) {
				request.getRequestDispatcher(page.getValue()).forward(request, response);
			}
		}
	}
}
