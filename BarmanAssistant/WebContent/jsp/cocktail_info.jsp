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
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

</head>
<body
	style="display: flex; flex-flow: column; justify-content: space-between">
	<section>
		<header>
			<div class="container">
				<div class="logo">
					<i class="fa fa-glass logo-img"></i>
					<p class="logo-text">
						<fmt:message bundle="${locale}" key="title" />
					</p>
				</div>
			</div>
		</header>
		<nav class="navigation">
			<div class="container">
				<ul class="top-menu">
					<li class="active">
						<form action="app" method="post">
							<input type="hidden" name="command" value="Home" />
							<button type="submit">
								<fmt:message bundle="${locale}" key="menubar.homebutton" />
							</button>
						</form>
					</li>
					<c:if test="${sessionScope.Role == 'GUEST'}">
						<li class="active">
							<form action="app" method="post">
								<input type="hidden" name="command" value="Sign_In" />
								<button type="submit">
									<fmt:message bundle="${locale}" key="menubar.login" />
								</button>
							</form>
						</li>
					</c:if>
					<c:if test="${sessionScope.Role != 'GUEST'}">
						<li class="active">
							<form action="app" method="post">
								<input type="hidden" name="command" value="Log_Out" />
								<button class="menu-text" type="submit">
									<fmt:message bundle="${locale}" key="menubar.logout" />
								</button>
							</form>
						</li>
					</c:if>
					<c:if test="${sessionScope.Role != 'GUEST'}">
						<li class="active">
							<form action="app" method="post">
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
							<form action="app" method="post">
								<input type="hidden" name="command" value="Barman_Panel" />
								<button type="submit">
									<fmt:message bundle="${locale}" key="menubar.barmanpanel" />
								</button>
							</form>
						</li>
					</c:if>
					<c:if test="${sessionScope.Role == 'ADMIN'}">
						<li class="active">
							<form action="app" method="post">
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
			</div>
		</nav>
	</section>
	<section>
		<div class="container">
			<div id="heading">
				<h1>${language}</h1>

				<h1>
					<label for="username"><fmt:message bundle="${locale}"
							key="homepage.title" /></label>
				</h1>
				<h1>${Role}</h1>
				<h1>${User.userName}</h1>
			</div>
			<aside style="float: left; width: 25%; margin-right: 2%">
				<nav>
					<ul class="aside-menu">
						<li class="active">
							<form action="app" method="post">
								<input type="hidden" name="command" value="Cocktail_List" />
								<button class="side" type="submit">
									<fmt:message bundle="${locale}" key="sidebar.cocktaillist" />
								</button>
							</form>
						</li>
						<c:if test="${sessionScope.Role != 'GUEST'}">
							<li class="active">
								<form action="app" method="post">
									<input type="hidden" name="command" value="Show_Barman" />
									<button class="side" type="submit">
										<fmt:message bundle="${locale}" key="sidebar.barmanlist" />
									</button>
								</form>
							</li>
						</c:if>
						<li class="active">
							<form action="app" method="post">
								<input type="hidden" name="command" value="Show_Ingredient" />
								<button class="side" type="submit">
									<fmt:message bundle="${locale}" key="sidebar.ingredientlist" />
								</button>
							</form>
						</li>
						<c:if test="${sessionScope.Role == 'ADMIN'}">
							<li class="active">
								<form action="app" method="post">
									<input type="hidden" name="command" value="Add_Ingredient" />
									<button class="side" type="submit">
										<fmt:message bundle="${locale}"
											key="sidebar.addingredientpage" />
									</button>
								</form>
							</li>
						</c:if>
						<c:if test="${sessionScope.Role != 'GUEST'}">
							<li class="active">
								<form action="app" method="post">
									<input type="hidden" name="command" value="Add_Cocktail" />
									<button class="side" type="submit">
										<fmt:message bundle="${locale}" key="sidebar.addcocktail" />
									</button>
								</form>
							</li>
						</c:if>
					</ul>
				</nav>
				<!-- 			<h2>OUR OFFICES</h2>
					<p>
						<img src="images/sample.png" width="230" height="180"
							alt="Our offices">
					</p> -->
			</aside>
			<div style="float: left; width: 73%;">
				<blockquote>
					<div>
						<p>${Errormessage}</p>
						<div class="form-group">
							<c:if test="${!empty cocktail}">
								<table>
									<tr>
										<th><fmt:message bundle="${locale}"
												key="cocktaillist.name" /></th>
										<th><fmt:message bundle="${locale}"
												key="cocktaillist.description" /></th>
										<th><fmt:message bundle="${locale}"
												key="cocktaillist.vol" /></th>
										<th><fmt:message bundle="${locale}"
												key="cocktaillist.image" /></th>
										<th><fmt:message bundle="${locale}"
												key="cocktaillist.rating" /></th>
									</tr>
									<tr class="row">
										<td>${cocktail.cocktailName}</td>
										<td>${cocktail.cocktailDescription}</td>
										<td>${cocktail.cocktailVol}</td>
										<td><img
											src="app?imageId=${cocktail.cocktailId}&command=Show_Cocktail_Image" /></td>
										<td><c:if test="${!empty cocktailRatingMap}">
												<c:forEach items="${cocktailRatingMap}"
													var="cocktailRatingMap">
													<c:if
														test="${cocktailRatingMap.key.cocktailId == cocktail.cocktailId}">
														<c:if test="${!empty cocktailRatingMap.value}">${cocktailRatingMap.value.rating}</c:if>
														<c:if test="${empty cocktailRatingMap.value}">
															<c:if
																test="${sessionScope.Role != 'GUEST' && sessionScope.User.userId != author.userId }">
																<form action="app" method="post">
																	<input type="number" min="0" max="10" class="edit"
																		name="cocktail_rating" id="vol" placeholder="0">
																	<input type="hidden" name="test" value="number" /> <input
																		type="hidden" name="command"
																		value="Add_Cocktail_Rating" /> <input type="hidden"
																		name="cocktail_id" value="${cocktail.cocktailId}" />
																	<input type="hidden" name="user_id"
																		value="${author.userId}" />
																	<button type="submit" class="active">
																		<fmt:message bundle="${locale}" key="button.estimate" />
																	</button>
																</form>
															</c:if>
														</c:if>

													</c:if>
												</c:forEach>
											</c:if></td>
									</tr>
								</table>
							</c:if>
						</div>
						<div>
							<c:if test="${!empty author}">
								<table>
									<tr>
										<th><fmt:message bundle="${locale}" key="user.name" /></th>
										<th><fmt:message bundle="${locale}" key="user.email" /></th>
									</tr>
									<tr>
										<td>${author.userName}</td>
										<td>${author.userEmail}</td>
									</tr>

								</table>
							</c:if>
						</div>
						<div>
							<c:if test="${!empty ingredientSet}">
								<table border="1">
									<tr>
										<th><fmt:message bundle="${locale}"
												key="ingredientlist.name" /></th>
										<th><fmt:message bundle="${locale}"
												key="ingredientlist.description" /></th>
									</tr>
									<c:forEach items="${ingredientSet}" var="ingredientSet">
										<tr>
											<td>${ingredientSet.ingredientName}</td>
											<td>${ingredientSet.ingredientDescription}</td>
										</tr>
									</c:forEach>
								</table>
							</c:if>
							<div></div>
						</div>
					</div>
				</blockquote>
			</div>
		</div>
	</section>
	<footer>
		<div id="footer">
			<ctg:info-tag />
		</div>
	</footer>
</body>
</html>
