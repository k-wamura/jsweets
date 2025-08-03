package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.entity.Product;
import util.DBUtil;

/**
 * 商品テーブルに関するDAOクラスです。
 * 
 * @author wamura
 */
public class ProductDao {
	
	private static Logger logger = LoggerFactory.getLogger(ProductDao.class);

	/**
	 * 全ての商品情報を取得
	 * 
	 * @return 全ての商品情報をリストで返す
	 */
	private final String SQL_ALL = "SELECT * FROM product";
	public List<Product> findAll(){
		List<Product> products = new ArrayList<>();
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_ALL)){
			
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					Product product = new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description"),
							rs.getInt("price"),
							rs.getInt("stock"),
							rs.getString("image_path")
							);
					product.setStatus(rs.getInt("status"));
					
					products.add(product);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	
	/**
	 * 全ての商品情報を取得
	 * 
	 * @return 全ての商品情報をリストで返す
	 */
	private final String SQL_SELECT_ACTIVE = "SELECT * FROM product WHERE status = 1";
	public List<Product> findActive(){
		List<Product> products = new ArrayList<>();
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ACTIVE)){
			
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					Product product = new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description"),
							rs.getInt("price"),
							rs.getInt("stock"),
							rs.getString("image_path")
							);
					
					products.add(product);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	
	/**
	 * IDから対応する商品情報を取得
	 * 
	 * @param id
	 * @return IDに対応する商品情報
	 */
	private final String SQL_FIND_ID = "SELECT * FROM product WHERE id=?";
	public Product findById(int id) {
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_FIND_ID)){
			
			pstmt.setInt(1, id);
			
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description"),
							rs.getInt("price"),
							rs.getInt("stock"),
							rs.getString("image_path")
							);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 渡された情報を使用し、在庫の更新を行う
	 * 
	 * @param id 該当商品ID
	 * @param newStock 新しい在庫数
	 */
	private final String SQL_UPDATE_STOCK = "UPDATE product SET stock = ? WHERE id = ?";
	public void updateStock(Connection conn, int id, int newStock) throws SQLException {
		
		try(PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_STOCK)){
			
			pstmt.setInt(1, newStock);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			
		}
	}
	
	
	/**
	 * 渡された情報を使用し、該当する情報を更新します
	 * 
	 * @param product 更新商品情報
	 */
	private final String SQL_UPDATE = "UPDATE product "
			+ "SET name = ?, description = ?, price = ?, stock = ?, image_path = ?"
			+ "WHERE id = ?";
	public void update(Product product) {
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)){
			
			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getDescription());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getStock());
			pstmt.setString(5, product.getImagePath());
			pstmt.setInt(6, product.getId());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 渡された情報を使用し、商品情報を追加します。
	 * 
	 * @param product 新しい商品情報
	 */
	private final String SQL_ADD = "INSERT INTO product(name, price, stock, description, image_path, status)"
									+ "VALUES (?, ?, ?, ?, ?, default)";
	public void addProduct(Product product) throws Exception {
		try (Connection conn = DBUtil.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(SQL_ADD)) {

		        pstmt.setString(1, product.getName());
		        pstmt.setInt(2, product.getPrice());
		        pstmt.setInt(3, product.getStock());
		        pstmt.setString(4, product.getDescription());
		        pstmt.setString(5, product.getImagePath());

		        pstmt.executeUpdate();
		        //成功ログ
		        logger.info("商品名 {} の追加に成功", product.getName());

		    } catch (SQLException e) {
		        e.printStackTrace();
		        //エラーログ
		        logger.error("商品追加エラー", e);
		        throw new Exception();
		    }
	}
	
	
	private final String SQL_UPDATE_STATUS = "UPDATE product SET status = ? WHERE id = ?";
	/**
	 * 渡された情報を使用し、商品の公開状態を変更します
	 * 
	 * @param productId
	 * @param status
	 */
	public void updateStatus(int productId, int status) {
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_STATUS)){
			
			pstmt.setInt(1, status);
			pstmt.setInt(2, productId);
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
