package process;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import dao.ArticleDAO;
import dao.Pagination;
import dao.Search;
import dao.Sort;
import dto.Article;
import dto.User;
import view.ArticleView;
import view.UI;

public class ArticleController {

	/*This method use to control when user want to update article*/
	public void updateControl() {
		ArticleDAO dao = new ArticleDAO();
		ArticleView view = new ArticleView();
		int id = view.updateForm();//Call from ArticleView Class
		
		/*This method use to check does id that user want to update their article exist or not*/
		Article art = dao.checkValidId(id);
		if (art != null) {
			/* Call method update article from ArticleDAO and return boolean */
			boolean isTrue = dao.updateArticle(id, view.updateChoice(art)/*Call method from ArticleView to get user decision*/);
			if (isTrue == true) {
				System.out.println("Update successfully!");
			} else {
				System.out.println("Operation fail");
			}
		} else {
			System.out.println("Invalid ID!");
		}
	}
	/* The end of method */
	
	/* This method use to control when you user want to insert new Article */
	public void insertControl(int userId) {
		Article art = new ArticleView().insertForm();
		art.setAuthorId(userId);
		boolean status = new ArticleDAO().insertArticle(art);
		if (status == true) {
			System.out.println("Insert Successfully!");
		} else {
			System.out.println("Insert fail!");
		}
	}
	/*The end of method*/

	/* This method use to control when user want to delete*/
	public void deleteControl() {
		
		/*
		 *  Call to method from Class ArticleView to ask user to input id to delete
		 *  @param delID use to store value of Id from user input
		 */
		int delID = new ArticleView().setIdOption();
		
		/* Check user option */
		if (new ArticleView().userChoice().equalsIgnoreCase("y")/*check if user agree to delete this article*/) {
			new ArticleDAO().deleteArticle(delID);// Call method deleteArticle from ArticleDAO Class
			System.out.println("Delete successfully!");
		} else {
			System.out.println("Delete was canceled!");
		}
	}
	/*----------------The end of method---------------------*/
	
	/* 
	 * Method searchControl() use to control on article when user want to  
	 * search by option Like 'ID', 'Title', 'author'
	 */
	public void searchControl(){
		Pagination.page=1;
		ArticleView view = new ArticleView();
		String searchBy = view.searchBy();
		try {
			String keyword = view.setSrtSearch();
			if(searchBy.equals("id")){ // if searchBy option equal to id				
				Search.searchByID(Integer.parseInt(keyword));//
				/* get value from method getArticleBySearch() store in arrayList arr*/
				ArrayList<Article> arr = Pagination.getArticleBySearch(Search.searchValue, Search.searchType, Sort.order, Sort.sort, Pagination.startIndex());
				int totalRecord = arr.size();// get size of arraList
				int totalPage = Pagination.calculatePage(totalRecord);//get total record from database
				new UI().listContent(arr, Pagination.page, totalRecord, totalPage);// call method listContent() From class UI in package view
			}else if(searchBy.equals("title")){ //if searchBy option equal to title
				Search.searchByTitle(keyword);
				int totalRecord = Pagination.countSearchBy(keyword, Search.searchType);
				int totalPage = Pagination.calculatePage(totalRecord);
				new UI().listContent(Pagination.getArticleBySearch(Search.searchValue, Search.searchType, Sort.order, Sort.sort, Pagination.startIndex()), Pagination.page, totalRecord, totalPage);			
			}else if(searchBy.equals("author")){//if searchBy option equal to author
				Search.searchByAuthor(keyword);
				int totalRecord = Pagination.countSearchBy(keyword, Search.searchType);
				int totalPage = Pagination.calculatePage(totalRecord);
				new UI().listContent(Pagination.getArticleBySearch(Search.searchValue, Search.searchType, Sort.order, Sort.sort, Pagination.startIndex()), Pagination.page, totalRecord, totalPage);			
			}						
		} catch (SQLException | ParseException |NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			new ArticleView().invalid();/*call method invalid() from class ArticleView to show message when user input invalid keyword*/
		}finally{
			new UI().menu();// call method menu from UI class
		}
	}
	public void displaySearch(){
		int totalRecord;
		try {
			totalRecord = Pagination.countSearchBy(Search.searchValue, Search.searchType);
			int totalPage = Pagination.calculatePage(totalRecord);			
			new UI().listContent(Pagination.getArticleBySearch(Search.searchValue, Search.searchType, Sort.order, Sort.sort, Pagination.startIndex()), Pagination.page, totalRecord, totalPage);			
		
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			new UI().menu();
		}
		
	}
	
	public void sortControl(){
		ArticleView view = new ArticleView();
		Sort.setOrder(view.sortBy());
		Sort.setSort(view.setSrtSort());
		if(Search.searchAct==false){
			int totalRecord;
			try {
				totalRecord = Pagination.countSelectAll();
				int totalPage = Pagination.calculatePage(totalRecord);
				Pagination.first();
				new UI().listContent(Pagination.getArticleAll(Sort.order, Sort.sort, Pagination.startIndex()), Pagination.page, totalRecord, totalPage);							
			} catch (SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				new UI().menu();
			}			
		}else{
			Pagination.first();
			int totalRecord,totalPage ;
			if(Search.searchType.equals("id")){	
				ArrayList<Article> arr;
				try {
					arr = Pagination.getArticleBySearch(Search.searchValue, Search.searchType, Sort.order, Sort.sort, Pagination.startIndex());
					totalRecord = arr.size();
					totalPage = Pagination.calculatePage(totalRecord);	
					new UI().listContent(arr, Pagination.page, totalRecord, totalPage);
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}else if(Search.searchType.equals("title")){
				try {
					totalRecord = Pagination.countSearchBy(Search.searchValue, Search.searchType);
					totalPage = Pagination.calculatePage(totalRecord);
					Pagination.first();	
					new UI().listContent(Pagination.getArticleBySearch(Search.searchValue, Search.searchType, Sort.order, Sort.sort, Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
			}else if(Search.searchType.equals("author")){
				try {
					totalRecord = Pagination.countSearchBy(Search.searchValue, Search.searchType);
					totalPage = Pagination.calculatePage(totalRecord);
					Pagination.first();	
					new UI().listContent(Pagination.getArticleBySearch(Search.searchValue, Search.searchType, Sort.order, Sort.sort, Pagination.startIndex()), Pagination.page, totalRecord, totalPage);	
				} catch (SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}
	
	public void readArticleControl(){
		Article  art = new Article();
		int id = new ArticleView().readChoice();
		art = new ArticleDAO().readArticle(id);
		new ArticleView().displayArticle(art);
	}
	
	public void displayHomePage(){
		int totalRecord = 0;
		try {
			totalRecord = Pagination.countSelectAll();
			int totalPage = Pagination.calculatePage(totalRecord);
			new UI().listContent(Pagination.getArticleAll(Sort.order, Sort.sort, Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
			new UI().menu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Sql cannot get data from database");
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("Cannot pasre");
		}	 
	}

}
