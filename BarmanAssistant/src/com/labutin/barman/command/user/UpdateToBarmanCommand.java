package com.labutin.barman.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.CommandDecorator;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.UserService;

public class UpdateToBarmanCommand implements Command {
	private UserUtil util = new UserUtil();
	public UpdateToBarmanCommand() {

		// TODO Auto-generated constructor stub
	}

	private static Logger logger = LogManager.getLogger();
	private UserService receiver;
	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new UserService();
			int userId = Integer.parseInt(request.getParameter("userId"));
			receiver.updateToBarman(userId);
			util.showUserSet(request, response);
			logger.info("User: " + userId + " upgraded to Barman");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PageEnum.USER_LIST;
	}

}
