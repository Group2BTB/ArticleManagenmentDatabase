package utilities;

//import java.sql.DatabaseMetaData;
//import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

	private static Connection con = null;
	/*
	 * @param con to store the connection to database
	 */

	public static Connection getConnection(){
		
		try {
			Class.forName("org.postgresql.Driver");// load postgresql driver
			/* Connect to database */

			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ArticleManagement","postgres", "12345");

			/// DatabaseMetaData dm = con.getMetaData();
			//System.out.println(dm.getDatabaseProductName());
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot connect to database!");
		}catch(ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Cannot load driver!");
		}

		return con;
	}
	/*public static void main(String[] args) throws SQLException {
		getConnection();
	}*/

}
