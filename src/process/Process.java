package process;

import dao.*;
import dto.Article;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import view.AdminMenu;
import view.ArticleView;
import view.UI;
import view.UserView;
import dao.UserDAO;
import dto.User;

public class Process {
	/*
	 * @param userId is used to store id of current user
	 */
	private static int userId = 0;//

	public void respondProcess() throws Exception {
		User udto = new User();// create object of class User in dto
		UserView choice = new UserView();// create object of class UserView in
											// view
		String opt = new AdminMenu().displayAminPage();

		if (opt.matches("A") || opt.matches("V") || opt.matches("U") || opt.matches("L")
				|| opt.matches("D") || opt.matches("DE") || opt.matches("X")
				|| opt.matches("HM") || opt.matches("H") || opt.matches("A")  || opt.matches("AC") ) {

			switch (opt) {
			case "AC":
				new UserView().activeUser(udto);
				new UserDAO().activedUsers(udto);
				new Process().respondProcess();
				break;
				
			case "A":
				new UserView().InsertFormUser(udto);// get user object from view
				new UserDAO().insertView(udto);// get user object from model and
												// pass to view
				new Process().respondProcess();
				break;
			case "V":
				//new UserView().getUserId(udto);// get user id from view
				//new UserDAO().viewUser(udto);// get user object from model and
												// pass to view
				//new UserView().viewUserInfo(udto);// show the result in to
													// console
				new UserController().userDisplayControl();
				new Process().respondProcess();
				break;
			case "U":
				int num = new UserView().UpdateUser(udto);// get user object
															// from model and
															// pass to view
				new UserDAO().Update(num, udto);// get choice and user object
												// and updated to database
				new Process().respondProcess();
				break;

			case "D":
				new UserView().DeleteUser(udto);// get id from view
				new UserDAO().DeleteUsers(udto);// delete the row where id is
												// set
				new Process().respondProcess();
				break;
			case "DE":
				new UserView().deActiveUser(udto);// get id from view
				new UserDAO().DeActivedUsers(udto);// delete the row where id is
													// set
				new Process().respondProcess();
				break;
			case "X":
				System.exit(0);
				break;
			case "HM":
				new Process().respondProcess();//go page respone for show menu
				break;
			case "H":
				new AdminMenu().helpe();//go to page help
				new Process().respondProcess();
				break;
			case "L":

				new UserDAO().writeLogFile("Logout", udto.getUsername(), "Successful"); 


				userControl();
				break;
			case "LO":
				new Process().respondProcess();
				break;
			default:

			}

		} else {
			System.out.println("Syntax Error!!!");
			new Process().respondProcess();

		}
	}
	
	

	public void userControl() {
		AdminMenu adm = new AdminMenu();// create object of class AdminMenu in
										// view
		User user = new User();// create object of class User in dto
		UserDAO udao = new UserDAO();//create object of class UserDAO
		boolean istrue = true;//declare a variable as boolean true
		try {
			do {
				adm.displayLoginMenu(user);//to display login menu
				udao.checkUserLogin(user);//to check whether account active and type of user
				String login = "Login";//declare a variable in string as login
				if (udao.checkUserLogin(user) == istrue) {//to check if it correct and active or not
					System.out.println("Login success.");//display susscess
					try {
						// System.out.println(user.getType());
						if (user.getType().equalsIgnoreCase("admin")) {//to check if user type as admin
							respondProcess();//call admin menu
						} else {//user type as user
							// System.out.println(user.getId());
							userId += user.getId();
							udao.writeLogFile(login, user.getUsername(), "Successful");//to write all user transaction to logfile
							new Process().articleController();//call user menu
							

						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out
							.println("Invalid Username or Password! Login again.");
					int opt = adm.confirmMenu();
					switch(opt){
					case 1:
						userControl();
						break;
					case 2:
						String username = adm.getUsername(user);
						udao.getNewPassword(username);
						if(udao.getNewPassword(username) == istrue){
							String newPasswd = adm.inputNewPassword();
							udao.newPasswd(newPasswd, username);
							userControl();
						}
						break;
					}
				}
			} while (udao.checkUserLogin(user) != istrue);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		new Process().userControl();
		//new ArticleController().readArticleControl();
	}

	public void articleController() throws Exception {
		new UI().head();
		new ArticleController().displayHomePage();
		String searchAction = null;
		while (true) {
			String[] str = new ArticleView().getOption();
			if (str.length > 2 || str.length <= 0) {
				new ArticleView().invalid();
			} else {
				int totalRecord = 0;
				int totalPage = 0;
				if (str.length == 2) {
					if (str[1].matches("[a-zA-z]{1}")) {
						new ArticleView().invalid();
						continue;
					}
				}
				if (Search.searchAct == false) {
					switch (str[0]) {
					case "S":
						new ArticleController().searchControl();
						break;
					case "A":
						new ArticleController().insertControl(userId);
						new ArticleController().displayHomePage();
						break;
					case "E":
						new ArticleController().updateControl();
						new ArticleController().displayHomePage();
						break;
					case "D":
						new ArticleController().deleteControl();
						new ArticleController().displayHomePage();
						break;
					case "HM":
						Search.notSearch();
						new ArticleController().displayHomePage();
						break;
					case "R":
						if(str.length>1){
							if (Integer.parseInt(str[1]) > 0) {
								Pagination.setRow(Integer.parseInt(str[1]));
							} else {
								System.out.println("Set Row must be biger than 0.");
								continue;
							}
							totalRecord = Pagination.countSelectAll();
							totalPage = Pagination.calculatePage(totalRecord);
							new UI().listContent(
									Pagination.getArticleAll(Sort.order, Sort.sort,
											Pagination.startIndex()),
									Pagination.page, totalRecord, totalPage);
						}else{
							new ArticleView().invalid();
						}
						new UI().menu();
						break;
					case "G":
						if(str.length>1){
							totalRecord = Pagination.countSelectAll();
							totalPage = Pagination.calculatePage(totalRecord);
							if (Integer.parseInt(str[1]) > 0) {
								if(Integer.parseInt(str[1]) > totalPage){
									System.out.println("Goto page must be small or equal Total page .");
									continue;
								}else{
									Pagination.gotoPage(Integer.parseInt(str[1]));
								}
							}else{
								System.out.println("Goto page must be biger than 0.");
								continue;
							}
							new UI().listContent(
									Pagination.getArticleAll(Sort.order, Sort.sort,
											Pagination.startIndex()),
									Pagination.page, totalRecord, totalPage);
						}else{
							new ArticleView().invalid();
						}
						new UI().menu();
						break;
					case "N":
						totalRecord = Pagination.countSelectAll();
						totalPage = Pagination.calculatePage(totalRecord);
						Pagination.next(totalPage);
						new UI().listContent(
								Pagination.getArticleAll(Sort.order, Sort.sort,
										Pagination.startIndex()),
								Pagination.page, totalRecord, totalPage);
						new UI().menu();
						break;
					case "L":
						totalRecord = Pagination.countSelectAll();
						totalPage = Pagination.calculatePage(totalRecord);
						Pagination.last(totalPage);
						new UI().listContent(
								Pagination.getArticleAll(Sort.order, Sort.sort,
										Pagination.startIndex()),
								Pagination.page, totalRecord, totalPage);
						new UI().menu();
						break;
					case "P":
						totalRecord = Pagination.countSelectAll();
						totalPage = Pagination.calculatePage(totalRecord);
						Pagination.previous();
						new UI().listContent(
								Pagination.getArticleAll(Sort.order, Sort.sort,
										Pagination.startIndex()),
								Pagination.page, totalRecord, totalPage);
						new UI().menu();
						break;
					case "F":
						totalRecord = Pagination.countSelectAll();
						totalPage = Pagination.calculatePage(totalRecord);
						Pagination.first();
						new UI().listContent(
								Pagination.getArticleAll(Sort.order, Sort.sort,
										Pagination.startIndex()),
								Pagination.page, totalRecord, totalPage);
						new UI().menu();
						break;
					case "RD":
						new ArticleController().readArticleControl();
						//new ArticleController().displayHomePage();
						break;
					case "H":
						new UI().help();
						break;
					case "X":
						new UI().thanks();
						System.exit(0);
						break;
					case "SO":
						new ArticleController().sortControl();
						break;
					case "LO":
						new Process().userControl();
						break;
					}
				} else {
					switch (str[0]) {
					case "S":
						new ArticleController().searchControl();
						break;
					case "A":
						new ArticleController().insertControl(userId);
						new ArticleController().displayHomePage();
						break;
					case "E":
						new ArticleController().updateControl();
						new ArticleController().displayHomePage();
						break;
					case "D":
						new ArticleController().deleteControl();
						new ArticleController().displayHomePage();
						break;
					case "HM":
						Search.notSearch();
						new ArticleController().displayHomePage();
						break;
					case "R":
						if (Integer.parseInt(str[1]) > 0) {
							Pagination.setRow(Integer.parseInt(str[1]));
						} else {
							System.out.println("Set Row must be biger than 0.");
							continue;
						}
						new ArticleController().displaySearch();
						break;
					case "G":
						if(str.length>1){
													
							if (Search.searchType.equals("id")) {
								ArrayList<Article> arr = Pagination
										.getArticleBySearch(Search.searchValue,
												Search.searchType, Sort.order,
												Sort.sort, Pagination.startIndex());
								totalRecord = arr.size();
								totalPage = Pagination.calculatePage(totalRecord);
								if (Integer.parseInt(str[1]) > 0) {
									if(Integer.parseInt(str[1]) > totalPage){
										System.out.println("Goto page must be small or equal Total page .");
										continue;
									}else{
										Pagination.gotoPage(Integer.parseInt(str[1]));
									}
								}else{
									System.out.println("Goto page must be biger than 0.");
									continue;
								}
								new UI().listContent(arr, Pagination.page,
										totalRecord, totalPage);
							} else if (Search.searchType.equals("title")) {
								totalRecord = Pagination.countSearchBy(
										Search.searchValue, Search.searchType);
								totalPage = Pagination.calculatePage(totalRecord);
								if (Integer.parseInt(str[1]) > 0) {
									if(Integer.parseInt(str[1]) > totalPage){
										System.out.println("Goto page must be small or equal Total page .");
										continue;
									}else{
										Pagination.gotoPage(Integer.parseInt(str[1]));
									}
								}else{
									System.out.println("Goto page must be biger than 0.");
									continue;
								}
								new UI().listContent(Pagination.getArticleBySearch(
										Search.searchValue, Search.searchType,
										Sort.order, Sort.sort,
										Pagination.startIndex()), Pagination.page,
										totalRecord, totalPage);
							} else if (Search.searchType.equals("author")) {
								totalRecord = Pagination.countSearchBy(
										Search.searchValue, Search.searchType);
								totalPage = Pagination.calculatePage(totalRecord);
								if (Integer.parseInt(str[1]) > 0) {
									if(Integer.parseInt(str[1]) > totalPage){
										System.out.println("Goto page must be small or equal Total page .");
										continue;
									}else{
										Pagination.gotoPage(Integer.parseInt(str[1]));
									}
								}else{
									System.out.println("Goto page must be biger than 0.");
									continue;
								}
								new UI().listContent(Pagination.getArticleBySearch(
										Search.searchValue, Search.searchType,
										Sort.order, Sort.sort,
										Pagination.startIndex()), Pagination.page,
										totalRecord, totalPage);
							}
						}else{
							new ArticleView().invalid();
						}
						new UI().menu();
						break;
					case "N":
						if (Search.searchType.equals("id")) {
							ArrayList<Article> arr = Pagination
									.getArticleBySearch(Search.searchValue,
											Search.searchType, Sort.order,
											Sort.sort, Pagination.startIndex());
							totalRecord = arr.size();
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.next(totalPage);
							new UI().listContent(arr, Pagination.page,
									totalRecord, totalPage);
						} else if (Search.searchType.equals("title")) {
							totalRecord = Pagination.countSearchBy(
									Search.searchValue, Search.searchType);
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.next(totalPage);
							new UI().listContent(Pagination.getArticleBySearch(
									Search.searchValue, Search.searchType,
									Sort.order, Sort.sort,
									Pagination.startIndex()), Pagination.page,
									totalRecord, totalPage);
						} else if (Search.searchType.equals("author")) {
							totalRecord = Pagination.countSearchBy(
									Search.searchValue, Search.searchType);
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.next(totalPage);
							new UI().listContent(Pagination.getArticleBySearch(
									Search.searchValue, Search.searchType,
									Sort.order, Sort.sort,
									Pagination.startIndex()), Pagination.page,
									totalRecord, totalPage);
						}
						new UI().menu();
						break;
					case "L":
						if (Search.searchType.equals("id")) {
							ArrayList<Article> arr = Pagination
									.getArticleBySearch(Search.searchValue,
											Search.searchType, Sort.order,
											Sort.sort, Pagination.startIndex());
							totalRecord = arr.size();
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.last(totalPage);
							new UI().listContent(arr, Pagination.page,
									totalRecord, totalPage);
						} else if (Search.searchType.equals("title")) {
							totalRecord = Pagination.countSearchBy(
									Search.searchValue, Search.searchType);
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.last(totalPage);
							new UI().listContent(Pagination.getArticleBySearch(
									Search.searchValue, Search.searchType,
									Sort.order, Sort.sort,
									Pagination.startIndex()), Pagination.page,
									totalRecord, totalPage);
						} else if (Search.searchType.equals("author")) {
							totalRecord = Pagination.countSearchBy(
									Search.searchValue, Search.searchType);
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.last(totalPage);
							new UI().listContent(Pagination.getArticleBySearch(
									Search.searchValue, Search.searchType,
									Sort.order, Sort.sort,
									Pagination.startIndex()), Pagination.page,
									totalRecord, totalPage);
						}
						new UI().menu();
						break;
					case "P":
						if (Search.searchType.equals("id")) {
							ArrayList<Article> arr = Pagination
									.getArticleBySearch(Search.searchValue,
											Search.searchType, Sort.order,
											Sort.sort, Pagination.startIndex());
							totalRecord = arr.size();
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.previous();
							new UI().listContent(arr, Pagination.page,
									totalRecord, totalPage);
						} else if (Search.searchType.equals("title")) {
							totalRecord = Pagination.countSearchBy(
									Search.searchValue, Search.searchType);
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.previous();
							new UI().listContent(Pagination.getArticleBySearch(
									Search.searchValue, Search.searchType,
									Sort.order, Sort.sort,
									Pagination.startIndex()), Pagination.page,
									totalRecord, totalPage);
						} else if (Search.searchType.equals("author")) {
							totalRecord = Pagination.countSearchBy(
									Search.searchValue, Search.searchType);
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.previous();
							new UI().listContent(Pagination.getArticleBySearch(
									Search.searchValue, Search.searchType,
									Sort.order, Sort.sort,
									Pagination.startIndex()), Pagination.page,
									totalRecord, totalPage);
						}
						new UI().menu();
						break;
					case "F":
						if (Search.searchType.equals("id")) {
							ArrayList<Article> arr = Pagination
									.getArticleBySearch(Search.searchValue,
											Search.searchType, Sort.order,
											Sort.sort, Pagination.startIndex());
							totalRecord = arr.size();
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.first();
							new UI().listContent(arr, Pagination.page,
									totalRecord, totalPage);
						} else if (Search.searchType.equals("title")) {
							totalRecord = Pagination.countSearchBy(
									Search.searchValue, Search.searchType);
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.first();
							new UI().listContent(Pagination.getArticleBySearch(
									Search.searchValue, Search.searchType,
									Sort.order, Sort.sort,
									Pagination.startIndex()), Pagination.page,
									totalRecord, totalPage);
						} else if (Search.searchType.equals("author")) {
							totalRecord = Pagination.countSearchBy(
									Search.searchValue, Search.searchType);
							totalPage = Pagination.calculatePage(totalRecord);
							Pagination.first();
							new UI().listContent(Pagination.getArticleBySearch(
									Search.searchValue, Search.searchType,
									Sort.order, Sort.sort,
									Pagination.startIndex()), Pagination.page,
									totalRecord, totalPage);
						}
						new UI().menu();
						break;
					case "RD":
						new ArticleController().readArticleControl();
						//new ArticleController().displayHomePage();
						break;
					case "H":
						new UI().help();
						break;
					case "LO":
						new Process().userControl();
						break;
					case "X":
						new UI().thanks();
						System.exit(0);
						break;
					case "SO":
						new ArticleController().sortControl();
						break;
					}
				}
			}
		}
	}
}
