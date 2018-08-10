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
							<input type="hidden" name="command" value="Sign_In" />
							<button type="submit">
								<fmt:message bundle="${locale}" key="menubar.login" />
							</button>
						</form>
					</li>
				</c:if>
				<c:if test="${sessionScope.Role != 'GUEST'}">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="Log_Out" />
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
							<input type="hidden" name="command" value="User_Panel" />
							<button type="submit">
								<fmt:message bundle="${locale}" key="menubar.userpanel" />
							</button>
						</form>
					</li>
				</c:if>
				<c:if
					test="${(sessionScope.Role == 'ADMIN') || sessionScope.Role == 'BARMAN' }">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="Barman_Panel" />
							<button type="submit">
								<fmt:message bundle="${locale}" key="menubar.barmanpanel" />
							</button>
						</form>
					</li>
				</c:if>
				<c:if test="${sessionScope.Role == 'ADMIN'}">
					<li class="active">
						<form action="MainServlet" method="post">
							<input type="hidden" name="command" value="Admin_Panel" />
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
								<fmt:message bundle="${locale}" key="sidebar.ingredientlist" />
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
						<c:if test="${!empty userCocktailMap}">
							<table>
								<tr>
									<td>Id</td>
									<td>Name</td>
									<td>Description</td>
									<td>Volume</td>
									<td>UserName</td>
									<td>Image</td>
									<td>AverageRating</td>
								</tr>
								<c:forEach items="${userCocktailMap}" var="userCocktailMap">
									<tr>
										<td>${userCocktailMap.key.cocktailId}</td>
										<td>${userCocktailMap.key.cocktailName}</td>
										<td>${userCocktailMap.key.cocktailDescription}</td>
										<td>${userCocktailMap.key.cocktailVol}</td>
										<td>${userCocktailMap.value.userName}</td>
										<td><img
											src="MainServlet?imageId=${userCocktailMap.key.cocktailId}&command=Show_Cocktail_Image"
											width="200" /></td>
										<td><c:if test="${!empty cocktailAverageRatingMap}">
												<c:forEach items="${cocktailAverageRatingMap}"
													var="cocktailAverageRatingMap">
													<c:if
														test="${userCocktailMap.key.cocktailId == cocktailAverageRatingMap.key.cocktailId}">
									${cocktailAverageRatingMap.value}
									</c:if>
												</c:forEach>
											</c:if>
										<td>
										<td><form action="MainServlet" method="post">
												<input type="hidden" name="command" value="Cocktail_Info" />
												<input type="hidden" name="cocktail_id"
													value="${userCocktailMap.key.cocktailId}" /> <input
													type="hidden" name="user_id"
													value="${userCocktailMap.value.userId}" />
												<button type="submit">INFO</button>
											</form></td>
										<c:if test="${sessionScope.Role == 'ADMIN'}">
											<td><form action="MainServlet" method="post">
													<input type="hidden" name="command" value="Delete_Cocktail" />
													<input type="hidden" name="cocktail_id"
														value="${userCocktailMap.key.cocktailId}" />
													<button type="submit" onclick="return proverka();">Delete</button>
												</form> <script type="text/javascript">
													function proverka() {
														if (confirm("Подтвердить")) {
															return true;
														} else {
															return false;
														}
													}
												</script></td>
										</c:if>
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
