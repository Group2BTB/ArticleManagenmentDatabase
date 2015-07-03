package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import process.Format;
import utilities.DBConnection;
import dto.Article;

public class Pagination {
	public static int page=1;
	public static int perpage=10;
	public static ArrayList<Article> getArticle(String field,String orderTo,int stop) throws SQLException, ParseException{
		ArrayList<Article> arr = new ArrayList<>();
		Connection con = DBConnection.getConnection(); 
		CallableStatement pre = con.prepareCall("{ call selectall(?,?,?,?) }");
		pre.setString(1, field);
		pre.setString(2, orderTo);
		pre.setInt(3, perpage);
		pre.setInt(4, stop);
		ResultSet rs = pre.executeQuery();
		while(rs.next()){
			arr.add(new Article(rs.getInt(1),rs.getString(2),rs.getString(3),Format.formatDate(rs.getString(4))));
		}
		rs.close();
		con.close();
		return arr;
	}
	public static void main(String[] args) throws SQLException, ParseException {
		ArrayList<Article> arr = Pagination.getArticle("id", "DESC", 0);
		System.out.println(arr.get(0).getDate());
	}
}
