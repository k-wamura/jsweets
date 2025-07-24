package servlet.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ProductDao;
import model.entity.Product;

/**
 * Servlet implementation class ProductEditServlet
 */
@WebServlet("/admin/productEdit")
public class ProductEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("id") != null) {
			int productId = Integer.parseInt(request.getParameter("id"));
			
			Product product = new ProductDao().findById(productId);
			request.setAttribute("product", product);	
			
			request
			.getRequestDispatcher("/WEB-INF/jsp/admin/productEdit.jsp")
			.forward(request, response);
		}else {
			response.sendRedirect("/WEB-INF/jsp/admin/admin.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
