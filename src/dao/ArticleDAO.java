package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

import dto.Article;
import utilities.DBConnection;

public class ArticleDAO implements IArticleDAO {
	
	
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

	public ArrayList<Article> searchArticle(String str, String field, String order, int numRow, int startRow) {

		ArrayList<Article> arrList = new ArrayList<Article>();

		ResultSet rs = null;
		try(Connection con = DBConnection.getConnection();) {
			//Statement stm = con.createStatement();
			//String sql = "SELECT * from tbl_article JOIN tbl_user on tbl_article.author_id = tbl_user.id Where title ~* '.*"+str+".*' or content ~* '.*"+str+".*' or fullname ~* '.*"+str+".*'";
			CallableStatement callStm = con.prepareCall("{ call search_all( ?, ?, ?, ?, ?) }");
			callStm.setString(1, str);
			callStm.setString(2, field);
			callStm.setString(3, order);
			callStm.setInt(4, numRow);
			callStm.setInt(5, startRow);
			rs = callStm.executeQuery();
			
			while (rs.next()) {
				arrList.add(new Article(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getString(4)));
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return arrList;
	}
	
}
