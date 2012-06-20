package com.trs.smas.tracking.model.comment;

import com.trs.dev4.jdk16.dao.PagedList;
import com.trs.smas.tracking.bo.Comment;

public interface ICommentAdminService {

	public Comment get(int commentId);
	
	public int add(Comment comment);
	
	public void update(Comment comment);
	
	public int remove(int commentId);
	
	public PagedList<Comment> pageList(int feedbackId, int pageNo, int pageSize);
	
	public PagedList<Comment> findByFeedback(int feedbackId, int pageNo, int pageSize);
	
//	public Foo findFirstByXXX();
	
	public int total();
}
