package model.service;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.dao.OrderDao;
import model.dao.ProductDao;
import model.entity.CartItem;
import model.entity.Order;
import model.entity.Product;
import model.entity.User;
import util.DBUtil;

/**
 * 注文に関するserviceクラスです
 * 
 * @author kazu-
 */
public class OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	/**
	 * 
	 * 
	 * @param user
	 * @param cart
	 * @throws Exception
	 */
	public void purchase(User user, Map<Integer, CartItem> cart) throws Exception {
		logger.info("購入処理開始：userId={}, 商品数={}", user.getId(), cart.size());
		
		Connection conn = null;
		
		try{
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			OrderDao orderDao = new OrderDao();
			ProductDao productDao = new ProductDao();
			
			//合計金額の算出
			int totalPrice = calcTotalPrice(cart.values());
			
			//注文情報の登録
			int orderId = orderDao.insertOrder(conn, user.getId(), totalPrice);
			
			for(CartItem item : cart.values()) {
				//注文詳細情報の登録
				orderDao.insertOrderDetail(
						conn, 
						orderId, 
						item.getProduct().getId(),
						item.getQuantity(),
						item.getProduct().getPrice()
						);
				//在庫チェック
				int stock = productDao.findById(item.getProduct().getId()).getStock();
				int newStock = stock - item.getQuantity();
				if(newStock < 0) {
					throw new IllegalStateException(item.getProduct().getName() + "の在庫が不足しています");
				}
				//在庫更新
				productDao.updateStock(conn, item.getProduct().getId(), newStock);
			}
			
			conn.commit();
			
			logger.info("購入処理終了：userId={}, 商品数={}", user.getId(), cart.size());

			
		}catch (Exception e) {
			if(conn != null) {
				conn.rollback();
				throw e;
			}
			
		}finally {
			if(conn != null) {
				conn.close();				
			}
		}
	}
	
	
	
	/**
	 * 渡された情報に対応する購入詳細情報を取得
	 * 
	 * @param orderId
	 * @param userId
	 * @return
	 */
	public Order findByOrderIdAndUserId(int orderId, int userId) {
		
		OrderDao orderDao = new OrderDao();
		Order order = orderDao.findByOrderIdAndUserId(orderId, userId);
		List<Product> orderDetailList = orderDao.findDeteilById(orderId, order);
		
		order.setOrderList(orderDetailList);
		
		return order;
		
	}
	
	
	
	public int calcTotalPrice(Collection<CartItem> cart) {
		int total = 0;
		for(CartItem item : cart) {
			total += item.getProduct().getPrice() * item.getQuantity();
		}
		return total;
	}
}