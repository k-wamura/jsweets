package servlet.mypage;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Order;
import model.entity.User;
import model.service.OrderService;

/**
 * Servlet implementation class PurchaseHistoryDetail
 */
@WebServlet("/mypage/purchaseHistoryDetail")
public class PurchaseHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//IDがなければ購入履歴画面へリダイレクト
		String orderId = request.getParameter("orderId");
		if(orderId == null) {
			response.sendRedirect(request.getContextPath() + "/mypage/purchaseHistory");
			return;
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		
		//IDに対応する購入情報を取得
		Order order = new OrderService().findByOrderIdAndUserId(Integer.parseInt(orderId), user.getId());
		
		request.setAttribute("order", order);
		
		request
		.getRequestDispatcher("/WEB-INF/jsp/mypage/purchaseHistoryDetail.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
