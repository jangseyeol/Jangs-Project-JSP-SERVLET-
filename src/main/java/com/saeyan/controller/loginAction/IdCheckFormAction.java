package com.saeyan.controller.loginAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.LoginDAO;

public class IdCheckFormAction implements LoginAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		LoginDAO lDao = LoginDAO.getInstance();
		
		int result = lDao.confirmID(id);
		
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		
		String url = "/login/idCheckPass.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
		
	
	}

}
