package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDao;
import model.entity.User;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("WEB-INF/jsp/login.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		//ログイン処理
		User loginUser = new UserDao().findByEmailAndPass(email, pass);
		
		//ログイン失敗
		if(loginUser == null) {
			request.setAttribute("errorMsg", "ログイン情報が正しくありません");
			request
			.getRequestDispatcher("WEB-INF/jsp/login.jsp")
			.forward(request, response);
			return;
		}
		
		//ログイン成功
		//ユーザ情報をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loginUser);
		
		//戻り先URLがあればそこにリダイレクト
		String afterUrl = (String)session.getAttribute("afterUrl");
		session.removeAttribute("afterUrl");
		if(afterUrl != null) {
			response.sendRedirect(afterUrl);
			return;
		}
		
		response.sendRedirect("productList");
	}

}
