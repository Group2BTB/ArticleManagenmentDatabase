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
	public static int perpage=3;
	public static ArrayList<Article> getArticleAll(String field,String orderTo,int stop) throws SQLException, ParseException{
		ArrayList<Article> arr = new ArrayList<>();
		Connection con = DBConnection.getConnection(); 
		CallableStatement pre = con.prepareCall("{ call select_all(?,?,?,?) }");
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
	public static int countSelectAll() throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement pre = con.prepareCall("{ call count_select_all() }");
		ResultSet rs = pre.executeQuery();
		while(rs.next()){
			return rs.getInt(1);			
		}
		return 0;
	}
	public ArrayList<Article> getArticleBySearch(String value,String searchBy, String field,String orderTo,int stop) throws SQLException, ParseException{
		ArrayList<Article> arr = new ArrayList<>();
		Connection con = DBConnection.getConnection(); 
		CallableStatement pre=null;
		switch (searchBy) {
		case "id":
			pre = con.prepareCall("{ call search_by_id(?,?,?,?,?) }");
			pre.setInt(1, Integer.parseInt(value));
			break;
		case "title":
			pre = con.prepareCall("{ call searchbytitle(?,?,?,?,?) }");
			pre.setString(1, value);
			break;
		case "author":
			pre = con.prepareCall("{ call search_by_author(?,?,?,?,?) }");
			pre.setString(1, value);
			break;		
		}
				
		pre.setString(2, field);
		pre.setString(3, orderTo);
		pre.setInt(4, perpage);
		pre.setInt(5, stop);
		ResultSet rs = pre.executeQuery();
		while(rs.next()){
			arr.add(new Article(rs.getInt(1),rs.getString(2),rs.getString(3),Format.formatDate(rs.getString(4))));
		}
		rs.close();
		con.close();
		return arr;
	}
	public static int countSearchBy(String value,String searchBy) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement pre=null;
		switch (searchBy) {		
		case "title": 
			pre = con.prepareCall("{ call count_search_by_title(?) }");			
			break;
		case "author":System.out.println('d');
			pre = con.prepareCall("{ call count_search_by_author(?) }");			
			break;		
		default:
			pre = con.prepareCall("{ call count_search_all(?) }");
			break;
		}
		pre.setString(1, value);		
		ResultSet rs = pre.executeQuery();
		while(rs.next()){			
			return rs.getInt(1);			
		}
		rs.close();
		con.close();
		return 0;
	}
	public static int calculatePage(int totalRecord){
		return (int) Math.ceil((totalRecord/(float)perpage));			
	}
	public static int startIndex(){
		return (page-1)*perpage;
	}
	public static void next(int totalPage){
		if(page!=totalPage)
			page++;			
	}
	public static void previous(){
		if(page!=1)
			page--;
	}
	public static void first(){
		page=1;
	}
	public static void last(int totalPage){
		page=totalPage;
	}
	public static void setRow(int num){
		perpage=num;
		page=1;
	}
}
