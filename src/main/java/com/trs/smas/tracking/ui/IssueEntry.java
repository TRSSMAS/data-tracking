package com.trs.smas.tracking.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trs.dev4.jdk16.utils.StringHelper;
import com.trs.smas.tracking.exception.SMASDataTrackingException;
import com.trs.smas.tracking.model.issue.IIssueAdminService;

@Controller
public class IssueEntry {

	private IIssueAdminService issueAdminService;

	public void setIssueAdminService(IIssueAdminService issueAdminService) {
		this.issueAdminService = issueAdminService;
	}

	@ResponseBody
	@RequestMapping(value = "/issues", method = RequestMethod.POST)
	public String createIssue(
			@RequestParam(value = "documentId") String documentId) {
		if (StringHelper.isEmpty(documentId)) {
			return "{'succeed':false,'reason':'document id required'}";
		}

		try {
			String key = issueAdminService.createIssue(documentId);
			return "{'succeed':true,'key':'" + key + "'}";
		} catch (SMASDataTrackingException e) {
			return "{'succeed':false,'reason':'"
					+ e.getMostSpecificCause().getMessage() + "'}";
		}
	}
}
