package com.javaex.controller.notice;

import java.util.List;

import com.javaex.controller.BoardController;

public class BoardService {
	public List<BoardController>getBoardList(){

	
	return getBoardList("title", "", 1);
	}


public List<BoardController>getBoardList(int page){

	
	return getBoardList("title", "", page);
	}


public List<BoardController>getBoardList(String field, String query, int page){

	String sql= "select*from(" + 
			" select rownum num, bo.* " + 
			" from(select*from board order by no desc)bo" + 
			") " + 
			"where rownum between 6 and 10";
	
		return null;
	}

public int getBoardCount(){

return getBoardCount("title", "");
}

public int getBoardCount(String field, String query) {
	String sql= "select*from(" + 
			" select rownum num, bo.* " + 
			" from(select*from board order by no desc)bo" + 
			") " + 
			"where rownum between 6 and 10";
	
	return 0;

}

public BoardService getBoard(int id) {
	String sql = "select*from notice where id=?";
	
	return null;
}

public BoardService getNextBoard(int id) {
	String sql ="select*from board " + 
			"    where no =( " + 
			"    select no from board " + 
			"    where reg_date >(select reg_date from BOARD where no=13) " + 
			"    AND ROWNUM = 1 " + 
			" ) ";
	return null;
}

public BoardService getPrevBoard(int id) {
	
	return null;
}

}