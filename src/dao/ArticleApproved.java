package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import process.Format;
import utilities.DBConnection;
import dto.Article;

public class ArticleApproved {
	public ArrayList<Article> getArticleApproved(int status) throws SQLException, ParseException{
		ArrayList<Article> arr = new ArrayList<>();
		Connection con = DBConnection.getConnection(); 
		CallableStatement pre = con.prepareCall("{ call select_approved(?) }");
		pre.setInt(1, status);
		ResultSet rs = pre.executeQuery();
		while(rs.next()){
			arr.add(new Article(rs.getInt(1),rs.getString(2),rs.getString(3),Format.formatDate(rs.getString(4))));
		}
		rs.close();
		con.close();
		return arr;
	}
}
