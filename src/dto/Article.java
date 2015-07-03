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
	private int authorId;
	private String date;
	private String authorName;
	
	
	/* Constructor with parameter */
	public Article(int id, String title, int authorId , String content, String date){
		this.id = id;
		this.title = title;
		this.content = content;
		this.authorId = authorId;
		this.date = date;
	}
	public Article(int id, String title, String authorName , String date){
		this.id = id;
		this.title = title;
		this.content = content;
		this.authorName = authorName;
		this.date = date;
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
	
	public int getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
}
