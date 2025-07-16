package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.ProductDao;
import model.entity.CartItem;
import model.entity.Product;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/addcart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(AddCartServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//現在の在庫数を取得
		Product product = new ProductDao().findById(productId);
		int stock = product.getStock();
		
		//在庫チェック
		if(product.getStock() < quantity) {
			request.setAttribute("error", "在庫が足りません");
			
			request
			.getRequestDispatcher("productDetail?id=" + productId)
			.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>)session.getAttribute("cart");
		
		//カートがなにもなければ新しく生成
		if(cart == null) {
			cart = new LinkedHashMap<Integer, CartItem>();
			logger.debug("カート作成");
		}
		
		//カートに同商品があればその個数にプラス
		if(cart.containsKey(productId)) {
			CartItem item = cart.get(productId);
			int newQuantity = item.getQuantity() + quantity;
			
			//在庫を超える場合は最大値を設定
			if(newQuantity > product.getStock()) {
				newQuantity = product.getStock();
				logger.debug("カート追加：最大値（{}）", newQuantity);
			}
			
			//デバッグ用
			logger.debug("カートに追加：{}", product);
			logger.debug("数量：{}", newQuantity);
			
			item.setQuantity(newQuantity);
			
		}else {
			//カートに新規追加
			cart.put(productId, new CartItem(product, quantity));
			logger.debug("カートに新規追加");
		}
		
		//再設定を明示的に記述
		session.setAttribute("cart", cart);
		
		response.sendRedirect("cart.jsp");
	}

}
