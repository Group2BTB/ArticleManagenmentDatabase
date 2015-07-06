package dao;

import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

import process.Format;
import process.Validation;
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
			PreparedStatement prestm = con.prepareStatement("insert into tbl_article(title,author_id,content,approved)values(?,?,?,?)"); ) {			
			
				
			prestm.setString(1, art.getTitle()); //set title for an article to insert to Database
			prestm.setInt(2,art.getAuthorId()); //set author_id for an article to insert to Database
			prestm.setString(3, art.getContent()); //set content for an article to insert to Database			
			if((Validation.checkWord(art.getTitle())!= null) || (Validation.checkWord(art.getContent().trim())!= null)){				
				System.out.println("dddd");
				prestm.setInt(4, 0);
			}else{
				System.out.println("fffff");
				prestm.setInt(4, 1);
			}
			
			prestm.executeUpdate();		
			
			//CallableStatement stmCall = con.prepareCall(" call search_all(?, ?, ?, ?)}");
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
				PreparedStatement prestm = con.prepareStatement("update tbl_article set title=?, author_id=?,content=?, approved=? where id=?");){
			prestm.setString(1, art.getTitle()); //set new title to be updated
			prestm.setInt(2, art.getAuthorId()); //set new author_id to be updated
			prestm.setString(3, art.getContent()); //set new content to be updated			
			if((Validation.checkWord(art.getTitle())!= null) || (Validation.checkWord(art.getContent())!= null))
				prestm.setInt(4, 0);
			else
				prestm.setInt(4, 1);
			prestm.setInt(5, id); //set id for article to update
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

	public ArrayList<Article> searchArticle(String keyword, String field, String orderTo, int numRow, int startRow) {

		ArrayList<Article> arrList = new ArrayList<Article>();

		ResultSet rs = null;
		CallableStatement pre=null;
		try(Connection con = DBConnection.getConnection();) {
			//Statement stm = con.createStatement();
			//String sql = "SELECT * from tbl_article JOIN tbl_user on tbl_article.author_id = tbl_user.id Where title ~* '.*"+keyword+".*' or content ~* '.*"+keyword+".*' or fullname ~* '.*"+keyword+".*'";
			pre = con.prepareCall("{ call search_all(?,?,?,?,?) }");
			pre.setString(1, keyword);
			pre.setString(2, field);
			pre.setString(3, orderTo);
			pre.setInt(4, numRow);
			pre.setInt(5, startRow);
			rs = pre.executeQuery();
			//rs = stm.executeQuery(sql);
			while (rs.next()) {
				arrList.add(new Article(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getString(4)));
			}
			rs.close();
			pre.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return arrList;
	}
	
	public Article readArticle(int id){
		Article art = new Article();
		ResultSet rs  = null;
		PreparedStatement pstm = null;
		try(Connection con = DBConnection.getConnection()){
			pstm = con.prepareStatement("SELECT art.title, art.content, us.fullname, art.create_date from tbl_article art join tbl_user us on art.author_id = us.id where art.id=?",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstm.setInt(1, id);
			if((rs = pstm.executeQuery()).last()){
				art.setContent(rs.getString("content")); //set content for the article
				art.setTitle(rs.getString("title")); //set title for the article
				art.setId(id); //set id for the article
				art.setAuthorName(rs.getString("fullname"));
				art.setDate(rs.getString("create_date"));
				return art;
			}
			/*while(rs.next()){
				art.setId(id);
				art.setTitle(rs.getString("title"));
				art.setContent(rs.getString("content"));
			}*/
			rs.close();
			pstm.close();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}