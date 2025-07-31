package filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {"/buy", "/mypage/*"})
public class LoginFilter extends HttpFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
       
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		logger.debug("ログインフィルター起動");
		
		HttpSession session = request.getSession(false);
		boolean isLogin = session != null 
						&& session.getAttribute("loginUser") != null;//TODO contextPath利用検討
		
		if(isLogin) {
			//通す
			chain.doFilter(request, response);
			
		}else {
			//元のURLをセッションに保存
			session = request.getSession(true);
			String uri = request.getRequestURI();
			String query = request.getQueryString();
			String url = uri + (query != null ? "?" + query : "");
			session.setAttribute("afterUrl", url);
			
			logger.debug("戻り先URL：{}", url);
			
			//ログインページへ
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

}
