package process;

import dao.ArticleDAO;
import view.ArticleView;

public class ArticleController {
	
	private ArticleDAO art = null;
	
	public ArticleController(){
		art = new ArticleDAO();
	}
	
	public void articleController(){
		String[] str = new ArticleView().getOption();
		switch(str[0]){
		case "S":
			art.searchArticle(new ArticleView().getSrtSearch());
			break;
		}
		
	}
	
}
