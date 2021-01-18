package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.boVo;

public class BoardDao {
	// 0. import java.sql.*;
			private Connection conn = null;
			private PreparedStatement pstmt = null;
			private ResultSet rs = null;
			// db정보
			private String driver = "oracle.jdbc.driver.OracleDriver";
			private String url = "jdbc:oracle:thin:@localhost:1521:xe";
			private String id = "webdb";
			private String pw = "webdb";
			//db연결
			public void getConnection() {
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

			// 자원 정리
			public void close() {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}
			}
			//게시물 리스트 가져오기
			public List<boVo> getBoradList(){
				
				List<boVo> bList = new ArrayList<boVo>();
				
				//db접속
				getConnection();	
				try {
					
				
				String query="";
				query += " select no, ";
				query += "        title, ";
				query += "        name, ";
				query += "        content, ";
				query += "        hit, ";
				query += "        to_char(reg_date,'DD-MON-RR') reg_date, "; 
				query += "        user_no ";
				query += " from board b , users u ";
				query += " where b.user_no = u.no "; 
				query += " order by b.reg_date desc"; 
				close();
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				
				int count = pstmt.executeUpdate();
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					int no = rs.getInt("no");
					String title = rs.getString("title");
					String name = rs.getString("name");
					String content = rs.getString("content");
					int hit = rs.getInt("hit");
					String reg_date = rs.getString("reg_date");
					int user_no = rs.getInt("user_no");

					
					boVo vo = new boVo(no,title,name ,content ,hit, reg_date,user_no);
					bList.add(vo);
					System.out.println(bList);
				}
					
		
				}catch(SQLException e) {
					System.out.println("error:" + e );
				}
			
				//자원정리
				close();
						
				return bList;
			}
			
			
			
			//한명의 게시물 정보 가져오기 (read 게시물 번호 no값으로 가져오기 게시물번호) 
			
			public boVo getBoardNo(int no) { 
				
				boVo boardVo = null;
				
				getConnection();
				
				
				boVo boVo = null;
				try {
					
				
					String query = "";	
					query += " select no, ";
					query += "        title, ";
					query += "        name, ";
					query += "        content, ";
					query += "        hit, ";
					query += "        to_char(reg_date,'DD-MON-RR') reg_date, "; 
					query += "        user_no ";
					query += " from board b , users u ";
					query += " where b.user_no = u.no "; 
					query += " and b.no = ?"; //게시판번호로 가져오기


					
					pstmt = conn.prepareStatement(query); //쿼리로 만들기
					pstmt.setInt(1, no); //물음표 순서 중요 ,1번째
					
					rs = pstmt.executeQuery(); //쿼리문 실행
					
					//4. 결과처리
					
					// System.out.println("[" +count+ "등록되었습니다.]");
					
					while(rs.next()) {
						int boardno = rs.getInt("no");
						String title = rs.getString("title");
						String name = rs.getString("name");
						String content = rs.getString("content");
						int hit = rs.getInt("hit");
						String reg_date = rs.getString("reg_date");
						int user_no = rs.getInt("user_no");
						
						boVo = new boVo(boardno,title,name ,content ,hit, reg_date,user_no);
					}				
					 
				}catch(SQLException e) {
					System.out.println("error:" + e);
				}
				close();
				
				return boVo;			
			}
			
			
			//조회수 올리기
			
			public int hitCount(int no) {
				
				getConnection();
				
				int count =0;
				
				try {
					
					//3. sql문 준비 /바인딩 /실행
					
					//--게시물 조회수 업데이트
					//--update문
					//update board
					//set hit = hit+1
					//where no = 1;
					
					String query = "";	
					query += " update board ";
					query += " set hit = hit+1 ";
					query += " where no = ? ";
					
					pstmt = conn.prepareStatement(query); //쿼리로 만들기
					pstmt.setInt(1, no); //물음표 순서 중요 ,1번째
					
					count = pstmt.executeUpdate(); //쿼리문 실행(count, rs랑 다르게 쓰는거 잊지말기)
					
					//4. 결과처리
					
					System.out.println("[" +count+ "등록되었습니다.]");
					

					 
				}catch(SQLException e) {
					System.out.println("error:" + e);
				}
				close();
				
				return count;
			}
			
			
			//게시글 삭제
			
			public int boardDelete(int no) {
				
				getConnection();
				
				int count = 0;
				
				try {
					//3.sql문 준비
					/*
					delete문 (데이터 삭제) // 서장훈행 삭제
					delete from person
					where person_id = 5;
				    */
					
					String query ="";
					query += " delete from board ";
					query += " where no = ? ";
					
					//System.out.println(query);
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, no);
					
					count = pstmt.executeUpdate();
					
					//4.결과처리
					
					System.out.println("[" + count + "건 삭제되었습니다.]");
					
				}catch(SQLException e) {
					System.out.println("error:" + e );
				}	
				
				//자원정리
				close();		
				
				return count;
			}
		}