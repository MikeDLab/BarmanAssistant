package com.labutin.barman.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
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
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.pool.PoolConnection;

/**
 * Servlet implementation class First
 */
@WebServlet(name = "MainServlet", urlPatterns = "/app")
@MultipartConfig(maxFileSize = 16177215)
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger();

	@Override
	public void init() throws ServletException {
		super.init();
		PoolConnection pool = PoolConnection.POOL;
		if (!pool.isCreated().get()) {
			try {
				pool.initialization();
			} catch (NoJDBCDriverException e) {
				logger.fatal(e);
			} catch (NoJDBCPropertiesFileException e) {
				logger.fatal(e);
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter(JspParameter.COMMAND.getValue()) == null) {
			request.getRequestDispatcher(PageEnum.HOME_PAGE.getValue()).forward(request, response);
		} else {
			TypeCommandBuilder typeBuilder = new TypeCommandBuilder(
					request.getParameter(JspParameter.COMMAND.getValue()));
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

	@Override
	public void destroy() {
		super.destroy();
		PoolConnection pool = PoolConnection.POOL;
		if (pool.isCreated().get()) {
			pool.closePool();
		}
	}
}
