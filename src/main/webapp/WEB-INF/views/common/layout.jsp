<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>SMAS Data Tracking</title>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/bootstrap/bootstrap.min.css" type="text/css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/bootstrap/bootstrap-responsive.min.css" type="text/css" />
		<!--[if lt IE 9]>
		<script src="<%=request.getContextPath() %>/scripts/html5shim/html5.js"></script>
		<![endif]-->
		<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/bootstrap/bootstrap.min.js"></script>
		<decorator:head />
		<style type="text/css">
			body {
				padding-top: 60px;
				padding-bottom: 40px;
			}
			.sidebar-nav {
				padding: 9px 0;
			}
		</style>
	</head>
	<body>
		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					<a class="brand" href="<%=request.getContextPath() %>/">SMAS Data Tracking</a>
					<div class="btn-group pull-right">
						<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="icon-user"></i> admin
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="#">Profile</a></li>
							<li class="divider"></li>
							<li><a href="<%=request.getContextPath() %>/logout">Sign Out</a></li>
						</ul>
					</div>
					<!-- <div class="nav-collapse">
						<ul class="nav">
							<li class="active"><a href="#">Home</a></li>
							<li><a href="#about">About</a></li>
							<li><a href="#contact">Contact</a></li>
						</ul>
					</div> --><!--/.nav-collapse -->
				</div>
			</div>
		</div>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span3">
					<div class="well sidebar-nav">
						<ul class="nav nav-list">
							<li class="nav-header">Admin</li>
							<li><a href="<%=request.getContextPath() %>/feedback"><i class="icon-tasks"></i>Feedback</a></li>
							<li><a href="<%=request.getContextPath() %>/users"><i class="icon-user"></i>Users</a></li>
							<li class="nav-header">Personal</li>
							<!-- <li class="active"><a href="#">Link</a></li> -->
							<li><a href="<%=request.getContextPath() %>/settings"><i class="icon-cog"></i>Settings</a></li>
							<li class="divider"></li>
							<li><a href="#"><i class="icon-question-sign"></i>Help</a></li>
						</ul>
					</div><!--/.well -->
				</div><!--/span-->
				<div class="span9">
					<decorator:body />
				</div><!--/span-->
			</div><!--/row-->

			<hr>

			<footer>
			<p>&copy; TRS 2012</p>
			</footer>

		</div><!--/.fluid-container-->
	</body>
</html>
