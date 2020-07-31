package employerinformation;

import java.sql.*;

public class DatabaseConnection {
	public static Connection con = null;
	public static String URL = "jdbc:sqlserver://DESKTOP-R0OLOAE:1433;databaseName=Testing;integratedSecurity=true";
	public static String JDBCDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	// Connection to the Database
	public static Connection getConnection() {

		try {
			Class.forName(JDBCDriver);
			con = DriverManager.getConnection(URL);
		} catch (ClassNotFoundException ex1) {
			System.out.println("Cannot find the driver class" + ex1.getMessage());
			System.exit(1);
		} catch (SQLException ex2) {
			System.out.println("Connection has failed" + ex2.getMessage());
			System.exit(2);
		}
		return con;
	}

	// Method to Close the connection
	public static void closeConnection(Statement stmt, Connection con) {

		if (stmt != null) {

			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
