package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.saeyan.dto.BoardVO;
import com.saeyan.dto.CommentVO;

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {
		
	}

	public static BoardDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;	
	}
	
	//select 수행한 후 리소스 해제를 위한 메소드
	private void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if(rs != null)rs.close();
			if(stmt != null)stmt.close();
			if(con != null)con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//insert, update, delete 수행한 후 리소스 해제를 위한 메소드
	private void close(Connection con, Statement stmt) {
		try {
			if(stmt != null)stmt.close();
			if(con != null)con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//게시판의 전체 글 수를 가져오는 메소드
	public int getBoardCount() {
		String sql = "select count(*) from board";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		int boardCount = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardCount = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
		return boardCount;
	}
	
	// 전체 데이터 가져오기 (page = 현재페이지, pageSize= 페이지당 글 개수)
	public ArrayList<BoardVO> selectAllBoards(int page, int pageSize){
		
		int startNum = ((page-1)*pageSize)+1;
		int endNum = page*pageSize;
		
		ArrayList<BoardVO> lists = new ArrayList<BoardVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * ");
		sql.append(" from ( ");
		sql.append(" select rownum rno,num,pass,name,email,title,content,readcount,writedate,comment_count");
		sql.append(" from ( ");
		sql.append(" select num,pass,name,email,title,content,readcount,writedate,comment_count");
		sql.append(" from board ");
		sql.append(" order by ref desc,STEP desc ) )");
		sql.append(" where rno >=? and rno <=?");
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setPass(rs.getString("pass"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				bVo.setComment_count(rs.getInt("comment_count"));
				
				lists.add(bVo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return lists;
	}
	
	
	//단건데이터 가져오기
	public BoardVO selectOneBoardByNum(String num){
		BoardVO bVo = new BoardVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board where num = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
		//한개 넘어오기때문에
			if(rs.next()) {
				bVo.setNum(rs.getInt("num"));
				bVo.setPass(rs.getString("pass"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				bVo.setPictureUrl(rs.getString("img"));
				bVo.setLike_it(rs.getInt("like_it"));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return bVo;
	}
	
	//패스워드 체크
	public BoardVO checkPassWord(String pass, int num){
		BoardVO bVo = new BoardVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board where pass =? and num = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				bVo.setNum(rs.getInt("num"));
				bVo.setPass(rs.getString("pass"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return bVo;
	}
	
	//조회수 증가
	public void updateReadCount(String num) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		String sql = "update board set readcount = readcount+1 where num = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}
	
	//게시글 삽입
	public void insertBoard(BoardVO bVo) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into board(num,pass,name,email,title,content,img,ref)"
				+ "values(board_seq.nextval,?,?,?,?,?,?,board_seq.nextval)";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bVo.getPass());
			pstmt.setString(2, bVo.getName());
			pstmt.setString(3, bVo.getEmail());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setString(6, bVo.getPictureUrl());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}
	
	//글 수정
	public void updateBoard(BoardVO bVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update board set name=?, pass=?, email=?, title=?, content=?, img=? where num=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			System.out.println("updateBoard === >" + sql);
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getPass());
			pstmt.setString(3, bVo.getEmail());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setString(6, bVo.getPictureUrl());
			pstmt.setInt(7, bVo.getNum());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}

	//게시글 삭제
	public void deleteBoard(String num) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from board where num = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}

	//답글쓰기
	public void replyBoard(BoardVO bVo) {
		
		int idx = bVo.getNum(); //해당 글의 번호를 가지고 온다. idx로 설정
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			int indent = 0; // indent는 원글과 구분하기 위한 번호 처음에는 0으로 초기화
			
			con = getConnection();
			stmt = con.createStatement();
			
			//1. 부모글에서 ref, indent, step 가져오기
			String sql = "SELECT REF, INDENT, STEP FROM board WHERE NUM=" + idx; 
			//답문을 쓰기 위한 글의 ref(참조할 번호) indent(원글 +1), step:게시판 순서에 따라 계속증가)
			
			rs= stmt.executeQuery(sql);
			
			if(rs.next()) {
				indent = rs.getInt("indent");
			}
			
			//2.답글 입력
			//ref 부모글 번호
			//step 하나의 글에 답글 여러개 달렸들 때 답글 순서를 위한 변수
			sql = "insert into board(num,pass,name,email,title,content,img,ref,indent,step)"
					+ "values(board_seq.nextval,?,?,?,?,?,?,?,?,board_seq.nextval)";
			pstmt = con.prepareStatement(sql);
			System.out.println("답글 sql" + sql);
			pstmt.setString(1, bVo.getPass());
			pstmt.setString(2, bVo.getName());
			pstmt.setString(3, bVo.getEmail());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setString(6, bVo.getPictureUrl());
			pstmt.setInt(7, idx);
			pstmt.setInt(8, indent+1);
			
			pstmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		
		}finally {
			close(con, stmt);
			
		}
	}
	

	
	
	public ArrayList<BoardVO> getBoardSearchList(String searchCondition, String searchKeyword) {
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			String sql = "select * from board";
			//키워드 검색창에 뭔가 입력이 되었을 때
			// 여기에서 test입력했을 때 그것만 나오게 할지...?
			//아니면 test를 포함하는 모든 것을 검색되게 해야 할지...?
			if(searchKeyword != null && !searchKeyword.equals("")) {
				sql +=" where "+searchCondition.trim()+" like '%"+searchKeyword.trim()+"%' order by num";
				//sql +=" where "+searchCondition.trim()+" like '"+searchKeyword.trim()+"' order by num";
			//입력 창에 아무 것도 입력하지 않으면 모든 것을 표시하게
			}else{
				sql += " order by num";
			}
			//위에서 실행된 sql문이 뭔지를...
            System.out.println("검색할 때 쓰여진 sql = " + sql);
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setPass(rs.getString("pass"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				bVo.setPictureUrl(rs.getString("img"));
				bVo.setLike_it(rs.getInt("like_it"));
				
				list.add(bVo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return list;
	
	}

	//댓글 쓰기
	public void insertComment(CommentVO cVo) {
		String sql = "insert into board_comment(comment_num, board_num, id, content) "
				+ "values(comment_seq.nextval,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int commentCount=0;
		
		try {
			//1.댓글쓰기
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cVo.getBoard_num());
			pstmt.setString(2, cVo.getId());
			pstmt.setString(3, cVo.getContent());
			
			pstmt.executeUpdate();
			
			//2.댓글 갯수 구하기
			sql = "select count(*) from board_comment where board_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cVo.getBoard_num());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				commentCount = rs.getInt(1); //전체 글 수
			}
			//3. board table에 댓글 갯수 업데이트 하기 - > 리스트에서 댓글 몇개인지 보여주기 위함
			sql = "update board set comment_count=? where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentCount);
			pstmt.setInt(2, cVo.getBoard_num());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(conn, pstmt);
			
		}
	}

	//댓글 리스트 읽어오기
	public ArrayList<CommentVO> comment_list(String num){
		String sql = "select * from board_comment where board_num=?";
		
		ArrayList<CommentVO> lists = new ArrayList<CommentVO>();
		CommentVO cVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, num); //게시글 번호 에 맞는 게시글 찾아온 다음
			rs = pstmt.executeQuery(); //sql 실행값을 rs에 저장
			
			while(rs.next()) {
				cVo = new CommentVO();
				cVo.setComment_num(rs.getInt("comment_num"));
				cVo.setBoard_num(rs.getInt("board_num"));
				cVo.setId(rs.getString("id"));
				cVo.setContent(rs.getString("content"));
				cVo.setWritedate(rs.getTimestamp("writedate"));
				
				lists.add(cVo);
						
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return lists;
	}

	public void update_Like(int bno) {
		String sql = "update board set like_it=like_it+1 where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int like=0;
		try {
			conn =getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				like = rs.getInt("like_it");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt);
		}
	}
	
	//좋아요 개수 찾기
	public int select_Like(int bno) {
		String sql = "select like_it from board where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int like=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				like = rs.getInt("like_it");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		return like;
	}
}
