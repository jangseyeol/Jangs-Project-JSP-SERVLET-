package com.saeyan.controller.boardAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dao.HeavenLoadDAO;
import com.saeyan.dto.BoardVO;
import com.saeyan.dto.HeavenLoadVO;

public class HeavenLoadCheckPassAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		
		String url = null;
		
		HeavenLoadVO hVo = HeavenLoadDAO.getInstance().selectOneHeavenLoadBoardByNum(num);

		if(hVo.getPass().equals(pass)) {
			url = "/heavenLoadBoard/heavenLoadCheckSuccess.jsp";
		}else {
			url = "/heavenLoadBoard/HeavenLoadCheckPass.jsp";
			request.setAttribute("message", "비밀번호가 틀립니다.");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
