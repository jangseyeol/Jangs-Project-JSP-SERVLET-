package com.saeyan.controller.boardAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardUpdateAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset = utf-8");
		
		BoardVO bVo = new BoardVO();
		PrintWriter out = response.getWriter();
		
		String savePath = "C:/java/Jsp/jangs-project2/src/main/webapp/upload";
		
		int uploadFileSizeLimit = 5*1024*1024;
		String enType = "utf-8";
		
		MultipartRequest multi = new MultipartRequest(request, savePath, uploadFileSizeLimit, enType, new DefaultFileRenamePolicy());
		
		bVo.setNum(Integer.parseInt(multi.getParameter("num")));
		bVo.setName(multi.getParameter("name"));
		bVo.setPass(multi.getParameter("pass"));
		bVo.setEmail(multi.getParameter("email"));
		bVo.setTitle(multi.getParameter("title"));
		bVo.setContent(multi.getParameter("content"));
		bVo.setPictureUrl(multi.getFilesystemName("pictureUrl"));
		
		BoardDAO.getInstance().updateBoard(bVo);
		
		new BoardListAction().execute(request, response);
	}

}
