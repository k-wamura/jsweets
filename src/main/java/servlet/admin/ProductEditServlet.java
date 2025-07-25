package servlet.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
		
		String ID = request.getParameter("productId");
		logger.debug("id:{}", ID);
		int id = Integer.parseInt(request.getParameter("productId"));
		
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String description = request.getParameter("description");
        
        //画像ファイル取得
        Part imagePart = request.getPart("image");
        logger.debug("imagePart:{}", imagePart);
        
        String newFileName = Path.of(imagePart.getSubmittedFileName()).getFileName().toString();
        logger.debug("newFileName:{}", newFileName);
        String saveFileName = null;
        
        if(newFileName != null && !newFileName.isEmpty()) {
        	saveFileName = System.currentTimeMillis() + "_" + newFileName;
        	logger.debug("saveFileName:{}", saveFileName);
        	
        	//保存先のパス
        	String IMAGE_DIR = "/images";
//        	String savePath = getServletContext().getRealPath(IMAGE_DIR);
        	String savePath = new File("webapp/images").getAbsolutePath();
        	logger.debug("保存ファイル名: {}", saveFileName);
        	logger.debug("保存先パス：{}", savePath);
        	
        	//ファイルの保存
        	imagePart.write(savePath + File.separator + saveFileName);
        }
        
        //商品情報の更新
        ProductDao productDao = new ProductDao();
        Product product = productDao.findById(id);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setDescription(description);
        
        if(saveFileName != null) {
        	product.setImagePath("images/" + saveFileName);
        }
       
        productDao.update(product);
        
        response.sendRedirect("productList");
	}

}
