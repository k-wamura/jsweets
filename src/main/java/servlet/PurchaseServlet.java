package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.ProductDao;
import model.entity.Product;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		//在庫チェック
		Product product = new ProductDao().findById(productId);
		int stock = product.getStock();
		if(stock <= 0) {
			request.setAttribute("rsultMsg", "在庫切れです");
			
		}else {
			request.setAttribute("resultMsg", "カートに追加しました");
			
			//カートに追加（セッションに保存）
			HttpSession session = request.getSession();
			session.setAttribute("productId", productId);			
		}
		
		request
		.getRequestDispatcher("WEB-INF/jsp/detailResult.jsp")
		.forward(request, response);
		
	}

}
