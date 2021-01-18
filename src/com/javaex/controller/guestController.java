package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;


@WebServlet("/guest")
public class guestController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String action = request.getParameter("action");
				System.out.println(action);
				//한글 깨짐 방지용
				request.setCharacterEncoding("UTF-8");
				
				if("list".equals(action)){
					//System.out.println("리스트 처리");
					GuestDao gDao = new GuestDao();
					List<GuestVo> gList= gDao.getList();

					request.setAttribute("gList",gList);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp"); //jsp파일 위치를 알려줌
					rd.forward(request, response);
				}else if ("add".equals(action)) {
					//System.out.println("등록폼 처리");
					request.setCharacterEncoding("utf-8");
					String id = request.getParameter("id");
					String password = request.getParameter("password");
					String name = request.getParameter("name");
					String gender = request.getParameter("gender");
	
					GuestVo guVo =new GuestVo(id,password,name, gender, null);

					GuestDao guDao = new GuestDao();
					guDao.insert(guVo);
					response.sendRedirect("/mysite2/guestbook?action=addList");
				}else if ("delete".equals(action)) {
					//System.out.println("삭제");
					RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
					rd.forward(request, response);
				}else if ("delete".equals(action)) {
					int no=Integer.parseInt(request.getParameter("no"));
					String password = request.getParameter("password");
					GuestVo guestVo = new GuestVo(no, password);
					GuestDao guestDao = new GuestDao();		
					int er=guestDao.guestDelete(guestVo);
					if(er==1) {
					response.sendRedirect("/mysite2/guestbook?action=addList");
					}else if (er==0){
						RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/passerror.jsp");
						rd.forward(request, response);
					}
				}
				
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}