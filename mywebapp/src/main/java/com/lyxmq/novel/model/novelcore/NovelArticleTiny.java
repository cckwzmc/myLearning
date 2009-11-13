package com.lyxmq.novel.model.novelcore;

import java.util.Date;

import com.lyxmq.novel.model.BaseModel;

/**
 * 
 * 文章内容的精简对象
 * @author Administrator
 *
 */
public class NovelArticleTiny extends BaseModel {
	private static final long serialVersionUID = -5290624427573952157L;
	private long id;
	private long typeId;
	private long superTypeId;
	private String title;
	private long sortrank;
	private Date sendDate;
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
