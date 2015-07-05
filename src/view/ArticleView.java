package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.Pagination;
import dto.Article;
import process.ArticleController;
import process.Validation;

public class ArticleView {

	private Scanner scan;

	/* This method use to get keyword from user when they want to search article */
	public String setSrtSearch() {
		scan = new Scanner(System.in);
		System.out.print("Enter keywoard to search: ");
		String keyword = scan.nextLine();
		return keyword;// return value of user's choice
	}

	/*--------End------------*/

	/* This method use to ask user to choose option that they want to do */
	public String[] getOption() {
		scan = new Scanner(System.in);
		System.out.print("-> Choose: ");

		/* Get data from keyboard and covert to Capital letter */
		String option = scan.nextLine().trim().toUpperCase();
		
		/* Call method checkNull from Validation Class in Process Package */
		String[] str = new Validation().spliteStr(option);
		
		return str;// return user choice
	}

	/* ----------End-------- */

	/* Method use to display list of Article from search */
	public void searchDisplay(ArrayList<Article> arrList) {
		for (Article art : arrList) {
			System.out.println(art.getContent() + " " + art.getTitle());
		}
	}

	/*---------End-----------*/

	public Article insertForm() {
		scan = new Scanner(System.in);
		Article art = new Article();
		System.out.print("Enter Title: ");
		art.setTitle(scan.next());
		System.out.print("Enter Content");
		art.setContent(getMiltiLineString());
		System.out.print("Enter AuthorID: ");
		art.setAuthorId(scan.nextInt());

		return art;// return value of object Article to Model ArticleDAO
	}

	public String getMiltiLineString(/* String msg */) {
		Scanner scan = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		System.out
				.println(/* msg */" (Press Enter the content + '.' + Enter to Finish) "
						+ " => ");
		while (true) {
			String imsi = scan.nextLine();
			if (imsi != null && imsi.trim().length() == 1
					&& imsi.trim().charAt(0) == '.')
				break;
			if (imsi == null)
				imsi = "";
			sb.append(imsi);
			sb.append("\n");
		}
		return sb.toString();
	}

	/*
	 * This method is used to check value that user input is type as Integer or
	 * not
	 */
	public boolean isInteger(String option) {
		try {
			Integer.parseInt(option);// convert from string to integer
			/* return true if value of variable "option" as Integer */
			return true;
		} catch (NumberFormatException nfe) {
			/* return false if value of variable 'option' as Integer */
			return false;
		}
	}

	/* ------------End----------- */

	public int setIdOption() {
		scan = new Scanner(System.in);
		System.out.print("Enter Id to delete: ");
		String id = scan.next();
		if (isInteger(id) == true) {
			return Integer.parseInt(id);
		} else {
			System.out.println("Please input digit!");
			return 0;
		}

	}
	
	public int updateForm(){
		scan = new Scanner(System.in);
		System.out.print("Enter id to update: ");
		String id = scan.next();
		if(isInteger(id) == true){
			return Integer.parseInt(id);
		}else{
			System.out.println("Please Input Digit!");
			return 0;
		}
		
	}
	
	public Article updateChoice(Article art){
		scan = new Scanner(System.in);
		System.out.println(art.getTitle() +""+ art.getAuthorName());
		System.out.print("Choose one of [1.Title|2.Content]: ");
		String option = scan.next();
		if(isInteger(option) == true){
			switch(Integer.parseInt(option)){
			case 1:
				System.out.print("Enter Title: ");
				art.setTitle(scan.next());
				return art;
			case 2: 
				System.out.print("Enter Content");
				art.setContent(getMiltiLineString());
				return art;
			}
			
		}
		return art;
	}
	
	public void displayHomePage(){
		int totalRecord = 0;
		try {
			totalRecord = Pagination.countSelectAll();
			int totalPage = Pagination.calculatePage(totalRecord);
			new UI().listContent(Pagination.getArticleAll("id", "DESC", 0), Pagination.page, totalRecord, totalPage);
			new UI().menu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Sql cannot get data from database");
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			System.err.println("Cannot pasre");
		}			 
	}
	
	/* this method used to ask the user's choice */
	public String userChoice(){
		scan = new Scanner(System.in);
		System.out.print("Do you want to delete this article? [y/n]: ");
		String option = scan.next();
		return option;
	}
	
	public String searchBy(){
		scan = new Scanner(System.in);
		while(true){
			System.out.print("Search By [id|Title|Author]: ");
			String searchBy = scan.next();
			if(searchBy.equalsIgnoreCase("id")||searchBy.equalsIgnoreCase("title")||searchBy.equalsIgnoreCase("author")){
				return searchBy;
			}else{
				System.out.println("Invalid Option!");
				searchBy();
			}
		}
	}
	
	public String sortBy(){
		scan = new Scanner(System.in);
		while(true){
			System.out.print("Sort By [1.id|2.Title|3.Author] :");
			String sortBy = scan.next();
			if(sortBy.equalsIgnoreCase("id")||sortBy.equalsIgnoreCase("title")||sortBy.equalsIgnoreCase("author")){
				return sortBy;
			}else{
				System.out.println("Invalid Option!");
			}
		}
	}

}
