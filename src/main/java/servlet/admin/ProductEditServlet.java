package servlet.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.dao.ProductDao;
import model.entity.Product;

/**
 * Servlet implementation class ProductEditServlet
 */
@WebServlet("/admin/productEdit")
@MultipartConfig
public class ProductEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductListServlet.class);

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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
		
			String ID = request.getParameter("productId");
			logger.debug("id:{}", ID);
			int productId = Integer.parseInt(request.getParameter("productId"));
			
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			String description = request.getParameter("description");
		
		try {			
			
			//画像ファイル取得
			Part imagePart = request.getPart("image");
			String submittedFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
			String saveFileName = null;
			
			if(submittedFileName != null && !submittedFileName.isEmpty()) {
				saveFileName = System.currentTimeMillis() + "_" + submittedFileName;
				//画像アップロード
				String path = "C:\\pleiades\\2023-12\\workspace\\Jsweets\\src\\main\\webapp\\images";//開発用パス
				imagePart.write(path + File.separator + saveFileName);
				logger.debug("画像ファイル保存：{}", path + File.separator + saveFileName);
			}
			//画像パスの取得
			String imagePath = (saveFileName != null) ? "images/" + saveFileName : null;
			logger.debug("保存ファイル名：{}", saveFileName);
			
			
			//データベース更新
			ProductDao productDao = new ProductDao();
			Product product = productDao.findById(productId);
			
			product.setName(name);
			product.setPrice(price);
			product.setStock(stock);
			product.setDescription(description);
			if(saveFileName != null) {
				product.setImagePath(imagePath);        	
			}
			productDao.update(product);
			
			response.sendRedirect("productList");
			return;
			
		}catch(IOException e) {
			logger.error("FileNotFoundException : {}", e);
			request.setAttribute("errorMsg","ファイルを保存できませんでした。");

			Product product = new ProductDao().findById(productId);
			request.setAttribute("product", product);
			
			request
			.getRequestDispatcher("/WEB-INF/jsp/admin/productEdit.jsp")
			.forward(request, response);
		}
	}

}
