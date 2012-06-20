package com.trs.smas.tracking.model.feedback;

import com.trs.dev4.jdk16.dao.PagedList;
import com.trs.smas.tracking.bo.Feedback;

public interface IFeedbackAdminService {

	public Feedback get(int feedbackId);
	
	public int add(Feedback feedback);
	
	public void update(Feedback feedback);
	
	public int remove(int feedbackId);
	
	public PagedList<Feedback> pageList(int pageNo, int pageSize);
	
	public Feedback findFirstByDocument(String documentId);
	
	public int total();
}
