<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.trs.smas.tracking.bo.Comment" %>
<%@ page import="com.trs.dev4.jdk16.utils.DateUtil" %>
<%@ page import="com.trs.dev4.jdk16.utils.StringHelper" %>
<%
	Comment comment = (Comment)request.getAttribute("comment");
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
				<legend >Comment Detail</legend>
				<div class="control-group">
					<label class="control-label" for="feedbackId">Feedback ID:</label>
					<div class="controls">
						<p><%=comment.getFeedbackId() %></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="content">Content:</label>
					<div class="controls">
						<p><%=comment.getContent() %></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="createdBy">Created By:</label>
					<div class="controls">
						<p><%=StringHelper.avoidNull(comment.getCreatedUser()) %></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="createdAt">Created At:</label>
					<div class="controls">
						<p><%=(comment.getCreatedTime() > 0)? DateUtil.formatMillis(comment.getCreatedTime()):"" %></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="createdBy">Last Modified By:</label>
					<div class="controls">
						<p><%=StringHelper.avoidNull(comment.getLastModifiedUser()) %></p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="createdAt">Last Modified At:</label>
					<div class="controls">
						<p><%=(comment.getLastModifiedTime() > 0)? DateUtil.formatMillis(comment.getLastModifiedTime()):"" %></p>
					</div>
				</div>
				<div class="form-actions">
					<a class="btn btn-primary" href="<%=request.getContextPath() %>/feedback/<%=comment.getFeedbackId() %>/comments/<%=comment.getId() %>/editform">Edit</a>
					<a class="btn" href="<%=request.getContextPath() %>/feedback/<%=comment.getFeedbackId() %>/comments">Return</a>
				</div>
			</fieldset>
		</form>	
	</body>
</html>
