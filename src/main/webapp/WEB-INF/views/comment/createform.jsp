<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring-form-tag" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Create Comment</title>
		<style type="text/css">
			span.error{color:red;}
		</style>
	</head>
	<body>
		<spring-form-tag:form cssClass="form-horizontal" modelAttribute="comment" action="../comments" method="POST">
			<fieldset>
				<legend >Create Comment</legend>
				<div class="control-group">
					<spring-form-tag:label path="feedbackId" cssClass="control-label">Feedback ID:</spring-form-tag:label>
					<div class="controls">
						<spring-form-tag:input path="feedbackId" cssClass="input-xlarge uneditable-input" disabled="true"/>
					</div>
				</div>
				<div class="control-group">
					<spring-form-tag:label path="content" cssClass="control-label">Content:</spring-form-tag:label>
					<div class="controls">
						<spring-form-tag:textarea path="content" cssClass="input-xlarge" rows="8"/>
						<spring-form-tag:errors path="content" cssClass="error" />
					</div>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">Save</button>
					<a class="btn" href="./">Cancel</a>
				</div>
			</fieldset>
		</spring-form-tag:form>
	</body>
</html>
