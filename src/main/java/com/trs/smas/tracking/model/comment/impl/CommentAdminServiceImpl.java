package com.trs.smas.tracking.model.comment.impl;

import com.trs.dev4.jdk16.dao.PagedList;
import com.trs.dev4.jdk16.dao.SearchFilter;
import com.trs.dev4.jdk16.model.IBaseManager;
import com.trs.smas.tracking.bo.Comment;
import com.trs.smas.tracking.model.comment.ICommentAdminService;
import com.trs.smas.tracking.system.ContextHolder;

public class CommentAdminServiceImpl implements ICommentAdminService {

	private IBaseManager<Comment> commentManager;
	
	public void setCommentManager(IBaseManager<Comment> commentManager) {
		this.commentManager = commentManager;
	}

	@Override
	public Comment get(int commentId) {
		return commentManager.get(commentId);
	}

	@Override
	public int add(Comment comment) {
		comment.setCreatedUser(ContextHolder.getCurrentUser());
		comment.setCreatedTime(System.currentTimeMillis());
		return commentManager.addNew(comment);
	}

	@Override
	public void update(Comment comment) {
		comment.setLastModifiedUser(ContextHolder.getCurrentUser());
		comment.setLastModifiedTime(System.currentTimeMillis());
		commentManager.update(comment);
	}

	@Override
	public int remove(int commentId) {
		SearchFilter sf = SearchFilter.getNoPagedFilter();
		sf.addEqCondition("id", commentId);
		return commentManager.delete(sf);
	}

	@Override
	public PagedList<Comment> pageList(int feedbackId, int pageNo, int pageSize) {
		SearchFilter sf = SearchFilter.getSearchFilter(pageNo, pageSize);
		sf.addEqCondition("feedbackId", feedbackId);
		return commentManager.pagedObjects(sf);
	}

	@Override
	public PagedList<Comment> findByFeedback(int feedbackId, int pageNo,
			int pageSize) {
		SearchFilter sf = SearchFilter.getSearchFilter(pageNo, pageSize);
		sf.addEqCondition("feedbackId", feedbackId);
		return commentManager.pagedObjects(sf);
	}

	@Override
	public int total() {
		return commentManager.total();
	}

}
