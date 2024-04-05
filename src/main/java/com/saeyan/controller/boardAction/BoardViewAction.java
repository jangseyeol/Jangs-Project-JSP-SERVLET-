package com.saeyan.controller.boardAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;
import com.saeyan.dto.CommentVO;

public class BoardViewAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardView.jsp";

		String num = request.getParameter("num");
		
		BoardDAO bDao = BoardDAO.getInstance();

		bDao.updateReadCount(num);

		BoardVO bVo = bDao.selectOneBoardByNum(num);
		
		request.setAttribute("board", bVo);
		
		//댓글 읽어오기
		ArrayList<CommentVO> lists = new ArrayList<CommentVO>();
		lists = bDao.comment_list(num);
		request.setAttribute("cmt_list", lists);
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
