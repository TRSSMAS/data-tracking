<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.trs.smas.tracking.bo.Feedback" %>
<%@ page import="com.trs.dev4.jdk16.utils.DateUtil" %>
<%@ page import="com.trs.dev4.jdk16.utils.StringHelper" %>
<%
	Feedback feedback = (Feedback)request.getAttribute("feedback");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			p{	padding-top: 5px; }
		</style>
	</head>
	<body>
		<form class="form-horizontal">
			<fieldset>
				<legend >Feedback Detail</legend>
				<div class="control-group">
					<label class="control-label" for="documentId">Document ID:</label>
					<div class="controls">
						<p><%=feedback.getDocumentId() %></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="description">Description:</label>
					<div class="controls">
						<p><%=feedback.getDescription() %></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="createdBy">Created By:</label>
					<div class="controls">
						<p><%=StringHelper.avoidNull(feedback.getCreatedUser()) %></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="createdAt">Created At:</label>
					<div class="controls">
						<p><%=(feedback.getCreatedTime() > 0) ? DateUtil.formatMillis(feedback.getCreatedTime()) : "" %></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="createdBy">Last Modified By:</label>
					<div class="controls">
						<p><%=StringHelper.avoidNull(feedback.getLastModifiedUser()) %></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="createdAt">Last Modified At:</label>
					<div class="controls">
						<p><%=(feedback.getLastModifiedTime() > 0) ? DateUtil.formatMillis(feedback.getLastModifiedTime()) : "" %></p>
					</div>
				</div>
				<div class="form-actions">
					<a class="btn btn-primary" href="<%=request.getContextPath() %>/feedback/<%=feedback.getId() %>/editform">Edit</a>
					<a class="btn" href="<%=request.getContextPath() %>/feedback">Return</a>
				</div>
			</fieldset>
		</form>	
	</body>
</html>
