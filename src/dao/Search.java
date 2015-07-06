package dao;

public class Search {
	public static boolean searchAct = false;
	public static String searchType =null;
	public static String searchValue=null;
	public static void  searchByID(int value){
		searchValue = value+"";
		searchAct = true;
		searchType = "id";
	}
	public static void  searchByTitle(String value){
		searchValue = value;
		searchAct = true;
		searchType = "title";
	}
	public static void  searchByAuthor(String value){
		searchValue = value;
		searchAct = true;
		searchType = "author";
	}
	
	public static void notSearch(){
		Pagination.page = 1;
		Pagination.perpage=5;
		
		searchValue = null;
		searchAct = false;
		searchType = null;
	}
}
