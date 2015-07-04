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
		String opt = new AdminMenu().displayAminPage();
		
		switch (opt) {
		
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
		case "HM":
			new AdminMenu().displayAminPage();
			break;
		default:
			break;
		}
	}
	
	public void userControl(){
		AdminMenu adm = new AdminMenu();// create object of class AdminMenu in view
		User user = new User();// create object of class User in dto
		UserDAO udao = new UserDAO();
		boolean istrue = true;
		try {
			do{
				adm.displayLoginMenu(user);
				udao.checkUserLogin(user);
				if(udao.checkUserLogin(user) == istrue){
					System.out.println("Login success.");
					try {System.out.println(user.getType());
						if(user.getType().equalsIgnoreCase("admin")){
							respondProcess();
						}else{
							new Process().articleController();
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					System.out.println("Invalid Username or Password! Login again.");
				}
			}while(udao.checkUserLogin(user) != istrue);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		//new Process().respondProcess();

		new Process().userControl();

		/*Article art = new ArticleDAO().checkValidId(2);
		System.out.println(art.getTitle());*/
		//new Process().articleController();

	}
	
	public void articleController() throws SQLException, ParseException{

		new ArticleView().displayHomePage();
		String searchAction = null;
		while(true){
			String[] str = new ArticleView().getOption();
			if(str.length>2 || str.length<=0){
				System.out.println("Invalid Keyword!!! Please Input again!");
			}else{
				int totalRecord=0;
				int totalPage=0;
				if(str.length==2){
					if(str[1].matches("[a-zA-z]{1}")){
						System.out.println("Invalid Keyword!!! Please Input again!");
						continue;
					}
				}
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
					new ArticleController().insertControl();
					new ArticleView().displayHomePage();
					break;
				case "E":
					new ArticleController().updateControl();
					break;
				case "D":
					int delID = new ArticleView().setIdOption();
					new ArticleDAO().deleteArticle(delID);
					break;
				case "HM":
					searchAction = null;
					new ArticleView().displayHomePage();
					break;
				case "R":					
					if(Integer.parseInt(str[1])>0){
						Pagination.setRow(Integer.parseInt(str[1]));
					}else{
						System.out.println("Set Row must be biger than 0.");
						continue;
					}
					totalRecord = Pagination.countSelectAll();
					totalPage = Pagination.calculatePage(totalRecord);
					new UI().listContent(Pagination.getArticleAll("id", "DESC", Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
					break;
				case "N":				
					totalRecord = Pagination.countSelectAll();	
					totalPage = Pagination.calculatePage(totalRecord);
					Pagination.next(totalPage);
					new UI().listContent(Pagination.getArticleAll("id", "DESC", Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
					break;
				case "L":				
					totalRecord = Pagination.countSelectAll();	
					totalPage = Pagination.calculatePage(totalRecord);
					Pagination.last(totalPage);
					new UI().listContent(Pagination.getArticleAll("id", "DESC", Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
					break;
				case "P":				
					totalRecord = Pagination.countSelectAll();	
					totalPage = Pagination.calculatePage(totalRecord);
					Pagination.previous();
					new UI().listContent(Pagination.getArticleAll("id", "DESC", Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
					break;
				case "F":				
					totalRecord = Pagination.countSelectAll();	
					totalPage = Pagination.calculatePage(totalRecord);
					Pagination.first();					
					new UI().listContent(Pagination.getArticleAll("id", "DESC", Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
					break;
				}
				
			}
		}
	}
	
}
