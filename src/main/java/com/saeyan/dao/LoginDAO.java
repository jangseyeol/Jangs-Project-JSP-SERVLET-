package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.saeyan.dto.BoardVO;
import com.saeyan.dto.LoginVO;

public class LoginDAO {
	
	private static LoginDAO instance = new LoginDAO();
	
	private LoginDAO() {
		
	}

	public static LoginDAO getInstance() {
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
	
	private void lClose(Connection con, Statement stmt, ResultSet rs) {
		try {
			if(rs != null)rs.close();
			if(stmt != null)stmt.close();
			if(con != null)con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//insert, update, delete 수행한 후 리소스 해제를 위한 메소드
	private void lClose(Connection con, Statement stmt) {
		try {
			if(stmt != null)stmt.close();
			if(con != null)con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그인 체크 회원인지 아닌지
	public int userCheck(String id, String pass) {
		String sql = "select * from christian where id = ?";
		int result = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
		    pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				if(rs.getString("pass") != null && rs.getString("pass").equals(pass)) {
						result = 1; //회원
			}else {
				result = 0;  // 비번틀림
			} 
			}else {
				result = -1; //회원아님
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
			lClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	
	//id 해당하는 전체 데이타 가져오기
		public LoginVO getMember(String id) {
			String sql = "select * from christian where id = ?";
			LoginVO vo = new LoginVO();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = getConnection();
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					//      rs.getString("여기이름은 데이타베이스 필드명"
					vo.setId(rs.getString("id"));
					vo.setPass(rs.getString("pass"));
					vo.setName(rs.getString("name"));
					vo.setEnter(rs.getDate("enter"));  //날짜
					vo.setGender(Integer.parseInt(rs.getString("gender")));
					vo.setPhone(rs.getString("phone"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				try {
					lClose(con, pstmt, rs);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return vo;
		}
		
		public int insertMember(LoginVO lVo) {
			int result = -1;
			String sql = "insert into christian(id,pass,name,enter,gender,phone) "
					+ "values(?,?,?,sysdate,?,?)";
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, lVo.getId());
				pstmt.setString(2, lVo.getPass());
				pstmt.setString(3, lVo.getName());
				pstmt.setString(4, "" + lVo.getGender()); //1 ==> "1"
				pstmt.setString(5, lVo.getPhone());
				
				result = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				try {
					lClose(con, pstmt);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}

			return result; 
			
		}
		
		public int updateMember(LoginVO vo) {
			String sql = "update christian set pass=?, name=?, gender=? "
					+ ", phone=? where id=?";
			int result = -1;

			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getPass());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, String.valueOf(vo.getGender()));
				pstmt.setString(4, vo.getPhone());
				pstmt.setString(5, vo.getId());
				
				result = pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				try {
					lClose(con, pstmt);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			return result; 
		}
		
		public int confirmID(String id) {
			int result = -1;
			String sql = "select * from christian where id=?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = 1;
				}else {
					result = -1;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				lClose(con, pstmt, rs);
			}
			return result;
		}
		
		public int dropMember(String id) {
			int result = -1;
			String sql = "delete from christian where id=?";
			
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				
				result = pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				lClose(con, pstmt);
			}
			return result;
		}
}
