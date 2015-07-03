package process;
import dao.*;
import dto.Article;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import view.ArticleView;
import view.UserView;
import dao.UserDAO;
import dto.User;

public class Process {
	
	public void respondProcess(String opt) throws Exception{
		User udto=new User();// create object of class User in dto
		UserView choice = new UserView();//create object of class UserView in view
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
		default:
			break;
		}
	}

	public static void main(String[] args) {
		Article art = new ArticleDAO().checkValid(11);
		System.out.println(art);
		new Process().articleController();
	}
	
	public void articleController(){
		String[] str = new ArticleView().getOption();
		switch(str[0]){
		case "S":
				ArrayList<Article> arrList = new ArrayList<Article>();
				try{
					arrList = new ArticleDAO().searchArticle(new ArticleView().setSrtSearch(),"id","DESC",1,0);
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
