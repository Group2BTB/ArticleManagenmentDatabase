package process;
import dao.*;
import dto.Article;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import view.UserView;
import dao.UserDAO;
import dto.User;

public class Process {
	
	public void respondProcess(int opt) throws Exception{
		User udto=new User();// create object of class User in dto
		UserView choice = new UserView();//create object of class UserView in view
		switch (opt) {
		case 1:		
			new UserView().InsertFormUser(udto);//get user object from view
			new UserDAO().insertView(udto);//get user object from model and pass to view
			break;
		case 2:
			new UserView().getUserId(udto);// get user id from view
			new UserDAO().viewUser(udto);// get user object from model and pass to view
			new UserView().viewUserInfo(udto);// show the result in to console
			
			break;	
		case 3: 
			int num = new UserView().UpdateUser(udto);//get user object from model and pass to view	
			new UserDAO().Update(num,udto);//get choice and user object and updated to database
			break;
			
		case 4:
			new UserView().DeleteUser(udto);// get id from view
			new UserDAO().DeleteUsers(udto);// delete the row where id is set
			
		default:
			break;
		}
	}

	public static void main(String[] args) {

		new ArticleController().articleController();

	}
	
}
