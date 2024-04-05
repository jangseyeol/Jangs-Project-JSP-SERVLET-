//BoardServlet? command = board_list
//               이름          값
//컨트롤러에 요청하고 싶은 작업인 board_list를 command란 이름에 실어서 서버로 보낸다.
//이처럼, 단 하나의 서블릿을 만들어서 모든 요청이 처리될 수 있도록 원하는 요청은 command란 파라미터에 서로 다른 값을 실어서 보내는 방식을 취한다.
//요청이 일어나면 초기 진입점으로서의 역할도 하고 뷰와 모델을 연동해 주기 때문에 매우 중요한 역할을 한다.

package com.saeyan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.controller.boardAction.BoardAction;
import com.saeyan.controller.boardAction.BoardActionFactory;


@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  //BoardServlet?command=board_list 요청이 전달됨
	//command=board_view & num=${board.num}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
		String command = request.getParameter("command");
		String page = request.getParameter("page");
		
		BoardActionFactory baf = BoardActionFactory.getInstance();
		BoardAction boardAction = baf.getBoardAction(command);
		
		if(boardAction != null) {
			boardAction.execute(request, response);
		}
	} catch(Exception e) {
		HttpSession session = request.getSession();
		String command = (String)session.getAttribute("command");
		
		String page = request.getParameter("page");
		
		BoardActionFactory baf = BoardActionFactory.getInstance();
		BoardAction boardAction = baf.getBoardAction(command);
		
		if(boardAction != null) {
			boardAction.execute(request, response);
		}
	}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post방식으로 호출되면 한글깨짐을 방지하기 위해서 인코딩 방식을 지정한 후에 goGet()메소드를 호출합니다.
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
