package process;
import dao.*;
import dto.Article;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import view.AdminMenu;

import view.ArticleView;
import view.UI;
import view.UserView;
import dao.UserDAO;
import dto.User;

public class Process {
	
	public void respondProcess() throws Exception{
		User udto=new User();// create object of class User in dto
		UserView choice = new UserView();//create object of class UserView in view
		String opt = new AdminMenu().DisplayAminPage();
		switch (opt.toUpperCase()) {
		case "A":		
			new UserView().InsertFormUser(udto);//get user object from view
			new UserDAO().insertView(udto);//get user object from model and pass to view
			break;
		case "V":
			new UserView().getUserId(udto);// get user id from view
			new UserDAO().viewUser(udto);// get user object from model and pass to view
			new UserView().viewUserInfo(udto);// show the result in to console
			
			break;	
		case "U": 
			int num = new UserView().UpdateUser(udto);//get user object from model and pass to view	
			new UserDAO().Update(num,udto);//get choice and user object and updated to database
			break;

		case "D":
			new UserView().DeleteUser(udto);// get id from view
			new UserDAO().DeleteUsers(udto);// delete the row where id is set
			break;
		case "L":
			
			break;
		default:
			break;
		}
	}


	public static void main(String[] args) throws Exception {
		new Process().respondProcess();


		//new ArticleController().articleController();

		/*Article art = new ArticleDAO().checkValidId(2);
		System.out.println(art.getTitle());*/
		new Process().articleController();

	}
	
	public void articleController() throws SQLException, ParseException{
		String[] str = new ArticleView().getOption();
		if(str.length>2 || str.length<=0){
			System.out.println("Invalid Keyword!!! Please Input again!");
		}else{
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
			case "E":
				new ArticleController().updateControl();
				break;
			case "D":
				int delID = new ArticleView().setIdOption();
				new ArticleDAO().deleteArticle(delID);
				break;
			case "HM":				
				int totalRecord = Pagination.countSelectAll();				
				new UI().listContent(Pagination.getArticleAll("id", "DESC", 0), Pagination.page, totalRecord, Pagination.calculatePage(totalRecord));
				break;
			}
		}
	}
	
}
