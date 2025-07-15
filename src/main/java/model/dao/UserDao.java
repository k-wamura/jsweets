package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.User;
import util.DBUtil;

/**
 * Userテーブルに関するDAOクラスです
 * 
 * @author kazu-
 */
public class UserDao {
	
	/**
	 * 渡された情報から対応するユーザ情報を返します
	 * 
	 * @param email
	 * @param pass
	 * @return ユーザ情報(情報がない場合null)
	 */
	private final String SQL_FIND_LOGIN = "SELECT * FROM user WHERE email = ? AND password = ?";
	public User findByEmailAndPass(String email, String pass) {
		
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_FIND_LOGIN)){
			
			pstmt.setString(1, email);
			pstmt.setString(2, pass);
			
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return new User(
							rs.getInt("id"),
							rs.getString("l_name"),
							rs.getString("f_name"),
							rs.getString("l_name_kana"),
							rs.getString("f_name_kana"),
							rs.getString("password"),
							rs.getString("prefecture"),
							rs.getString("city"),
							rs.getString("o_address"),
							rs.getString("tel"),
							rs.getString("email"),
							rs.getTimestamp("created_at").toLocalDateTime()
							);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
