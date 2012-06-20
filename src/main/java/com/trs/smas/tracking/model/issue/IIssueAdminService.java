package com.trs.smas.tracking.model.issue;

import com.trs.client.TRSException;
import com.trs.smas.tracking.exception.SMASDataTrackingException;

public interface IIssueAdminService {

	/**
	 * 创建一个Issue
	 * @param documentId 文档SID
	 * @return 创建成功的Issue Key
	 * @throws TRSException 
	 */
	public String createIssue(String documentId) throws SMASDataTrackingException;
}
