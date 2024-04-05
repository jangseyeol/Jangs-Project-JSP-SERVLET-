package com.saeyan.controller.boardAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardSearchAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
	//검색을 위한 변수 searchCondition(검색 조건(제목, 작성자) 
	//검색을 위한 변수 searchKeyword(검색어)
	String searchCondition = request.getParameter("searchCondition");
	String searchKeyword = request.getParameter("searchKeyword");

	BoardDAO bDao = BoardDAO.getInstance();

	//getBoardSerchList 메소드를 통해서 검색했을 때의 데이터를 가지고 와서 SearchList에 넣는다.
	ArrayList<BoardVO> SearchList = bDao.getBoardSearchList(searchCondition,searchKeyword);
	
	String url = "/board/boardSearchList.jsp";
	
	request.setAttribute("SearchList", SearchList);

	
	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	dispatcher.forward(request, response);
	}
	
	
	

}
