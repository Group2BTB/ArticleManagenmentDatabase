package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dto.Article;
import utilities.DBConnection;

public class ArticleDAO implements IArticleDAO {

	private Statement stm = null;
	private DBConnection dbcon = null;
	private Connection con = null;
	private PreparedStatement prestm = null;
	
	public ArticleDAO(){
		dbcon = new DBConnection();
		con = dbcon.getConnection();
	}
	
	@Override
	public boolean insertArticle(Article art) {
		// TODO Auto-generated method stub
		try {
			stm = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateArticle(Article art) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteArticle(Article art) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
