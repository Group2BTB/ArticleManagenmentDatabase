package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	public static void PassEncrypt(){
		String Password="password";
		String GeneratePass=null;
		try {
			// create MessageDigest for instance md5
			MessageDigest md=MessageDigest.getInstance("md5");
			md.update(Password.getBytes());
			byte[] bytes=md.digest();
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<bytes.length;i++){
				sb.append(Integer.toString((bytes[i]&0xff)+ 0x100,16).substring(1));
			}  
			GeneratePass=sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.print(GeneratePass);
	}
	
	
}
