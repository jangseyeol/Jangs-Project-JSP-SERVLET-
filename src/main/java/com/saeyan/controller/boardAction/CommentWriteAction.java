package com.saeyan.controller.boardAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.CommentVO;

public class CommentWriteAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CommentVO cVo = new CommentVO();
		
		cVo.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		cVo.setContent(request.getParameter("comment_content"));
		cVo.setId(request.getParameter("comment_id"));
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.insertComment(cVo);
		
		response.sendRedirect(request.getHeader("referer"));
	}

}
