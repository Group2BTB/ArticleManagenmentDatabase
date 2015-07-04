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

	/*To check if there is the id in the database
	 * @id the id of an article 
	 * */
	
	public Article checkValidId(int id){

		Article art = new Article();
		ResultSet rs = null;
				
		try(Connection con = DBConnection.getConnection(); //get connection to Database
				//TYPE_SCROLL_SENSITIVE and CONCUR_UPDATABLE is used to make Resultset navigatable
				PreparedStatement pre = con.prepareStatement("select * from tbl_article where id=?",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);){
			pre.setInt(1,id);
			if((rs = pre.executeQuery()).last()){ //get records and move to the last record in ResultSet, true if it can move to the last, false if there is no record
				art.setAuthorId(rs.getInt("author_id")); //set author_id for the article
				art.setContent(rs.getString("content")); //set content for the article
				art.setTitle(rs.getString("title")); //set title for the article
				art.setId(id); //set id for the article
				return art;
			}
			return null;
			
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;			
		}finally{
			try{
				rs.close(); //close RecordSet
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}

	/*To insert an article into Database
	 * @art an article object to insert to Database
	 * */
	@Override
	public boolean insertArticle(Article art) {
		// TODO Auto-generated method stub
		try(Connection con = DBConnection.getConnection(); //get connection to Database				
				PreparedStatement prestm = con.prepareStatement("insert into tbl_article(title,author_id,content)values(?,?,?)"); ) {			
			
			prestm.setString(1, art.getTitle()); //set title for an article to insert to Database
			prestm.setInt(2,art.getAuthorId()); //set author_id for an article to insert to Database
			prestm.setString(3, art.getContent()); //set content for an article to insert to Database
			prestm.executeUpdate();			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	
	/*To update an article in Database
	 * @id is an id of article to be updated
	 * @art is an new article to be updated to 
	 * */
	@Override
	public boolean updateArticle(int id, Article art) {
		
		try(Connection con = DBConnection.getConnection(); //get connection to Database
				PreparedStatement prestm = con.prepareStatement("update tbl_article set title=?, author_id=?,content=? where id=?");){
			prestm.setString(1, art.getTitle()); //set new title to be updated
			prestm.setInt(2, art.getAuthorId()); //set new author_id to be updated
			prestm.setString(3, art.getContent()); //set new content to be updated
			prestm.setInt(4, id); //set id for article to update
			prestm.executeUpdate();
			return true;
		}catch(SQLException ex){
			ex.printStackTrace();
			return false;
		}		
	}

	/*To delete an article in database
	 * @id is an id of article to be deleted
	 * */
	
	@Override
	public boolean deleteArticle(int id) {
		
		try(Connection con = DBConnection.getConnection(); //get connection to Database
				PreparedStatement prestm = con.prepareStatement("delete  from tbl_article where id=?");){
			prestm.setInt(1,id); //set id for article to be deleted
			prestm.executeUpdate(); 
			return true;
		}catch(SQLException ex){
			ex.printStackTrace();
			return false;
		}
		
	} 

	public ArrayList<Article> searchArticle(String str) {

		ArrayList<Article> arrList = new ArrayList<Article>();

		ResultSet rs = null;
		try(Connection con = DBConnection.getConnection();) {
			//Statement stm = con.createStatement();
			//String sql = "SELECT * from tbl_article JOIN tbl_user on tbl_article.author_id = tbl_user.id Where title ~* '.*"+str+".*' or content ~* '.*"+str+".*' or fullname ~* '.*"+str+".*'";
			CallableStatement callStm = con.prepareCall("{ ? = call search_all( ?, ?, ?, ?, ?) }");
			//callStm.registerOutParameter(1, Types.OTHER);
			callStm.setString(1, str);
			callStm.setString(2, "id");
			callStm.setString(3,"ASC");
			callStm.setInt(4,5);
			callStm.setInt(5,1);
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
