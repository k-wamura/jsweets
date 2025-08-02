package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.entity.User;
import util.RegistValidator;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/register")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lName = request.getParameter("lName");
		String fName = request.getParameter("fName");
		String lNameKana = request.getParameter("lNameKana");
		String fNameKana = request.getParameter("fNameKana");
		String password = request.getParameter("password");
		String prefecture = request.getParameter("prefecture");
		String city = request.getParameter("city");
		String oAddress = request.getParameter("oAddress");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		
		User user = new User(lName, fName, lNameKana, fNameKana, password, prefecture, city, oAddress, tel, email);
		List<String> errors = RegistValidator.validate(user);
		
		// エラーがあるかどうかの判定
		if (!errors.isEmpty()) {
		    request.setAttribute("errors", errors);
		    System.out.println("登録処理エラー");
		}else {
			//新規登録処理
			new UserDao().add(user);
			System.out.println("登録処理");
		}
		
	    request.getRequestDispatcher("/WEB-INF/jsp/top.jsp").forward(request, response);
	    return;

	}

}
