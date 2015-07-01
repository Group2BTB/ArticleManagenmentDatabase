package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.User;
import utilities.DBConnection;

public class UserDAO {
	private Connection con;
	public UserDAO() throws Exception{
		con=new DBConnection().getConnection();
	}
	
	public boolean insertView(User udto) throws SQLException{
		PreparedStatement ps=con.prepareStatement("insert into tbl_user(full_name)");
		
		return false;
		
	}
	
	
}
