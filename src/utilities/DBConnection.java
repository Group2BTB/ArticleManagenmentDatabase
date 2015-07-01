package utilities;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

	private Connection con = null;
	/*
	 * @param con to store the connection to database
	 */
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");// load postgresql driver 
			try {
				/* Connect to database */
				con = DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/ArticleManagement",
						"postgres", "12345");
				/*DatabaseMetaData dm = con.getMetaData();
				System.out.println(dm.getDatabaseProductName());*/
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Cannot connect to database!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot load postgresql driver!");
		}

		return con;
	}

}
