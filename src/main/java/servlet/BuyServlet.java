package servlet;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static Logger logger = LoggerFactory.getLogger(BuyServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Object cart = session.getAttribute("cart");
		if(cart == null) {
			response.sendRedirect("cart");
			return;
		}
		
		request
		.getRequestDispatcher("WEB-INF/jsp/buyConfirm.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//カート情報の取得
		HttpSession session = request.getSession();
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>)session.getAttribute("cart");
		
		if(cart == null || cart.isEmpty()) {
			response.sendRedirect("cart");
			return;
		}
		
		int orderId = 0;
		
		try {
			User loginUser = (User)session.getAttribute("loginUser");
			orderId = new OrderService().purchase(loginUser, cart);
			request.setAttribute("orderId", orderId);
			
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
		request.setAttribute("totalPrice", session.getAttribute("totalPrice"));
		request.setAttribute("cart", cart);
		
		for(CartItem item : cart.values()) {
			logger.debug("購入完了画面用カート情報；{}", item);			
		}
		
		//カートを空にする
		session.removeAttribute("cart");
		session.removeAttribute("totalPrice");
		
		//購入完了へ
		request
		.getRequestDispatcher("WEB-INF/jsp/buyComplete.jsp")
		.forward(request, response);
		
	}

}
