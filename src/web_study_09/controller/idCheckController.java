package web_study_09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_study_09.dto.Member;
import web_study_09.service.LoginService;

@WebServlet("/idCheck.do")
public class idCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService service = new LoginService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId").trim();
		Member member = service.getMember(new Member(userId));
		int result = member == null? -1 : 1;
		
		// 기본적으로 매개변수를 전달하거나 매개변수를 받는 내용이 제대로 받는지 체크!!
		System.out.println("userId : " + userId);
		System.out.println("result : " + result);
		
		request.setAttribute("userId", userId);
		request.setAttribute("result", result);
		
		request.getRequestDispatcher("member/idCheck.jsp").forward(request, response);
	}

}
