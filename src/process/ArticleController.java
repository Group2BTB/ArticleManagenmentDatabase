package process;

import java.util.ArrayList;

import dao.ArticleDAO;
import dto.Article;
import view.ArticleView;

public class ArticleController {
	
	public void articleController(){
		String[] str = new ArticleView().getOption();
		switch(str[0]){
		case "S":
				ArrayList<Article> arrList = new ArrayList<Article>();
				arrList = new ArticleDAO().searchArticle(new ArticleView().setSrtSearch());
				new ArticleView().searchDisplay(arrList);
			break;
		}
		
	}
	
}
