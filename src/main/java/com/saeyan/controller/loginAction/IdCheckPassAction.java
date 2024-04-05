package com.saeyan.controller.loginAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.LoginDAO;
import com.saeyan.dto.LoginVO;

public class IdCheckPassAction implements LoginAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		System.out.println(id);
		int result = -1;
		
		result = LoginDAO.getInstance().confirmID(id);
		System.out.println(result);
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login/idCheckPass.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
