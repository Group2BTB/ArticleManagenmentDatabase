package view;

import java.util.Scanner;

import process.Validation;

public class ArticleView {
	
	private String srtSearch;
	private Scanner scan;

	public String getSrtSearch() {
		return srtSearch;
	}

	public void setSrtSearch() {
		scan = new Scanner(System.in);
		System.out.print("Enter keywoard to search: ");
		this.srtSearch = scan.next();
	}
	
	public String[] getOption(){
		
		System.out.println("-> Choose: ");
		String option = scan.next();
		
		String[] str = new Validation().checkNull(option);
		
		return str;
		
	}
	

}
