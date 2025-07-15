package servlet;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.ProductDao;
import model.entity.CartItem;
import model.entity.Product;


@WebServlet("/buy")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//カート情報の取得
		HttpSession session = request.getSession();
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>)session.getAttribute("cart");
		
		if(cart == null || cart.isEmpty()) {
			response.sendRedirect("cart.jsp");
			return;
		}
		
		ProductDao productDao = new ProductDao();
		
		//在庫チェック
		for(CartItem item : cart.values()) {
			int id = item.getProduct().getId();
			Product product = productDao.findById(id);
			
			//注文数が不正であればエラー表示
			int newStock = product.getStock() - item.getQuantity();
			if(newStock < 0) {
				request.setAttribute("errro", product.getName() + "の在庫が不足しています");
				
				request
				.getRequestDispatcher("cart.jsp")
				.forward(request, response);
			}
			
			//在庫更新
			productDao.updateStock(product.getId(), newStock);
		}
		
		//TODO 購入履歴登録
		
		
		//カートを空にする
		session.removeAttribute("cart");
		
		//購入完了へ
		request
		.getRequestDispatcher("WEB-INF/jsp/buyComplete.jsp")
		.forward(request, response);
		
	}

}
