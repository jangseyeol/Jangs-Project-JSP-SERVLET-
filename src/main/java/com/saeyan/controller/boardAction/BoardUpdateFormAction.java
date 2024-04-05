package com.saeyan.controller.boardAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardUpdateFormAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/boardUpdate.jsp";
		
		String num = request.getParameter("num");
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		BoardVO bVo = bDao.selectOneBoardByNum(num);
		
		
		
		request.setAttribute("board", bVo);
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
