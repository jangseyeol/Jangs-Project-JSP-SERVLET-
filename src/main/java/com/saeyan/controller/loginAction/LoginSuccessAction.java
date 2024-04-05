package com.saeyan.controller.loginAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.LoginDAO;
import com.saeyan.dto.LoginVO;

public class LoginSuccessAction implements LoginAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "index.jsp";
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		LoginDAO lDao = LoginDAO.getInstance();
		int result = lDao.userCheck(id, pass);
		
		
		if(result==1) {
			LoginVO login_user_info = lDao.getMember(id);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", login_user_info);
			request.setAttribute("message", "로그인에 성공했습니다.");
			
			url = "main.jsp";
		}else if(result == 0){
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}else if(result == -1) {
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		

		
	}


}
