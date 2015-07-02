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
				try{
					arrList = new ArticleDAO().searchArticle(new ArticleView().setSrtSearch());
				}catch (NullPointerException e) {
					// TODO: handle exception
					System.out.println("Null Pointer Exception!");
				}
				new ArticleView().searchDisplay(arrList);
			break;
		case "A":
			Article art = new ArticleView().insertForm();
			new ArticleDAO().insertArticle(art);
			break;
		/*case "E":
			int id = new ArticleView().setIdOption();
			new ArticleDAO().updateArticle(id, art);
			break;*/
		case "D":
			int delID = new ArticleView().setIdOption();
			new ArticleDAO().deleteArticle(delID);
			break;
		}
		
	}
	
}
