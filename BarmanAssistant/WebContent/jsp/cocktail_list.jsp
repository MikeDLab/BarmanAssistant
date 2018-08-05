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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

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
							<input type="hidden" name="command" value="SignIn" />
							<button type="submit">
								<fmt:message bundle="${locale}" key="menubar.login" />
							</button>
						</form>
					</li>
				</c:if>
				<c:if test="${sessionScope.Role != 'GUEST'}">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="LogOut" />
							<button type="submit">
								<fmt:message bundle="${locale}" key="menubar.logout" />
							</button>
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
							<input type="hidden" name="command" value="UserPanel" />
							<button type="submit">
								<fmt:message bundle="${locale}" key="menubar.userpanel" />
							</button>
						</form>
					</li>
				</c:if>
				<c:if test="${sessionScope.Role == 'ADMIN'}">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="AdminPanel" />
							<button type="submit">
								<fmt:message bundle="${locale}" key="menubar.adminpanel" />
							</button>
						</form>
					</li>
				</c:if>
			</ul>
		</nav>
		<div id="heading">
			<h1>${language}</h1>
			<h1>
				<label for="username"><fmt:message bundle="${locale}"
						key="cocktaillist.title" />:</label>
			</h1>
			<h1>${Role}</h1>
			<h1>${User.userName}</h1>
		</div>
		<aside>
			<nav>
				<ul class="aside-menu">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="CocktailList" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.cocktaillist" />
							</button>
						</form>
					</li>
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="ShowBarman" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.barmanlist" />
							</button>
						</form>
					</li>
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="ShowIngredient" />
							<button class="side" type="submit">
								<fmt:message bundle="${locale}" key="sidebar.ingredientlist" />
							</button>
						</form>
					</li>
					<c:if test="${sessionScope.Role == 'ADMIN'}">
						<li class="active">
							<form action="MainServlet" method="post">
								<input type="hidden" name="command" value="AddIngredient" />
								<button class="side" type="submit">
									<fmt:message bundle="${locale}" key="sidebar.addingredientpage" />
								</button>
							</form>
						</li>
					</c:if>
					<c:if test="${sessionScope.Role != 'GUEST'}">
						<li class="active">
							<form action="MainServlet" method="post">
								<input type="hidden" name="command" value="AddCocktail" />
								<button class="side" type="submit">
									<fmt:message bundle="${locale}" key="sidebar.addcocktail" />
								</button>
							</form>
						</li>
					</c:if>
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
					<p>${Errormessage}</p>
					<div class="form-group">
						<%-- 	<c:if test="${!empty setCocktail}">
									<table class="table table-bordered">
										<tr class="bg-info row">
											<th class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">Id</th>
											<th class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">Name</th>
											<th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">Description</th>
											<th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">Volume</th>
												<th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">UserId</th>
										</tr>
										<c:forEach items="${setCocktail}" var="setCocktail">
											<tr class="row">
												<td class="col-xs-3 col-sm-3 col-md-3  col-lg-3">${setCocktail.cocktailId}</td>
												<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${setCocktail.cocktailName}</td>
													<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${setCocktail.cocktailDescription}</td>
														<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${setCocktail.cocktailVol}</td>
														<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${setCocktail.userId}</td>
											</tr>
										</c:forEach>
									</table>
								</c:if> --%>
						<c:if test="${!empty userCocktailMap}">
							<table class="table table-bordered">
								<tr class="bg-info row">
									<th class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">Id</th>
									<th class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">Name</th>
									<th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">Description</th>
									<th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">Volume</th>
									<th class="col-xs-3 col-sm-3 col-md-3  col-lg-3 ">UserName</th>
								</tr>
								<c:forEach items="${userCocktailMap}" var="userCocktailMap">
									<tr class="row">
										<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${userCocktailMap.key.cocktailId}</td>
										<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${userCocktailMap.key.cocktailName}</td>
										<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${userCocktailMap.key.cocktailDescription}</td>
										<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${userCocktailMap.key.cocktailVol}</td>
										<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${userCocktailMap.value.userName}</td>
										<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 "><form
												action="MainServlet" method="post">
												<input type="hidden" name="command" value="cocktailInfo" />
												<input type="hidden" name="cocktail_id" value="${userCocktailMap.key.cocktailId}"/>
												<input type="hidden" name="user_id" value="${userCocktailMap.value.userId}"/>
												<button type="submit">INFO</button>
											</form></td>

									</tr>
								</c:forEach>
							</table>
						</c:if>
					</div>
				</div>
			</blockquote>
		</section>
	</div>
</body>
</html>