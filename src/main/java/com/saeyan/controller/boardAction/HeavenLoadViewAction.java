package com.saeyan.controller.boardAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dao.HeavenLoadDAO;
import com.saeyan.dto.HeavenLoadVO;

public class HeavenLoadViewAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/heavenLoadBoard/heavenLoadView.jsp";

		String num = request.getParameter("num");

		HeavenLoadDAO hDao = HeavenLoadDAO.getInstance();

		hDao.updateReadCount(num);
		
		HeavenLoadVO hVo = hDao.selectOneHeavenLoadBoardByNum(num);
		
		request.setAttribute("heavenLoadBoard", hVo);
		
		request.getRequestDispatcher(url).forward(request, response);

		
	}

}
