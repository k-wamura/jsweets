package servlet;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
       
	private static final Logger logger = LoggerFactory.getLogger(ProductDetailServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int productId = Integer.parseInt(request.getParameter("id"));
//			System.out.println("取得商品ID：" + productId);
			logger.debug("取得商品ID： {}", productId);
			
			//IDから商品情報を取得
			Product product = new ProductDao().findById(productId);
			
			if(product == null) {
				throw new NumberFormatException();
			}
			
//			System.out.println("商品情報：" + product.toString());//デバッグ用
			logger.debug("取得商品情報： {}", product);
			
			//リクエストスコープに保存
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("product", product);
			
			request
			.getRequestDispatcher("WEB-INF/jsp/productDetail.jsp")
			.forward(request, response);
			
		}catch(NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "無効な商品ID");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
