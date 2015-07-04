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

	public void insertControl(){
		Article art = new ArticleView().insertForm();
		boolean status = new ArticleDAO().insertArticle(art);
		if(status == true){
			 System.out.println("Insert Successfully!");
		}else{
			System.out.println("Insert fail!");
		}
	}
	
	/*public ArrayList<Article> searchControl(){
		
	}*/
	
}
