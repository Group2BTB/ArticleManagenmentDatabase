package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import dto.Article;
import utilities.DBConnection;

public class ArticleDAO implements IArticleDAO {
	
	private ArrayList<Article> arrList = null;
	
	public Article checkValid(int id){
		
		
		Article art = new Article();
		ResultSet rs = null;
//		art.setAuthorId(11);
//		art.setContent("content1");
//		art.setId(1);
//		art.setTitle("title1");
//		if(art.getId()==1)
//			return art;
//		System.exit(0);
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement pre = con.prepareStatement("select * from tbl_article where id=?",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);){
			pre.setInt(1,id);
			if((rs = pre.executeQuery()).last()){
				
				art.setAuthorId(rs.getInt("author_id"));
				art.setContent(rs.getString("content"));
				art.setTitle(rs.getString("title"));
				art.setId(id);
				return art;
			}
			return null;
			
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;			
		}		
	}

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
	public boolean updateArticle(int id, Article art) {
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement prestm = con.prepareStatement("update tbl_article set title=?, author_id=?,content=? where id="+id+"")){
			prestm.setString(1, art.getTitle());
			prestm.setInt(2, art.getAuthorId());
			prestm.setString(3, art.getContent());
			prestm.executeUpdate();
			return true;
		}catch(SQLException ex){
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean deleteArticle(int id) {
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement prestm = con.prepareStatement("delete  from tbl_article where id=?");){
			prestm.setInt(1,id);
			prestm.executeUpdate();
			return true;
		}catch(SQLException ex){
			ex.printStackTrace();
			return false;
		}
		
	} 

	public ArrayList<Article> searchArticle(String str) {

		this.arrList = new ArrayList<Article>();

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

}
