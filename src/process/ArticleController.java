package process;
import java.sql.SQLException;

import dao.ArticleDAO;

public class ArticleController {
	
	private ArticleDAO art = null;
	
	public ArticleController(){
		art = new ArticleDAO();
	}
	
	public void Contriller(){
		art.searchArticle(str)
	}
}
