<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring-form-tag" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<spring-form-tag:form cssClass="form-horizontal" modelAttribute="feedback" action="./" method="PUT" >
			<fieldset>
				<legend >Edit Feedback</legend>
				<div class="control-group">
					<spring-form-tag:label path="documentId" cssClass="control-label">Document ID:</spring-form-tag:label>
					<div class="controls">
						<spring-form-tag:input path="documentId" cssClass="input-xlarge uneditable-input"/>
					</div>
				</div>
				<div class="control-group">
					<spring-form-tag:label path="description" cssClass="control-label">Description:</spring-form-tag:label>
					<div class="controls">
						<spring-form-tag:textarea path="description" cssClass="input-xlarge" rows="8"/>
						<spring-form-tag:errors path="description" cssClass="error" />
					</div>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">Save changes</button>
					<a class="btn" href="<%=request.getContextPath() %>/feedback">Cancel</a>
				</div>
			</fieldset>
		</spring-form-tag:form>
	</body>
</html>