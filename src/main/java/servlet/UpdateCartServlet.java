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

/**
 * Servlet implementation class UpdateCart
 */
@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>)session.getAttribute("cart");
		int productId = Integer.parseInt(request.getParameter("id"));
		String updateType = request.getParameter("action");
		
		//カート更新処理
		switch(updateType) {
		
			case "increase": {
				CartItem item = cart.get(productId);
				if(item != null) {
					item.setQuantity(item.getQuantity() + 1);
				}
				break;
			}
			
			case "decrease": {
				CartItem item = cart.get(productId);
				if(item != null) {
					if(item.getQuantity() > 1) {
						item.setQuantity(item.getQuantity() - 1);
					}else {
						cart.remove(productId);
					}
				}
				break;
			}
			
			case "remove" : {
				cart.remove(productId);
			}
			
			default:
				break;
		}
		
		response.sendRedirect("cart");
	}

}
