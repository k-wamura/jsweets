package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * データベース接続情報を扱うUtiltyクラスです
 * 
 * @author wamura
 */
public class DBUtil {
	
	private static final String URL = "jdbc:mariadb://localhost/jsweets";
	private static final String USER = "root";
	private static final String PASS = "insource.2015it";
	
	static {
		try {
		//JDBCドライバのロード
		Class.forName("org.mariadb.jdbc.Driver");
		}catch(ClassNotFoundException e) {
		throw new RuntimeException("JDBCドライバのロードに失敗しました。");
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
