package process;
import dao.*;
import dto.Article;
import java.sql.SQLException;
import java.util.Scanner;

import view.InsertFormUser;
import view.UpdateUserInfo;
import dao.UserDAO;
import dto.User;

public class Process {

	public void respondProcess(int opt) throws Exception{
		User udto=new User();
		UpdateUserInfo choice = new UpdateUserInfo();
		switch (opt) {
		case 1:			
			new InsertFormUser(udto);
			new UserDAO().insertView(udto);
			break;
		case 3: 

			new UpdateUserInfo().inputID(udto);
			new UserDAO().getUpdateFullname(udto);			
			
			break;		

		default:
			break;
		}
	}

	public static void main(String[] args) {
		
		Article art = new Article();
		art.setTitle("title1");
		art.setContent("content1");
		art.setAuthorId(4);
		
		new ArticleDAO().insertArticle(art);
		
	}
}
