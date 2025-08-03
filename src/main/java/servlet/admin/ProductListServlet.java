package servlet.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ProductDao;
import model.entity.Product;

@WebServlet("/admin/productList")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//商品リストの取得
		List<Product> productList = new ProductDao().findAll();
		request.setAttribute("productList", productList);
		
		request
		.getRequestDispatcher("/WEB-INF/jsp/admin/productList.jsp")
		.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productId = Integer.parseInt(request.getParameter("id"));
		int action = Integer.parseInt(request.getParameter("action"));
		
		//公開状態の更新
		new ProductDao().updateStatus(productId, action);
		
		response.sendRedirect(request.getContextPath() + "/admin/productList");
	}

}
