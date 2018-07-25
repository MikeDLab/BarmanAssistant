package com.labutin.barman.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.service.UserService;

public class UpdateToBarmanCommand implements Command {
	private static Logger logger = LogManager.getLogger();
	private UserService receiver;

	public UpdateToBarmanCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			int userId = Integer.parseInt(request.getParameter("userId"));
			receiver.updateToBarman(userId);
			logger.info("User: " + userId + " upgraded to Barman");
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ShowUserCommand().execute(request, response);

	}

}
