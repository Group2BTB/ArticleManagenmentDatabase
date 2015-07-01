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

	@Override
	public boolean insertArticle(Article art) {
		// TODO Auto-generated method stub
		try(Connection con = DBConnection.getConnection(); 
				PreparedStatement prestm = con.prepareStatement("insert into tbl_article(title,author_id,content)values(?,?,?)"); ) {			
			
			prestm.setString(1, art.getTitle());
			prestm.setInt(2,art.getAuthorId());
			prestm.setString(3, art.getContent());
			prestm.executeUpdate();			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
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

	public ArrayList<Article> searchArticle(String str) {

		ArrayList<Article> arrList = new ArrayList<Article>();

		ResultSet rs = null;
		try(Connection con = DBConnection.getConnection();
				Statement stm = con.createStatement();) {
			
			String sql = "SELECT * from tbl_article JOIN tbl_user on tbl_article.author_id = tbl_user.id Where title ~* '.*"+str+".*' or content ~* '.*"+str+".*' or fullname ~* '.*"+str+".*'";
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				arrList.add(new Article(rs.getInt(1), rs.getString(2), rs
						.getInt(3), rs.getString(4)));
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return arrList;
	}

	public static void main(String[] args) {
		ArrayList<Article> arrList = null;

		ArticleDAO adao = new ArticleDAO();
		arrList = adao.searchArticle("a");
		for (Article art : arrList) {
			System.out.println(art.getTitle());
		}

	}

}
