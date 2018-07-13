package com.labutin.barman.controller;

import java.io.IOException;

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
import com.labutin.barman.pool.PoolConnection;



/**
 * Servlet implementation class First
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("REDIRECT");
		Command command = null;
		logger.info("Command type: " +  request.getParameter("command").toUpperCase());
		TypeCommand typeCommand = TypeCommand.valueOf(request.getParameter("command").toUpperCase());
		CommandInvoker invokerCommand = new CommandInvoker();
		command = invokerCommand.getCommand(typeCommand);
		PageEnum page = command.execute(request, response);
		request.getRequestDispatcher(page.getValue()).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
