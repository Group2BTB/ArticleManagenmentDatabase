package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Article;
import utilities.DBConnection;

public class ArticleDAO implements IArticleDAO {

	private Statement stm = null;
	private DBConnection dbcon = null;
	private Connection con = null;
	private PreparedStatement prestm = null;
	
	
	public ArticleDAO() throws SQLException{
		
		con = DBConnection.getConnection();
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
	
	public ArrayList<Article> searchArticle(String str){
		
		ArrayList<Article> arrList = new ArrayList<Article>();
		
		ResultSet rs = null;
		try {
			stm = con.createStatement();
			String sql = "SELECT * from tbl_article as art join tbl_user on art.id = as.id like '%str%'";
			rs = stm.executeQuery(sql);
			while(rs.next()){
				arrList.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				stm.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return arrList;
	}
	
	public static void main(String[] args) {
		ArrayList<Article> arrList = null;
		
		try {
			ArticleDAO adao = new ArticleDAO();
			arrList = adao.searchArticle("Hello");
			for(Article art : arrList){
				System.out.println(art.getTitle());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
