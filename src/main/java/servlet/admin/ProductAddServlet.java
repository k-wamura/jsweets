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


@WebServlet("/admin/productAdd")
@MultipartConfig
public class ProductAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductListServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("/WEB-INF/jsp/admin/productAdd.jsp")
		.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//パラメータの取得
		request.setCharacterEncoding("UTF-8");
		String pname = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String description = request.getParameter("description");
		
		//画像の取得
		Part part = request.getPart("image");
		String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		String saveFileName = null;
		
		if(fileName != null && !fileName.isEmpty()) {
			saveFileName = System.currentTimeMillis() + "_" + fileName;
			//画像アップロード
        	String path = "C:\\pleiades\\2023-12\\workspace\\Jsweets\\src\\main\\webapp\\images";//開発用パス
        	part.write(path + File.separator + fileName);
		}
		//画像パスの取得
		String imagePath = (fileName != null) ? "images/" + fileName : null;
        logger.debug("保存ファイル名：{}", saveFileName);
		
        //DB更新
        ProductDao productDao = new ProductDao();
        Product product = new Product(
        		pname,
        		description,
        		price,
        		stock,
        		imagePath
        		);
        try {
        	productDao.addProduct(product);
        	request.setAttribute("errorMsg", "商品の追加処理が正常に完了しました");
        }catch(Exception e) {
        	request.setAttribute("errorMsg", "商品の追加処理でエラーが発生しました");
        }
        
        response.sendRedirect("admin/productAdd");
	}

}
