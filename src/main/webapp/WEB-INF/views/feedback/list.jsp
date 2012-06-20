<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring-form-tag" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.trs.smas.tracking.system.Const" %>
<%@ page import="com.trs.smas.tracking.bo.Feedback" %>
<%@ page import="com.trs.dev4.jdk16.dao.PagedList" %>
<%@ page import="com.trs.dev4.jdk16.utils.DateUtil" %>
<%@ page import="com.trs.dev4.jdk16.utils.StringHelper" %>
<%@ page import="com.trs.smas.tracking.util.PageUtil" %>
<%
	PagedList<Feedback> feedback = (PagedList<Feedback>)request.getAttribute("feedback");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Feedback List</title>
		<style type="text/css">
			form{margin: 0;}
		</style>
	</head>
	<body>
		<h2>Feedback List &nbsp;&nbsp;
			<a style="font-size: 13px;" href="<%=request.getContextPath() %>/feedback/createform">Create New</a>
		</h2>
		
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>NO.</th>
					<th>Document ID</th>
					<th>Description</th>
					<th>Created By</th>
					<th>Created At</th>
					<th>OP</th>
				</tr>
			</thead>
			<tbody>
<%
	int startPos = feedback.getPageIndex() * feedback.getPageSize();
	for(int i = 0 ; i < feedback.getThisPageTotal() ; i++ ){
		Feedback current = feedback.get(i);
		
%>
				<tr>
					<td><%=startPos + i + 1 %></td>
					<td><%=current.getDocumentId() %></td>
					<td><%=current.getDescription() %></td>
					<td><%=StringHelper.avoidNull(current.getCreatedUser()) %></td>
					<td><%=(current.getCreatedTime() > 0) ? DateUtil.formatMillis(current.getCreatedTime()) : "" %></td>
					<td>
						<form action="<%=request.getContextPath() %>/feedback/<%=current.getId() %>" method="POST" id="frm_d_<%=current.getId() %>">
							<a href="<%=request.getContextPath() %>/feedback/<%=current.getId() %>/comments"><i class="icon-comment"></i></a>&nbsp;
							<a href="<%=request.getContextPath() %>/feedback/<%=current.getId() %>"><i class="icon-list-alt"></i></a>&nbsp;
							<a href="<%=request.getContextPath() %>/feedback/<%=current.getId() %>/editform"><i class="icon-pencil"></i></a>&nbsp;
							<input type="hidden" name="<%=Const.HIDDEN_HTTP_METHOD_PARAMETER_NAME %>" value="DELETE" />
							<a onclick="$('#frm_d_<%=current.getId() %>').submit();return false;"><i class="icon-trash"></i></a>
						</form>
					</td>
				</tr>
<%		
	}
%>			
			</tbody>
			<tfoot>
				<%=PageUtil.generatePageTableBottom("?pageSize="+feedback.getPageSize(),feedback.getPageSize(),feedback.getTotalItemCount(),feedback.getPageIndex()) %>
			</tfoot>
		</table>
		<script type="text/javascript" >
			$(".navigation_page_td").attr("colspan","6").css("text-align","right");
		</script>		
	</body>
</html>
