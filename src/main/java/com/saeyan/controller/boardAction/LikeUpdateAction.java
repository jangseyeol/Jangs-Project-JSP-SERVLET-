package com.saeyan.controller.boardAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.saeyan.dao.BoardDAO;

public class LikeUpdateAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int bno = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.update_Like(bno);
		
		int like = bDao.select_Like(bno);
		
		JSONObject obj = new JSONObject();
		obj.put("like", like);
		
		response.setContentType("application/x-json; charset=utf-8");
		response.getWriter().print(obj);
	}

}
