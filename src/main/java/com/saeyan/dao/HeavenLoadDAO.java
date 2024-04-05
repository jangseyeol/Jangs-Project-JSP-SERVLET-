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
import com.saeyan.dto.HeavenLoadVO;

public class HeavenLoadDAO {
	
	private static HeavenLoadDAO instance = new HeavenLoadDAO();
	
	private HeavenLoadDAO() {
		
	}

	public static HeavenLoadDAO getInstance() {
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
	public int getHeavenLoadCount() {
		String sql = "select count(*) from heavenload";
		
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
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
		return boardCount;
	}
	
	// 전체 데이터 가져오기 (page = 현재페이지, pageSize= 페이지당 글 개수)
	public ArrayList<HeavenLoadVO> selectAllHeavenLoadBoards(int page, int pageSize){
		
		int startNum = ((page-1)*pageSize)+1;
		int endNum = page*pageSize;
		
		ArrayList<HeavenLoadVO> lists = new ArrayList<HeavenLoadVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select * ");
		sql.append(" from ( ");
		sql.append(" select rownum rno,num,pass,name,email,title,content,readcount,writedate");
		sql.append(" from ( ");
		sql.append(" select num,pass,name,email,title,content,readcount,writedate");
		sql.append(" from heavenload ");
		sql.append(" order by ref desc,STEP asc ) )");
		sql.append(" where rno >=? and rno <=?");
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				HeavenLoadVO hVo = new HeavenLoadVO();
				hVo.setNum(rs.getInt("num"));
				hVo.setPass(rs.getString("pass"));
				hVo.setName(rs.getString("name"));
				hVo.setEmail(rs.getString("email"));
				hVo.setTitle(rs.getString("title"));
				hVo.setContent(rs.getString("content"));
				hVo.setReadcount(rs.getInt("readcount"));
				hVo.setWritedate(rs.getTimestamp("writedate"));
				
				lists.add(hVo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return lists;
	}
	
	/*
	 * //조회수 증가 메소드 public void updateReadCount(String num) { String sql =
	 * "update board set readcount = readcount+1 where num=?";
	 * 
	 * Connection con = null; PreparedStatement pstmt = null;
	 * 
	 * try { con = getConnection(); pstmt = con.prepareStatement(sql);
	 * pstmt.setString(1, num);
	 * 
	 * pstmt.executeUpdate();
	 * 
	 * }catch(Exception e) { e.printStackTrace() }finally { close(con, pstmt); }
	 * 
	 * }
	 */
	
	//단건데이터 가져오기
	public HeavenLoadVO selectOneHeavenLoadBoardByNum(String num){
		HeavenLoadVO hVo = new HeavenLoadVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from heavenload where num = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
		//한개 넘어오기때문에
			if(rs.next()) {
				hVo.setNum(rs.getInt("num"));
				hVo.setPass(rs.getString("pass"));
				hVo.setName(rs.getString("name"));
				hVo.setEmail(rs.getString("email"));
				hVo.setTitle(rs.getString("title"));
				hVo.setContent(rs.getString("content"));
				hVo.setReadcount(rs.getInt("readcount"));
				hVo.setWritedate(rs.getTimestamp("writedate"));
				hVo.setPictureUrl(rs.getString("img"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return hVo;
	}
	
	//패스워드 체크
	public HeavenLoadVO checkPassWord(String pass, int num){
		HeavenLoadVO hVo = new HeavenLoadVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from heavenload where pass =? and num = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				hVo.setNum(rs.getInt("num"));
				hVo.setPass(rs.getString("pass"));
				hVo.setName(rs.getString("name"));
				hVo.setEmail(rs.getString("email"));
				hVo.setTitle(rs.getString("title"));
				hVo.setContent(rs.getString("content"));
				hVo.setReadcount(rs.getInt("readcount"));
				hVo.setWritedate(rs.getTimestamp("writedate"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return hVo;
	}
	
	//조회수 증가
	public void updateReadCount(String num) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		String sql = "update heavenload set readcount = readcount+1 where num = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}
	
	//게시글 삽입
	public void insertHeavenLoadBoard(HeavenLoadVO hVo) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into heavenload(num,pass,name,email,title,content,img,ref) "
				+ " values(heavenload_seq.nextval,?,?,?,?,?,?,heavenload_seq.nextval)";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hVo.getPass());
			pstmt.setString(2, hVo.getName());
			pstmt.setString(3, hVo.getEmail());
			pstmt.setString(4, hVo.getTitle());
			pstmt.setString(5, hVo.getContent());
			pstmt.setString(6, hVo.getPictureUrl());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}
	
	//글 수정
	public void updateHeavenLoadBoard(HeavenLoadVO hVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "update heavenload set name=?, pass=?, email=?, title=?, content=?, img=? where num=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hVo.getName());
			pstmt.setString(2, hVo.getPass());
			pstmt.setString(3, hVo.getEmail());
			pstmt.setString(4, hVo.getTitle());
			pstmt.setString(5, hVo.getContent());
			pstmt.setString(6, hVo.getPictureUrl());
			pstmt.setInt(7, hVo.getNum());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}

	//게시글 삭제
	public void deleteHeavenLoadBoard(String num) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from heavenload where num = ?";
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

}
