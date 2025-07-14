package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.Product;
import util.DBUtil;

/**
 * 商品テーブルに関するDAOクラスです。
 * 
 * @author wamura
 */
public class ProductDao {

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
	
	
	
}
