package list.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import board.model.BoardDAO;
import board.model.BoardVo;



@WebServlet("/SearchListController")
public class SearchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		String bwriter = request.getParameter("bwriter");
		response.getWriter().write(jsonadd(bwriter));
	}
	
    public String jsonadd(String userName) {
		String json = "";
    	BoardDAO boardDAO = new BoardDAO();
		ArrayList<BoardVo> boardList = new ArrayList<>();
		boardList = boardDAO.searchBoard(userName);
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
        JSONArray innerList = new JSONArray();
        JSONObject outer = new JSONObject();
       
        
        for(int i = 0 ; i< boardList.size() ; i++) {
        	String bdate = transFormat.format(boardList.get(i).getBdate());
        	JSONObject inner = new JSONObject();
        	inner.put("bid", boardList.get(i).getBid());
        	inner.put("btitle",boardList.get(i).getBtitle()); 
            inner.put("bwriter", boardList.get(i).getBwriter()); 
            inner.put("bdate", bdate); 
            inner.put("hit",boardList.get(i).getHit());
            System.out.println("inner : "+inner);
            innerList.add(i, inner);
        }
        System.out.println("innerList : "+innerList);
        // JSON객체를 String으로 변환
        outer.put("inf", innerList);
        json += outer.toJSONString(); 
        System.out.println("생성된 제이슨 : "+json);

        return json;
	}

}
