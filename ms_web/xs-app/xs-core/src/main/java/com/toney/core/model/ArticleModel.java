package com.toney.core.model;


public class ArticleModel extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3893473101310195928L;
	
	
	private ArticleBaseModel articleBaseModel;
	private ArticleExtendModel  articleExtendModel;
	
	public ArticleBaseModel getArticleBaseModel() {
		return articleBaseModel;
	}
	public void setArticleBaseModel(ArticleBaseModel articleBaseModel) {
		this.articleBaseModel = articleBaseModel;
	}
	public ArticleExtendModel getArticleExtendModel() {
		return articleExtendModel;
	}
	public void setArticleExtendModel(ArticleExtendModel articleExtendModel) {
		this.articleExtendModel = articleExtendModel;
	}
	@Override
	public String toString() {
		return "ArticleModel [articleBaseModel=" + articleBaseModel
				+ ", articleExtendModel=" + articleExtendModel + "]";
	}
	
}
