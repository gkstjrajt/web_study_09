package web_study_09.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import sun.rmi.transport.proxy.HttpReceiveSocket;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("LoginCheckFilter - doFilter()");
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		if(session != null && session.getAttribute("loginUser") != null) {			// 로그인 유무 체크
			chain.doFilter(request, response);										// 로그인 했으면 다음필터
		} else {
			request.getRequestDispatcher("login.do").forward(request, response);// 로그인 안한거니 로그인 페이지로
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
