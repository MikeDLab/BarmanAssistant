<!doctype html>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.locale" var="locale" />
<html lang="${language}">
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title><fmt:message bundle="${locale}" key="title" /></title>
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
				<li class="active">
					<form action="Es" method="get">
						<button type="submit">
							<fmt:message bundle="${locale}" key="menubar.spanish" />
						</button>
					</form>
				</li>
				<li class="active">
					<form action="Ru" method="get">
						<button type="submit">
							<fmt:message bundle="${locale}" key="menubar.russian" />
						</button>
					</form>
				</li>
				<li class="active">
					<form action="En" method="get">
						<button type="submit">
							<fmt:message bundle="${locale}" key="menubar.english" />
						</button>
					</form>
				</li>
			</ul>
		</nav>
		<div id="heading">

			<h1>${language}</h1>

			<h1>
				<label for="username"><fmt:message bundle="${locale}"
						key="barmanlist.title" /></label>
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
				<div class="ingredient">
					<c:if test="${!empty userSet}">
						<table>
							<tr>
								<th><fmt:message bundle="${locale}" key="user.login" /></th>
								<th><fmt:message bundle="${locale}" key="user.name" /></th>
								<th><fmt:message bundle="${locale}" key="user.email" /></th>
								<th><fmt:message bundle="${locale}" key="user.averagerating" /></th>
								<th><fmt:message bundle="${locale}" key="user.cocktailnumber" /></th>
							</tr>
							<c:forEach items="${userSet}" var="userSet">
								<tr>
									<td>${userSet.userLogin}</td>
									<td>${userSet.userName}</td>
									<td>${userSet.userEmail}</td>
									<td><c:if test="${!empty userAverageRatingMap}">
											<c:forEach items="${userAverageRatingMap}"
												var="userAverageRatingMap">
												<c:if
													test="${userAverageRatingMap.key.userId == userSet.userId}">
									${userAverageRatingMap.value}
									</c:if>
											</c:forEach>
										</c:if></td>
									<td><c:if test="${!empty userCocktailNumberMap}">
											<c:forEach items="${userCocktailNumberMap}"
												var="userCocktailNumberMap">
												<c:if
													test="${userCocktailNumberMap.key.userId == userSet.userId}">
									${userCocktailNumberMap.value}
									</c:if>
											</c:forEach>
										</c:if></td>
									<td><c:if test="${userSet.userRole == 2 }">
											<li class="active">
												<form action="MainServlet" method="post">
													<input type="hidden" name="user_id"
														value="${userSet.userId}" /> <input type="hidden"
														name="command" value="Update_To_Barman" />
													<button type="submit"><fmt:message bundle="${locale}" key="button.upgrade" /></button>
												</form>
											</li>
										</c:if> <c:if test="${userSet.userRole == 1 }">
											<li class="active">
												<form action="MainServlet" method="post">
													<input type="hidden" name="user_id"
														value="${userSet.userId}" /> <input type="hidden"
														name="command" value="Downgrade_To_User" />
													<button type="submit"><fmt:message bundle="${locale}" key="button.downgrade" /></button>
												</form>
											</li>
										</c:if></td>
									<td><form action="MainServlet" method="post">
											<input type="hidden" name="command" value="Delete_User" /> <input
												type="hidden" name="user_id" value="${userSet.userId}" />
											<button type="submit" onclick="return check();">Delete</button>
										</form> <script type="text/javascript">
											function check() {
												if (confirm("<fmt:message bundle="${locale}" key="check" />")) {
													return true;
												} else {
													return false;
												}
											}
										</script></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</blockquote>
		</section>
			<footer>
			<div id="footer">
				<ctg:info-tag />
			</div>
		</footer>
	</div>
</body>
</html>
