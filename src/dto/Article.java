package dto;

/*
 * This class is used to store all information of the article
 * and methods to set and get value of it.
 */

public class Article {
	/*
	 *@param id is used to store id value of Article
	 *@param title is used to store title of Article
	 *@param content is used store content of each article
	 *@param author is used to store author of each article
	 */
	
	private int id;
	private String title;
	private String content;
	private String author;
	//private String date;
	
	/* Constructor with parameter */
	public Article(int id, String title, String content, String author /*String date*/){
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		//this.date = date;
	}
	
	public Article() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id ;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/*public String getDate(){
		return this.date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}*/
}
