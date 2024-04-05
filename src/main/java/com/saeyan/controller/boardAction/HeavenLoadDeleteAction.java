package com.saeyan.controller.boardAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dao.HeavenLoadDAO;

public class HeavenLoadDeleteAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num = request.getParameter("num");
		
		HeavenLoadDAO.getInstance().deleteHeavenLoadBoard(num);
		
		response.sendRedirect("BoardServlet?command=heaven_load_list");
	}

}
