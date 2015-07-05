package dao;

public class Sort {
	public static String order = "id";
	public static String sort = "DESC";
	public static void setOrder(String orders){
		order = orders;
	}
	public static void setSort(String sorts){
		sort = sorts.toUpperCase();
	}
}
