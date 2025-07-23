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
import model.entity.User;


@WebFilter("/admin/*")
public class AdminFilter extends HttpFilter {
	
	private static Logger logger = LoggerFactory.getLogger(AdminFilter.class);
       
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//管理者権限のチェック
		HttpSession session = request.getSession(false);
		User loginUser = (session != null) ? (User)session.getAttribute("loginUser") : null;
		
		if(loginUser != null && loginUser.getRole() == 2) {
			//管理者認証成功
			chain.doFilter(request, response);
		}else {
			logger.warn("管理者権限がないユーザによるアクセス：{}", request.getRequestURI());
			
			//元のURLをセッションに保存
			session = request.getSession(true);
			String uri = request.getRequestURI();
			String query = request.getQueryString();
			String url = uri + (query != null ? "?" + query : "");
			session.setAttribute("afterUrl", url);
			
			logger.debug("戻り先URL：{}", url);
			
			//ログインページへリダイレクト
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

}
