package com.saeyan.controller.boardAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.saeyan.dao.BoardDAO;
import com.saeyan.dao.HeavenLoadDAO;
import com.saeyan.dto.BoardVO;
import com.saeyan.dto.HeavenLoadVO;

public class HeavenLoadUpdateAction implements BoardAction {


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset = utf-8");
		
		HeavenLoadVO hVo = new HeavenLoadVO();
		PrintWriter out = response.getWriter();
		
		String savePath = "C:/java/Jsp/jangs-project2/src/main/webapp/upload";
		
		int uploadFileSizeLimit = 5*1024*1024;
		String enType = "utf-8";
		
		MultipartRequest multi = new MultipartRequest(request, savePath, uploadFileSizeLimit, enType, new DefaultFileRenamePolicy());
		
		hVo.setNum(Integer.parseInt(multi.getParameter("num")));
		hVo.setName(multi.getParameter("name"));
		hVo.setPass(multi.getParameter("pass"));
		hVo.setEmail(multi.getParameter("email"));
		hVo.setTitle(multi.getParameter("title"));
		hVo.setContent(multi.getParameter("content"));
		hVo.setPictureUrl(multi.getFilesystemName("pictureUrl"));
		
		HeavenLoadDAO.getInstance().updateHeavenLoadBoard(hVo);
		
		new HeavenLoadListAction().execute(request, response);
	}

}
