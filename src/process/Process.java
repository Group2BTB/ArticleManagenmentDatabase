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
		User udto=new User();
		UserView choice = new UserView();
		switch (opt) {
		case 1:		
			new UserView().InsertFormUser(udto);
			new UserDAO().insertView(udto);
			break;
		case 2:
						
		case 3: 
			int num = new UserView().UpdateUser(udto);			
			new UserDAO().Update(num,udto);		
			break;

		default:
			break;
		}
	}

	public static void main(String[] args) {
		
		/*Article art = new Article();
		art.setTitle("title1");
		art.setContent("content1");
		art.setAuthorId(4);
		
		
		new ArticleDAO().insertArticle();
		Process pro = new Process();
		pro.articleResponseProcess();*/

		new ArticleController().articleController();

	}
	
}
