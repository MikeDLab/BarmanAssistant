package com.labutin.barman.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	PageEnum execute(HttpServletRequest request,HttpServletResponse response);
}
