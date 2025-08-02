package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	private final String SQL_FIND_LOGIN = "SELECT * FROM users WHERE email = ? AND password = ?";
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
							rs.getTimestamp("created_at").toLocalDateTime(),
							rs.getInt("role_id")
							);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 渡された情報を使用し、新規登録処理
	 * 
	 * @param user
	 */
	private final String SQL_INSERT = "INSERT INTO users(l_name, f_name, l_name_kana, f_name_kana, password, prefecture, city, o_address, tel, email, created_at)"
									+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public void add(User user) {
		try(Connection conn = DBUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)){
			
			pstmt.setString(1, user.getlName());
			pstmt.setString(2, user.getfName());
			pstmt.setString(3, user.getlNameKana());
			pstmt.setString(4, user.getfNameKana());
			pstmt.setString(5, user.getPassword());
			pstmt.setString(6, user.getPrefecture());
			pstmt.setString(7, user.getCity());
			pstmt.setString(8, user.getoAddress());
			pstmt.setString(9, user.getTel());
			pstmt.setString(10, user.getEmail());
			pstmt.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
