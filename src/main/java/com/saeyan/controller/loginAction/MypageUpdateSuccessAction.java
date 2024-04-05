package com.saeyan.controller.loginAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.LoginDAO;
import com.saeyan.dto.LoginVO;

public class MypageUpdateSuccessAction implements LoginAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String url = null;
		
		LoginVO lVo = new LoginVO();
		lVo.setPass(pass);
		lVo.setName(name);
		lVo.setGender(Integer.parseInt(gender));
		lVo.setPhone(phone);
		lVo.setId(id);
		
		LoginDAO lDao = LoginDAO.getInstance();
		int result = lDao.updateMember(lVo);
		HttpSession session = request.getSession();
		
		if(result == 1) {
			session.setAttribute("id", lVo.getId());
			request.setAttribute("message", "회원정보수정이 완료되었습니다.");
			url = "index.jsp";
		}else {
			request.setAttribute("message", "회원정보수정이 실패했습니다.");
			url = "mypageUpdate.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
