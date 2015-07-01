package process;
import java.sql.SQLException;

import dao.ArticleDAO;
import view.ArticleView;

public class ArticleController {
	
	private ArticleDAO art = null;
	
	public ArticleController(){
		art = new ArticleDAO();
	}
	
	public void Contriller(){
		art.searchArticle(new ArticleView().getSrtSearch());
	}
}
