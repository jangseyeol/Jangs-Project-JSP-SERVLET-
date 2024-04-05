package com.saeyan.controller.loginAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.LoginDAO;

public class LoginDropAction implements LoginAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		System.out.println("LoginDropAction >> " + id);
		
		LoginDAO lDao = LoginDAO.getInstance();
		int result = lDao.dropMember(id);
		System.out.println("result : " + result);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('탈퇴가 완료되었습니다.'); location.href='index.jsp';</script>");
		out.flush();
	}

}
