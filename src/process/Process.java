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
		if(opt.matches("A") ||opt.matches("V")||opt.matches("U") ||opt.matches("D") ||opt.matches("DE") ||opt.matches("X") ||opt.matches("HM") ||opt.matches("H") ||opt.matches("A")  ){
			
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
		case "DE":
			new UserView().deActiveUser(udto);// get id from view
			new UserDAO().DeActivedUsers(udto);// delete the row where id is set
			break;
		case "X":
			System.exit(0);
			break;
		case "HM":
			new Process().respondProcess();
			break;
		default:
			break;
		}
		
	}else{
		System.out.println("Syntax Error!!!");
		new Process().respondProcess();
		
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

		//new Process().userControl();

		/*Article art = new ArticleDAO().checkValidId(2);
		System.out.println(art.getTitle());*/
		//new Process().articleController();
		new ArticleController().searchControl();

	}
	
	public void articleController() throws SQLException, ParseException{
		//new UI().head();
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
							arrList = new ArticleDAO().searchArticle(new ArticleView().setSrtSearch(), "id", "DESC", 5,0);
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
					new ArticleView().displayHomePage();
					break;
				case "D":
					new ArticleController().deleteControl();
					new ArticleView().displayHomePage();
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
					new UI().menu();
					break;
				case "N":				
					totalRecord = Pagination.countSelectAll();	
					totalPage = Pagination.calculatePage(totalRecord);
					Pagination.next(totalPage);
					new UI().listContent(Pagination.getArticleAll("id", "DESC", Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
					new UI().menu();
					break;
				case "L":				
					totalRecord = Pagination.countSelectAll();	
					totalPage = Pagination.calculatePage(totalRecord);
					Pagination.last(totalPage);
					new UI().listContent(Pagination.getArticleAll("id", "DESC", Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
					new UI().menu();
					break;
				case "P":				
					totalRecord = Pagination.countSelectAll();	
					totalPage = Pagination.calculatePage(totalRecord);
					Pagination.previous();
					new UI().listContent(Pagination.getArticleAll("id", "DESC", Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
					new UI().menu();
					break;
				case "F":				
					totalRecord = Pagination.countSelectAll();	
					totalPage = Pagination.calculatePage(totalRecord);
					Pagination.first();					
					new UI().listContent(Pagination.getArticleAll("id", "DESC", Pagination.startIndex()), Pagination.page, totalRecord, totalPage);
					new UI().menu();
					break;
				case "H":
					new UI().help();
					break;
				case "X":
					new UI().thanks();
					System.exit(0);
					break;
				}
				
			}
		}
	}
	
}
