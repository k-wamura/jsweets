package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.entity.Order;
import model.entity.Product;
import util.DBUtil;

/**
 * ordersテーブルとorder_detailテーブルを扱うDAOクラスです。
 * 
 * @author kazu-
 */
public class OrderDao {
	private static Logger logger = LoggerFactory.getLogger(OrderDao.class);

	private final String SQL_INSERT_ORDERS = "INSERT INTO orders(user_id, total_price) VALUES(?, ?)";
	/**
	 * 渡された情報を使用し、注文情報を保存します
	 * 
	 * @param conn
	 * @param userId
	 * @param totalPrice
	 * @return 保存した件数
	 */
	public int insertOrder(Connection conn, int userId, int totalPrice) throws SQLException {
		int orderId = -1;

		try (PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_ORDERS, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, totalPrice);
			pstmt.executeUpdate();

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					orderId = rs.getInt("order_id");
				}
			}

		}
		return orderId;
	}
	
	

	private final String SQL_INSERT_ORDER_DETAIL = "INSERT INTO order_detail(order_id, product_id, quantity, price) VALUES(?, ?, ?, ?)";
	/**
	 * 渡された情報から注文詳細情報を保存
	 * 
	 * @param conn
	 * @param orderId
	 * @param productId
	 * @param quantity
	 * @param price
	 * @throws SQLException
	 */
	public void insertOrderDetail(Connection conn, int orderId, int productId, int quantity, int price)
			throws SQLException {

		try (PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_ORDER_DETAIL)) {
			pstmt.setInt(1, orderId);
			pstmt.setInt(2, productId);
			pstmt.setInt(3, quantity);
			pstmt.setInt(4, price);
			pstmt.executeUpdate();
		}
	}
	
	/**
	 * 渡されたユーザIDに対応する注文情報を全て取得します。
	 * 注文情報がない場合空のリストを返します。
	 * 
	 * @return 注文情報リスト
	 */
	private final String SQL_FIND_ALL = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";
	public List<Order> findById(int userId){
		List<Order> orderList = new ArrayList<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分");
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_FIND_ALL)){
			
			pstmt.setInt(1, userId);
			
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					
					
					Order order = new Order(
							rs.getInt("order_id"),
							rs.getInt("user_id"),
							dtf.format(rs.getTimestamp("order_date").toLocalDateTime()),
							rs.getInt("total_price")
							);
					
					orderList.add(order);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return orderList;
	}
	
	
	
	/**
	 * 渡された購入IDとユーザIDに対応する購入情報を取得
	 * 
	 * @param orderId
	 * @return
	 */
	private final String SQL_FIND_ORDERID = "SELECT * FROM orders WHERE order_id = ? AND user_id = ?";
	public Order findByOrderIdAndUserId(int orderId, int userId) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分");
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_FIND_ORDERID)){
			
			pstmt.setInt(1, orderId);
			pstmt.setInt(2, userId);
			
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return new Order(
							rs.getInt("order_id"),
							rs.getInt("user_id"),
							dtf.format(rs.getTimestamp("order_date").toLocalDateTime()),
							rs.getInt("total_price")
							);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 渡された購入IDに対応する購入詳細情報を取得
	 * 
	 * @param productId
	 * @return
	 */
	private final String SQL_FIND_DETAIL = """
			SELECT * FROM order_detail
			INNER JOIN 
			product
			ON
			order_detail.product_id = product.id
			WHERE order_detail.order_id = ?
			""";
	public List<Product> findDeteilById(int orderId, Order order){
		List<Product> orderDetaliList = new ArrayList<Product>();
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_FIND_DETAIL)){
			
			pstmt.setInt(1, orderId);
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
					product.setQuantity(rs.getInt("quantity"));
					orderDetaliList.add(product);
					logger.debug("購入履歴商品情報：{}", product);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return orderDetaliList;
	}
	
	
}
