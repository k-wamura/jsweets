package servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.CartItem;
import model.entity.User;
import model.service.OrderService;


@WebServlet("/buy")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("WEB-INF/jsp/buyConfirm.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//カート情報の取得
		HttpSession session = request.getSession();
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>)session.getAttribute("cart");
		
		if(cart == null || cart.isEmpty()) {
			response.sendRedirect("cart.jsp");
			return;
		}
		
		//TODO 購入履歴登録
		try {
			User loginUser = (User)session.getAttribute("loginUser");
			new OrderService().purchase(loginUser, cart);
			
		}catch(IllegalStateException e) {
			request.setAttribute("errorMsg", e.getMessage());
			
			request
			.getRequestDispatcher("WEB-INF/jsp/buyComplete.jsp")
			.forward(request, response);
			return;
			
		}catch(Exception e) {
			request.setAttribute("errorMsg", "購入処理でエラーが発生しました。");
			
			request
			.getRequestDispatcher("WEB-INF/jsp/buyComplete.jsp")
			.forward(request, response);
			return;
			
		}
		
		//カートを空にする
		session.removeAttribute("cart");
		
		//購入完了へ
		request
		.getRequestDispatcher("WEB-INF/jsp/buyComplete.jsp")
		.forward(request, response);
		
	}

}
