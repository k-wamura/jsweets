package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ProductDao;
import model.entity.Product;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/productDetail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("id"));
		System.out.println("取得ID：" + productId);
		
		//IDから商品情報を取得
		Product product = new ProductDao().findById(productId);
		
		System.out.println(product.toString());//デバッグ用
		
		//リクエストスコープに保存
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("product", product);
		
		request
		.getRequestDispatcher("WEB-INF/jsp/productDetail.jsp")
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
