package view;

import java.util.ArrayList;
import java.util.Scanner;

import dto.Article;
import process.ArticleController;
import process.Validation;

public class ArticleView {
	
	private Scanner scan;
	
	public String setSrtSearch() {
		scan = new Scanner(System.in);
		System.out.print("Enter keywoard to search: ");
		String keyword = scan.nextLine();
		
		return keyword;
	}
	
	public String[] getOption(){
		scan = new Scanner(System.in);
		System.out.println("-> Choose: ");
		String option = scan.next().trim().toUpperCase();
		String[] str = new Validation().checkNull(option);
		return str;
	}
	
	public void searchDisplay(ArrayList<Article> arrList){
		for(Article art: arrList){
			System.out.println(art.getContent() + " "+ art.getTitle());
		}
	}

}
