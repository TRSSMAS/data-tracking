<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring-form-tag" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.trs.smas.tracking.system.Const" %>
<%@ page import="com.trs.smas.tracking.bo.Comment" %>
<%@ page import="com.trs.dev4.jdk16.dao.PagedList" %>
<%@ page import="com.trs.dev4.jdk16.utils.DateUtil" %>
<%@ page import="com.trs.dev4.jdk16.utils.StringHelper" %>
<%@ page import="com.trs.smas.tracking.util.PageUtil" %>
<%
	PagedList<Comment> comments = (PagedList<Comment>)request.getAttribute("comments");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Comments List</title>
		<style type="text/css">
			form{margin: 0;}
		</style>
	</head>
	<body>
		<h2>Comments List &nbsp;&nbsp;
			<a style="font-size: 13px;" href="<%=request.getContextPath() %>/feedback/<%=request.getAttribute("feedbackId") %>/comments/createform">Create New</a>
		</h2>
		
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>NO.</th>
					<th>Content</th>
					<th>Created By</th>
					<th>Created At</th>
					<th>OP</th>
				</tr>
			</thead>
			<tbody>
<%
	int startPos = comments.getPageIndex() * comments.getPageSize();
	for(int i = 0 ; i < comments.getThisPageTotal() ; i++ ){
		Comment comment = comments.get(i);
		
%>
				<tr>
					<td><%=startPos + i + 1 %></td>
					<td><%=comment.getContent() %></td>
					<td><%=StringHelper.avoidNull(comment.getCreatedUser()) %></td>
					<td><%=(comment.getCreatedTime() > 0)? DateUtil.formatMillis(comment.getCreatedTime()) : "" %></td>
					<td>
						<form action="<%=request.getContextPath() %>/feedback/<%=comment.getFeedbackId() %>/comments/<%=comment.getId() %>" method="POST" id="frm_d_<%=comment.getFeedbackId() %>_<%=comment.getId() %>">
							<a href="<%=request.getContextPath() %>/feedback/<%=comment.getFeedbackId() %>/comments/<%=comment.getId() %>/editform">Edit</a>&nbsp;
							<input type="hidden" name="<%=Const.HIDDEN_HTTP_METHOD_PARAMETER_NAME %>" value="DELETE" />
							<a onclick="$('#frm_d_<%=comment.getFeedbackId() %>_<%=comment.getId() %>').submit();return false;">Delete</a>&nbsp;
							<a href="<%=request.getContextPath() %>/feedback/<%=comment.getFeedbackId() %>/comments/<%=comment.getId() %>">Detail</a>
						</form>
					</td>
				</tr>
<%		
	}
%>			
			</tbody>
			<tfoot>
				<%=PageUtil.generatePageTableBottom("?pageSize="+comments.getPageSize(),comments.getPageSize(),comments.getTotalItemCount(),comments.getPageIndex()) %>
			</tfoot>
		</table>
		<script type="text/javascript" >
			$(".navigation_page_td").attr("colspan","5").css("text-align","right");
		</script>		
	</body>
</html>
