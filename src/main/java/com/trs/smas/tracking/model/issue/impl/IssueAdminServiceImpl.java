package com.trs.smas.tracking.model.issue.impl;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import cn.net.trs.dev4.rpc.soap.jirasoapservice_v2.JiraSoapService;
import cn.net.trs.dev4.rpc.soap.jirasoapservice_v2.JiraSoapServiceService;

import com.atlassian.jira.rpc.exception.RemoteAuthenticationException;
import com.atlassian.jira.rpc.exception.RemoteException;
import com.atlassian.jira.rpc.soap.beans.RemoteIssue;
import com.trs.client.TRSException;
import com.trs.dev4.jdk16.utils.StringHelper;
import com.trs.smas.tracking.bo.Document;
import com.trs.smas.tracking.exception.SMASDataTrackingException;
import com.trs.smas.tracking.model.issue.IIssueAdminService;
import com.trs.smas.tracking.model.search.IDocumentSearchService;
import com.trs.smas.tracking.system.Const;

public class IssueAdminServiceImpl implements IIssueAdminService {

	private static final Logger LOG = Logger
			.getLogger(IssueAdminServiceImpl.class);

	private IDocumentSearchService documentSearchService;

	private JiraSoapServiceService jiraSoapServiceFactory;

	private static final String JIRA_ISSUE_TYPE_BUG = "1";
	
	private String jiraProject;

	private String jiraUserName;
	private String jiraPassword;

	public void setJiraProject(String jiraProject) {
		this.jiraProject = jiraProject;
	}

	public void setJiraUserName(String jiraUserName) {
		this.jiraUserName = jiraUserName;
	}

	public void setJiraPassword(String jiraPassword) {
		this.jiraPassword = jiraPassword;
	}

	public void setDocumentSearchService(
			IDocumentSearchService documentSearchService) {
		this.documentSearchService = documentSearchService;
	}

	public void setJiraSoapServiceFactory(
			JiraSoapServiceService jiraSoapServiceFactory) {
		this.jiraSoapServiceFactory = jiraSoapServiceFactory;
	}

	@Override
	public String createIssue(String documentId)
			throws SMASDataTrackingException {
		String trsl = Const.DOCUMENT_FIELD_SID + "='" + documentId + "'";
		Document document = null;
		try {
			document = documentSearchService.findFirst(trsl);
		} catch (TRSException e) {
			LOG.error("fail to get document by sid:[" + documentId + "]", e);
		}
		
		if(document == null){
			document = new Document();
			document.setSId(documentId);
		}

		try {
			return createIssue(buildIssue(document), jiraUserName, jiraPassword);
		} catch (Exception e) {
			LOG.error("can not create issue by document:[" + document + "]", e);
			throw new SMASDataTrackingException(
					"can not create issue via jira soap service.", e);
		}
	}

	protected RemoteIssue buildIssue(Document document) {
		RemoteIssue issue = new RemoteIssue();
		issue.setProject(this.jiraProject);
		issue.setType(JIRA_ISSUE_TYPE_BUG);
		issue.setSummary(StringHelper.isEmpty(document.getTitle()) ? document
				.getSId() : document.getTitle());
		
		StringBuilder descBuilder = new StringBuilder();
		descBuilder.append(Const.DOCUMENT_FIELD_SID).append(": ").append(StringHelper.avoidNull(document.getSId())).append("\n\r");
		descBuilder.append(Const.DOCUMENT_FIELD_GROUPNAME).append(": ").append(StringHelper.avoidNull(document.getGroupName())).append("\n\r");
		descBuilder.append(Const.DOCUMENT_FIELD_SITENAME).append(": ").append(StringHelper.avoidNull(document.getSiteName())).append("\n\r");
		descBuilder.append(Const.DOCUMENT_FIELD_TITLE).append(": ").append(StringHelper.avoidNull(document.getTitle())).append("\n\r");
		descBuilder.append(Const.DOCUMENT_FIELD_URL).append(": ");
		String url = StringHelper.avoidNull(document.getURL());
		if((false == StringHelper.isEmpty(url)) && false == url.startsWith("http")){
			descBuilder.append("http://");
		}
		descBuilder.append(url);
		issue.setDescription(descBuilder.toString());
		
		return issue;
	}

	protected String createIssue(RemoteIssue issue, String username,
			String password) throws ServiceException,
			RemoteAuthenticationException, RemoteException,
			java.rmi.RemoteException {
		JiraSoapService jiraSoapService = this.jiraSoapServiceFactory
				.getJirasoapserviceV2();
		String token = jiraSoapService.login(username, password);
		String key = jiraSoapService.createIssue(token, issue).getKey();
		jiraSoapService.logout(token);
		return key;
	}
}
