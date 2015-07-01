package view;

import java.util.Scanner;

public class ArticleView {
	
	private String srtSearch;

	public String getSrtSearch() {
		return srtSearch;
	}

	public void setSrtSearch() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter keywoard to search: ");
		this.srtSearch = scan.next();
	}
	

}
