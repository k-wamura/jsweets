package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import model.entity.Contact;
import util.DBUtil;

public class ContactDao {

	private final String SQL_INSERT = "INSERT INTO contact(contact_item_id, name, prefecture, mail, message, created_at, status)"
									+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
	/**
	 * 渡された問い合わせ情報を保存します
	 * 
	 * @param contact
	 */
	public void add(Contact contact) {
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)){
			
			pstmt.setInt(1, contact.getContactItemId());
			pstmt.setString(2, contact.getName());
			pstmt.setString(3, contact.getPrefecture());
			pstmt.setString(4, contact.getMail());
			pstmt.setString(5, contact.getMessage());
			pstmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setString(7, "未対応");
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}


}
