package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.dao.GuestDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;
import com.javaex.vo.boVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); 	//한글 깨짐 방지
		System.out.println("Boardcontroller");//연결 확인
		String action=request.getParameter("action");
		//데이터가 오는걸 action으로 구분
		System.out.println("action="+action);
		
		if("list".equals(action)){
			//System.out.println("리스트 처리");
			BoardDao bDao = new BoardDao();
			List<boVo> bList= bDao.getList();

			request.setAttribute("bList",bList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp"); //jsp파일 위치를 알려줌
			rd.forward(request, response);
		
		
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
			
		}else if ("read".equals(action)) {//페이지 들어갈 때 마다 조회수가 1 들어나야함 
			System.out.println("게시판 글읽기 처리");
			
				
			int no =Integer.parseInt(request.getParameter("no"));
			
		
			BoardDao boardDao = new BoardDao();
			
			
			boardDao.hitCount(no);
			
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/board/read.jsp");
			rd.forward(request, response);
			response.sendRedirect("/mysite2/board?action=read");
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
			
		}else if ("writeForm".equals(action)) {
			System.out.println("게시글쓰기");
			request.setCharacterEncoding("utf-8");
		
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String regDate = request.getParameter("regDate");
			String name = request.getParameter("name");
			String management = request.getParameter("management");
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/writeForm.jsp");
		
			Object authVo = null;
			System.out.println("글 업로드 실패");
			//리다이렉트 --> 글쓰기 폼
			WebUtil.redirect(request, response, "/mysite2/board?action=writeForm&result=fail");
		}else if("modifyForm".equals(action)) {
			System.out.println("게시글 수정");
			request.setCharacterEncoding("utf-8");
		
			String m_title = request.getParameter("title");
			String m_content = request.getParameter("content");
			String m_regDate = request.getParameter("regDate");
			String m_name = request.getParameter("name");
			String m_management = request.getParameter("management");
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/modifyForm.jsp");
			

			System.out.println("글 업로드 실패");
			//리다이렉트 --> 글쓰기 폼
			WebUtil.redirect(request, response, "/mysite2/board?action=writeForm&result=fail"); 
		}else if("delete".equals(action)) { //삭제기능
			
			System.out.println("게시글 삭제");
					
		
			int no =Integer.parseInt(request.getParameter("no"));
			System.out.println();
			

			BoardDao boardDao = new BoardDao();
			boardDao.boardDelete(no);
			
			System.out.println("게시번호 no:"+no); 
			
	
			WebUtil.redirect(request, response, "/mysite2/board?action=list");
					
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
