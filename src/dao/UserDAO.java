package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.User;
import utilities.DBConnection;

public class UserDAO {
	
	public boolean insertView(User udto) {

		try(Connection con=new DBConnection().getConnection();
			PreparedStatement ps=con.prepareStatement("insert into tbl_user(full_name, email, username, passwd ,type)"
			+ "values(?, ?, ?, ?, ?)");){
			
			ps.setString(1, udto.getFullName());
			ps.setString(2, udto.getEmail());
			ps.setString(3, udto.getUsername());
			ps.setString(4, udto.getPassword());
			ps.setString(5, udto.getType());
			
			if(ps.executeUpdate()>0){
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	

	public boolean getUpdateFullname(User udto){
		try(Connection con=new DBConnection().getConnection();
			PreparedStatement ps=con.prepareStatement("update tbl_user set full_name=? where id=?");){
			ps.setString(1, udto.getFullName());
			ps.setInt(2, udto.getId());
			if(ps.executeUpdate()>0){
				return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
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
	}
	
	
}
