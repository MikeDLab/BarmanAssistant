<!doctype html>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.locale" var="locale" />
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>Barman Assistant</title>
<link rel="stylesheet" href="css/styles.css" type="text/css">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Oswald:400,300"
	type="text/css">
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>
<body>
	<div id="wrapper">
		<header>
			<img src="images/logo.png" alt="Whitesquare logo">
		</header>
		<nav>
			<ul class="top-menu">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="Home" />
						<button type="submit">
							<fmt:message bundle="${locale}" key="menubar.homebutton" />
						</button>
					</form>
				</li>
				<li class="active">
					<form action="Es" method="get">
						<button type="submit">Es</button>
					</form>
				</li>
				<li class="active">
					<form action="MainServlet" method="get">
						<input type="hidden" name="locale" value="Ru" /> <input
							type="hidden" name="pageId" value="index.jsp" />
						<button type="submit">Русский</button>
					</form>
				</li>
				<li class="active">
					<form action="MainServlet" method="get">
						<input type="hidden" name="locale" value="En" />
						<button type="submit">Eng</button>
					</form>
				</li>
			</ul>
		</nav>
		<div id="heading">
			<h1>
				<fmt:message bundle="${locale}" key="loginpage.title" />
			</h1>
		</div>
		<section>
			<blockquote>
				<div class="col-6 col-sm-3">
					<form action="MainServlet" method="post" name="register">
						<div class="form-group">
							<input type="hidden" name="command" value="Login" /> <input
								type="text" class="edit" name="username" id="username"
								placeholder="Inter username" maxlength="45"> <input
								type="password" class="edit" name="password" id="username"
								placeholder="Inter password" maxlength="45">
							<button type="submit" class="active"><fmt:message bundle="${locale}" key="loginpage.signin" /></button>
						</div>
					</form>
					<form action="MainServlet" method="post" name="Registration">
						<div class="form-group">
							<input type="hidden" name="command" value="Registration" />
							<button type="submit" class="active"><fmt:message bundle="${locale}" key="loginpage.registration" /></button>
						</div>
					</form>
				</div>
			</blockquote>
		</section>
	</div>
</body>
</html>
