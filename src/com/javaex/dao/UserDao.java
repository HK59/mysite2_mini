package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// db정보
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	public void close() {
	} 
	// 자원 정리
	

	public UserVo getUser(String id, String pw) {
		// TODO Auto-generated method stub
		UserVo userVo = null;
		getConnection();
		
		try {
			String query ="";
			query +="select no, ";
			query +="       name, ";
			query +=" from users ";
			query +=" where id= ? ";
			query +=" and password = ? ";
			
			PreparedStatement pstat = conn.prepareStatement(query); //쿼리로 만들기
			pstmt.setString(1,  id); // ?(물음표) 중 1번째, 순서 중요
			pstmt.setString(2, pw); // ?(물음표) 중 2번째, 순서 중요
			
			rs= pstmt.executeQuery(); //쿼리문 실행
			
			//결과처리
			while(rs.next()) {
			int id1= rs.getInt("id");
			String password = rs.getString("pw");
			
			userVo = new UserVo(id1, pw);
			}
			
			
		}catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		return userVo;
		}

	public void insert(UserVo vo) {
		// TODO Auto-generated method stub
		
	}

	public UserVo getUser(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(UserVo userVo) {
		// TODO Auto-generated method stub
		
	}


	}

