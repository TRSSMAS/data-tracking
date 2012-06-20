package com.trs.smas.tracking.model.feedback.impl;

import com.trs.dev4.jdk16.dao.PagedList;
import com.trs.dev4.jdk16.dao.SearchFilter;
import com.trs.dev4.jdk16.model.IBaseManager;
import com.trs.smas.tracking.bo.Feedback;
import com.trs.smas.tracking.model.feedback.IFeedbackAdminService;
import com.trs.smas.tracking.system.ContextHolder;

public class FeedbackAdminServiceImpl implements IFeedbackAdminService {

	private IBaseManager<Feedback> feedbackManager;

	public void setFeedbackManager(IBaseManager<Feedback> feedbackManager) {
		this.feedbackManager = feedbackManager;
	}

	@Override
	public Feedback get(int feedbackId) {
		return feedbackManager.get(feedbackId);
	}

	@Override
	public int add(Feedback feedback) {
		feedback.setCreatedUser(ContextHolder.getCurrentUser());
		feedback.setCreatedTime(System.currentTimeMillis());
		return feedbackManager.addNew(feedback);
	}

	@Override
	public void update(Feedback feedback) {
		feedback.setLastModifiedUser(ContextHolder.getCurrentUser());
		feedback.setLastModifiedTime(System.currentTimeMillis());
		feedbackManager.update(feedback);
	}

	@Override
	public int remove(int feedbackId) {
		SearchFilter sf = SearchFilter.getNoPagedFilter();
		sf.addEqCondition("id", feedbackId);
		return feedbackManager.delete(sf);
	}

	@Override
	public PagedList<Feedback> pageList(int pageNo, int pageSize) {
		return feedbackManager.pagedObjects(SearchFilter.getSearchFilter(pageNo, pageSize));
	}

	@Override
	public Feedback findFirstByDocument(String documentId) {
		SearchFilter sf = SearchFilter.getSearchFilter(0, 1);
		sf.addEqCondition("documentId", documentId);
		return feedbackManager.findFirst(sf);
	}

	@Override
	public int total() {
		return feedbackManager.total();
	}
}
