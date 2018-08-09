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
				<c:if test="${sessionScope.Role == 'GUEST'}">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="Sign_In" />
						<button type="submit"><fmt:message bundle="${locale}" key="menubar.login" /></button>
					</form>
				</li>
			</c:if>
			<c:if test="${sessionScope.Role != 'GUEST'}">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="Log_Out" />
						<button type="submit"><fmt:message bundle="${locale}" key="menubar.logout" /></button>
					</form>
				</li>
			</c:if>
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
				<c:if test="${sessionScope.Role != 'GUEST'}">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="User_Panel" />
						<button type="submit"><fmt:message bundle="${locale}" key="menubar.userpanel" /></button>
					</form>
				</li>
				</c:if>
				<c:if test="${(sessionScope.Role == 'ADMIN') || sessionScope.Role == 'BARMAN' }">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="Barman_Panel" />
						<button type="submit"><fmt:message bundle="${locale}" key="menubar.barmanpanel" /></button>
					</form>
				</li>
			</c:if>
				<c:if test="${sessionScope.Role == 'ADMIN'}">
				<li class="active">
					<form action="MainServlet" method="post">
						<input type="hidden" name="command" value="Admin_Panel" />
						<button type="submit"><fmt:message bundle="${locale}" key="menubar.adminpanel" /></button>
					</form>
				</li>
			</c:if>
			</ul>
		</nav>
		<div id="heading">
			<h1>
				<fmt:message bundle="${locale}" key="loginpage.title" />
			</h1>
		</div>
		<aside>
		<nav>
				<ul class="aside-menu">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="Cocktail_List" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.cocktaillist" />
							</button>
						</form>
					</li>
					<c:if test="${sessionScope.Role != 'GUEST'}">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="Show_Barman" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.barmanlist" />
							</button>
						</form>
					</li>
					</c:if>
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="Show_Ingredient" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}"
									key="sidebar.ingredientlist" />
							</button>
						</form>
					</li>
					<c:if test="${sessionScope.Role == 'ADMIN'}">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="Add_Ingredient" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.addingredientpage" />
							</button>
						</form>
					</li>
					</c:if>
					<c:if test="${sessionScope.Role != 'GUEST'}">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="Add_Cocktail" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.addcocktail" />
							</button>
						</form>
					</li>
					</c:if>
				</ul>
			</nav>
		</aside>
		<section>
			<blockquote>
				<div class="col-6 col-sm-3">
					<form action="MainServlet" method="post" name="register">
						<p>${Errormessage}</p>
						<div class="form-group">
							<input type="hidden" name="command" value="Login" /> <input
								type="text" class="edit" name="user_login" id="user_login"
								placeholder="Inter username" maxlength="45"> <input
								type="password" class="edit" name="user_password" id="username"
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
