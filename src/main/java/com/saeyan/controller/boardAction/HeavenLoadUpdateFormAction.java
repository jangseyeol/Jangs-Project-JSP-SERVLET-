package com.saeyan.controller.boardAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dao.HeavenLoadDAO;
import com.saeyan.dto.BoardVO;
import com.saeyan.dto.HeavenLoadVO;

public class HeavenLoadUpdateFormAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "heavenLoadBoard/heavenLoadUpdate.jsp";
		
		String num = request.getParameter("num");
		
		HeavenLoadDAO hDao = HeavenLoadDAO.getInstance();
		
		HeavenLoadVO hVo = hDao.selectOneHeavenLoadBoardByNum(num);
		
		
		
		request.setAttribute("heavenLoad", hVo);
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
