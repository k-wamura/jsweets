package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.ContactItem;
import util.DBUtil;

public class ContactItemDao {

	/**
	 * 問い合わせ項目を全て取得します
	 * 
	 * @return
	 */
	private final String SQL_ALL = "SELECT * FROM contact_item";
	public List<ContactItem> findAll(){
		List<ContactItem> contactItem = new ArrayList<ContactItem>();
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_ALL)){
			
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					ContactItem item = new ContactItem(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("detail")
							);
					
					contactItem.add(item);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return contactItem;
	}
}
