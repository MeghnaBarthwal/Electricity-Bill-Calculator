package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(" jdbc:mysql://sql6.freemysqlhosting.net/sql6580998", "sql6580998", "BbfHfDpjv9");
	}

}
