<!doctype html>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.locale" var="locale" />
<html lang="${language}">
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
						<button type="submit">Home</button>
					</form>
				</li>
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="SignIn" />
						<button type="submit">Login</button>
					</form>
				</li>
				<li class="active">
					<form action="MainServlet" method="post">
						<button type="submit">About Us</button>
					</form>
				</li>
				<li class="active">
					<form action="MainServlet" method="get">
						<input type="hidden" name="locale" value="Ru" />
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
				<label for="username"><fmt:message bundle="${locale}"
						key="addingredientpage.title" />:</label>
			</h1>

		</div>
		<aside>
			<nav>
				<ul class="aside-menu">
					<li class="active">Barmen list</li>
					<li><a href="/donec/">Cocktail list</a></li>
				</ul>
			</nav>
			<h2>OUR OFFICES</h2>
			<p>
				<img src="images/sample.png" width="230" height="180"
					alt="Our offices">
			</p>
		</aside>
		<section>
			<blockquote>
				<div class="col-6 col-sm-3">
					<form action="MainServlet" method="post" name="PushIngredient">
						<div class="form-group">
							<input type="hidden" name="command" value="PushIngredient" /> <input
								type="text" class="edit" name="ingredientname" id="name"
								placeholder="Inter name" maxlength="45">
							<textarea rows="10" cols="45" name="ingredientdesc"
								maxlength="255">Add description</textarea>
							<button type="submit" class="active">Add</button>
						</div>
					</form>
				</div>
			</blockquote>
		</section>
	</div>
</body>
</html>
