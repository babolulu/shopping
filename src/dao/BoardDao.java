package dao;

import java.util.List;

import logic.BoardCommentModel;
import logic.BoardModel;

public interface BoardDao {
	// get all contents in JMBoard table
	List<BoardModel> getBoardList(int startArticleNum, int showArticleLimit);
	
	// show detail about selected article
	BoardModel getOneArticle(int idx);
	
	// get search result list
	List<BoardModel> searchArticle(String type, String keyword, int startArticleNum, int endArticleNum); 
	
	// get all comments
	List<BoardCommentModel> getCommentList(int idx);
	
	// get a comment
	BoardCommentModel getOneComment(int idx);
	
	// modify(update) article
	boolean modifyArticle(BoardModel board);
	
	// insert article data
	boolean writeArticle(BoardModel board);
	
	// insert comment data
	boolean writeComment(BoardCommentModel comment);
	
	// update hitcount
	void updateHitcount(int hitcount, int idx);
	
	// update recommendcount
	void updateRecommendCount(int recommendcount, int idx);
	
	// get contents count
	int getTotalNum();
	
	// get count of search results
	int getSearchTotalNum(String type, String keyword);
	
	// delete a comment
	void deleteComment(int idx);
	
	// delete a article
	void deleteArticle(int idx);
}
