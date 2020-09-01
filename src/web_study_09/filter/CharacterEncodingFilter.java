package web_study_09.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "encoding", value = "UTF-8")
		})
public class CharacterEncodingFilter implements Filter {
	private String enc;
	
	public void destroy() {
		System.out.println("destroy");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here 
		System.out.println("CharacterEncodingFilter - doFilter()");
		request.setCharacterEncoding(enc);
		
		// pass the request along the filter chain 다음 필터로 넘겨주는거
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init(FilterConfig fConfig)");
		enc = fConfig.getInitParameter("encoding");
		if(enc == null) {
			enc = "UTF-8";
		}
	}

}
