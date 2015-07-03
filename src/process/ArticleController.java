package process;

import java.util.ArrayList;

import dao.ArticleDAO;
import dto.Article;
import view.ArticleView;

public class ArticleController {
	
	public void updateControl(){
		ArticleDAO dao = new ArticleDAO();
		ArticleView view = new ArticleView();
		int id = view.updateForm();
		Article art = dao.checkValidId(id);
		if(art != null){
			boolean isTrue = dao.updateArticle(id, view.updateChoice(art));
			if(isTrue == true){
				System.out.println("Update successfully!");
			}else{
				System.out.println("Operation fail");
			}
			
		}else{
			System.out.println("Invalid ID!");
		}
	}
	
}
