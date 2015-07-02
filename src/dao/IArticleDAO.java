package dao;
import dto.Article;

public interface IArticleDAO {
	public boolean insertArticle(Article art);
	public boolean updateArticle(int id,Article art);
	public boolean deleteArticle(int id);
	
}
