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
						key="barmanlist.title" />:</label>
			</h1>

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
				<div class="ingredient">
					<c:if test="${!empty setBarman}">
						<table class="table table-bordered">
							<tr class="bg-info row">
								<th class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">Login</th>
								<th class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">Name</th>
								<th class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">Email</th>
								<th class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">Role</th>
								<c:if test="${sessionScope.Role != 'GUEST'}">
									<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">Rating</td>
								</c:if>
							</tr>
							<c:forEach items="${setBarman}" var="setBarman">
								<tr class="row">
									<td class="col-xs-3 col-sm-3 col-md-3  col-lg-3">${setBarman.userLogin}</td>
									<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${setBarman.userName}</td>
									<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${setBarman.userEmail}</td>
									<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${setBarman.userRole}</td>
									<c:if test="${!empty barmanRating}">
										<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">${barmanRating}</td>
									</c:if>
									<c:if test="${empty barmanRating}">
										<c:if test="${sessionScope.Role != 'GUEST'}">
											<td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 ">
												<form action="MainServlet" method="post">
													<input type="number" min="0" max="10"
														placeholder="Inter vol" class="edit" name="cocktailvol"
														id="vol"> <input type="hidden" name="test"
														value="number" /> <input type="hidden" name="command"
														value="AddBarmanRating" />
													<input type="hidden" name="barmanid"
														value="${setBarman.userId}" />
													<button type="submit" class="active">Оценить</button>
											</td>
											</form>
										</c:if>
									</c:if>
									<!-- <td class="col-xs-1 col-sm-1 col-md-1  col-lg-1 "><li
										class="active">
											<form action="MainServlet" method="get">
												<input type="hidden" name="locale" value="Ru" />
												<button type="submit">Edit</button>
											</form>
									</li> 
									</td> -->
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</blockquote>
		</section>
	</div>
</body>
</html>
