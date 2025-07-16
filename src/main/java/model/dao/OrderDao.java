package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ordersテーブルとorder_detailテーブルを扱うDAOクラスです。
 * 
 * @author kazu-
 */
public class OrderDao {

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
}
