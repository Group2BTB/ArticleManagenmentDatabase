package process;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import dao.ArticleDAO;
import dao.Pagination;
import dto.Article;
import view.ArticleView;

public class ArticleController {

	public void updateControl() {
		ArticleDAO dao = new ArticleDAO();
		ArticleView view = new ArticleView();
		int id = view.updateForm();
		Article art = dao.checkValidId(id);
		if (art != null) {
			boolean isTrue = dao.updateArticle(id, view.updateChoice(art));
			if (isTrue == true) {
				System.out.println("Update successfully!");
			} else {
				System.out.println("Operation fail");
			}
		} else {
			System.out.println("Invalid ID!");
		}
	}

	public void insertControl() {
		Article art = new ArticleView().insertForm();
		boolean status = new ArticleDAO().insertArticle(art);
		if (status == true) {
			System.out.println("Insert Successfully!");
		} else {
			System.out.println("Insert fail!");
		}
	}

	/*
	 * public ArrayList<Article> searchControl(){
	 * 
	 * }
	 */

	public void deleteControl() {
		int delID = new ArticleView().setIdOption();
		if (new ArticleView().userChoice().equalsIgnoreCase("y")) {
			new ArticleDAO().deleteArticle(delID);
			System.out.println("Delete successfully!");
		} else {
			System.out.println("Delete was canceled!");
		}
	}
	
	public void searchControl(){
		ArticleView view = new ArticleView();
		String searchBy = view.searchBy();
		try {
			new Pagination().getArticleBySearch(view.setSrtSearch(),searchBy,searchBy,"DESC", 0);
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sortControl(){
		
	}
}
