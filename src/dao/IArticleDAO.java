package dao;
import dto.Article;

public interface IArticleDAO {
	public boolean insertArticle(Article art);
	public boolean updateArticle(Article art);
	public boolean deleteArticle(Article art);
}
